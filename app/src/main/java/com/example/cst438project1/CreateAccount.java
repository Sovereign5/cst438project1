package com.example.cst438project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;

public class CreateAccount extends AppCompatActivity {
    private EditText firstnameText;
    private EditText lastnameText;
    private EditText usernameText;
    private EditText passwordText;
    private Button continueButton;

    AccountLogDAO mAccountLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        //EditTexts for Names and Private Information
        firstnameText = findViewById(R.id.enterFirstNameID);
        lastnameText = findViewById(R.id.enterLastNameID);
        usernameText = findViewById(R.id.enterUserNameID);
        passwordText = findViewById(R.id.enterPasswordeditText);

        //Enter Button
        continueButton = findViewById(R.id.createAccountButtonSubmit);

        //AccountLogDAO
        mAccountLog = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();


        continueButton.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AccountLog newUser = new AccountLog(firstnameText.getText().toString(), lastnameText.getText().toString(), usernameText.getText().toString(), passwordText.getText().toString() );
                        if(mAccountLog.findCredentials(newUser.getUsername(),newUser.getPassword())) {
                            Toast.makeText(CreateAccount.this, "Account Already Exists", Toast.LENGTH_LONG).show();
                            return;
                        }
                        Toast.makeText(CreateAccount.this, "New Account Created", Toast.LENGTH_LONG).show();
                        mAccountLog.insert(newUser);
                        Intent intent = new Intent(CreateAccount.this,UserPage.class);
                        intent.putExtra("username",newUser.getUsername());
                        intent.putExtra("pass", newUser.getPassword());
                        startActivity(intent);
                    }
                }
        );

    }
}
