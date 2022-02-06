package com.example.team;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText userMailInput, passwordInput;
    Button loginRequestButton, registerRequestButton;
    String isAdmin ="false";
    RadioButton adminRadioButton , guestRadioButton;
    DB db;
    public void onToggle(View view){
        if(isAdmin.equals("true"))
        {
            adminRadioButton.setChecked(false);
            guestRadioButton.setChecked(true);
            isAdmin ="false";
        }else
        {
            adminRadioButton.setChecked(true);
            guestRadioButton.setChecked(false);
            isAdmin ="true";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userMailInput = findViewById(R.id.loginUserMaleInput);
        passwordInput = findViewById(R.id.loginPasswordInput);
        loginRequestButton = findViewById(R.id.loginRequestButton);
        registerRequestButton = findViewById(R.id.registerRequestButton);
        adminRadioButton = findViewById(R.id.adminRadio);
        guestRadioButton = findViewById(R.id.guestRadio);
        db = new DB(this);

        loginRequestButton.setOnClickListener(view -> {

            String userMail = userMailInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (userMail.equals("") || password.equals(""))
                Toast.makeText(login.this, "Please Enter all Field", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkUserPass = db.checkUserPass(userMail, password,isAdmin);
                if (checkUserPass) {
                    Toast.makeText(login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    if (isAdmin.equals("true"))
                    {
                        Intent intent = new Intent(getApplicationContext(),admin.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        String[] data = db.getUserData(userMail);
                        intent.putExtra("Email",data[0]);
                        intent.putExtra("phone",data[1]);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(login.this, "Invalid E-Mail or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        registerRequestButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), register.class);
            startActivity(intent);
        });
    }
}