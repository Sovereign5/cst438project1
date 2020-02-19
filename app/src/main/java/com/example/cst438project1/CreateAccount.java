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

public class CreateAccount extends AppCompatActivity implements OnClickListener {
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
        passwordText = findViewById(R.id.enterPasswordID);

        //Enter Button
        continueButton = findViewById(R.id.button5);

        //AccountLogDAO
        mAccountLog = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();

    }

    public void addAccount(){
        continueButton.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(CreateAccount.this, "New Account Created", Toast.LENGTH_LONG).show();
                        AccountLog newUser = new AccountLog(firstnameText.getText().toString(), lastnameText.getText().toString(), usernameText.getText().toString(), passwordText.getText().toString() );
                        mAccountLog.insert(newUser);
                        //redirect to the userpage
                    }
                }
        );
    }



    @Override
    public void onClick(View v) {

    }
}
