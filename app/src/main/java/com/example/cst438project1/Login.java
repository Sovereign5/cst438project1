package com.example.cst438project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;

    Button mSubmit;
    Button createLink;

    AccountLogDAO mAccountLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Username & Password
        username = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);


        //Submit button
        mSubmit = findViewById(R.id.button2);
        createLink = (Button) findViewById(R.id.createAccountLink);

        //AccountLogDAO
        mAccountLog = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();



        mSubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String usernameInput = username.getText().toString();
                        String passwordInput = password.getText().toString();
                        if(mAccountLog.findCredentials(usernameInput,passwordInput)){
                            toastMaker("Login Successful");
                            Intent intent = new Intent(Login.this,UserPage.class);
                            intent.putExtra("username",usernameInput);
                            intent.putExtra("pass", passwordInput);
                            startActivity(intent);
                        }
                        else{
                            toastMaker("Incorrect Username/Password");
                        }

                    }
                }
        );


        createLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,CreateAccount.class);
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



}
