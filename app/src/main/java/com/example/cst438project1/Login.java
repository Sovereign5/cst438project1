package com.example.cst438project1;

import android.os.Bundle;
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

        //AccountLogDAO
        mAccountLog = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();
    }

    private boolean correctAccount() {

        String name = username.getText().toString();
        String pw = password.getText().toString();

        return mAccountLog.findCredentials(name, pw);

    }

    public void submit(){
        mSubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean x = correctAccount();
                        if(x = true){
                            Toast.makeText(Login.this, "Login", Toast.LENGTH_LONG).show();
                            mSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //sending to the next screen
                                }
                            });
                        }
                        else{
                            Toast.makeText(Login.this, "Incorrect Username/Password", Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }

}
