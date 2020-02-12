package com.example.cst438project1;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cst438project1.DB.AccountLogDAO;

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

        


    }

    @Override
    public void onClick(View v) {

    }
}
