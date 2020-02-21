package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;

import java.util.List;

public class editAccount extends AppCompatActivity {

    private AccountLogDAO accountLogDAO;
    AccountLog currUser;
    List<AccountLog> allUsers;

    TextView currUserDisplay;

    EditText FirstNameEditText;
    EditText LastNameEditText;
    EditText UsernameEditText;
    EditText PasswordEditText;

    Button returnButton;
    Button confirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        setUpActivity();

        currUserDisplay = (TextView) findViewById(R.id.editUsernameDisplay);
        FirstNameEditText = (EditText) findViewById(R.id.editFirstEditText);
        LastNameEditText = (EditText) findViewById(R.id.editLastEditText);
        UsernameEditText = (EditText) findViewById(R.id.editUsernameEditText);
        PasswordEditText = (EditText) findViewById(R.id.editPasswordEditText);
        returnButton = (Button) findViewById(R.id.editUserReturnButton);
        confirmButton = (Button) findViewById(R.id.editUserSaveButton);

        currUserDisplay.setText(currUser.getUsername());
        FirstNameEditText.setText(currUser.getFirstname());
        LastNameEditText.setText(currUser.getLastname());
        UsernameEditText.setText(currUser.getUsername());
        PasswordEditText.setText(currUser.getPassword());



        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uFirst = FirstNameEditText.getText().toString();
                String uLast = LastNameEditText.getText().toString();
                String uUsername = UsernameEditText.getText().toString();
                String uPass = PasswordEditText.getText().toString();

                if(uFirst.equals(currUser.getFirstname()) && uLast.equals(currUser.getLastname()) && uUsername.equals(currUser.getUsername()) && uPass.equals(currUser.getPassword())) {
                    toastMaker("No Changes Detected");
                    return;
                }

                for(AccountLog a : allUsers) {
                    if(a.getUsername().equals(uUsername) && a.getAccountId() != currUser.getAccountId()) {
                        toastMaker("Sorry That Username Is Already Taken Please Try Again");
                        return;
                    }
                }


                currUser.setFirstname(uFirst);
                currUser.setLastname(uLast);
                currUser.setUsername(uUsername);
                currUser.setPassword(uPass);
                accountLogDAO.update(currUser);
                Intent intent = new Intent(editAccount.this,UserPage.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass", currUser.getPassword());
                startActivity(intent);
            }
        });


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editAccount.this,UserPage.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass", currUser.getPassword());
                startActivity(intent);
            }
        });

    }


    private void toastMaker(String message){
        Toast t = Toast.makeText(this.getApplicationContext(),message,Toast.LENGTH_LONG );
        //Using CENTER_VERTICAL to make Dylan happy.
        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
        t.show();
    }



    public void setUpActivity() {
        accountLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();

        currUser = accountLogDAO.findAccount(getIntent().getStringExtra("username"),getIntent().getStringExtra("pass"));
        allUsers = accountLogDAO.getAccountLog();
    }
}
