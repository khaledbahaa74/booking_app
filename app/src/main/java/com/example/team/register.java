package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText userMailInput, passwordInput, confirmPasswordInput, phoneInput;
    Button registerConfirmButton;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userMailInput = findViewById(R.id.registerUserMailInput);
        passwordInput = findViewById(R.id.registerPasswordInput);
        confirmPasswordInput = findViewById(R.id.registerConfirmPasswordInput);
        phoneInput = findViewById(R.id.registerPhoneInput);
        registerConfirmButton = findViewById(R.id.registerConfirmButton);
        db = new DB(this);

        registerConfirmButton.setOnClickListener(view -> {
            String userMail = userMailInput.getText().toString();
            String password = passwordInput.getText().toString();
            String confirmPassword = confirmPasswordInput.getText().toString();
            String phone = phoneInput.getText().toString();

            if (userMail.length() < 5 || password.length() < 4 || confirmPassword.length() < 4 || phone.length() < 11 || phone.charAt(0) != '0' || phone.charAt(1) != '1')
                Toast.makeText(register.this, "Please Enter all Fields Correctly", Toast.LENGTH_SHORT).show();
            else if (userMail.indexOf('@') == -1 || userMail.indexOf('.') == -1 || userMail.indexOf('.') - userMail.indexOf('@') < 3)
            {
                Toast.makeText(register.this, "Enter Valid Email Format", Toast.LENGTH_SHORT).show();
            }
            else {
                if (password.equals(confirmPassword)) {
                    Boolean check = db.checkUser(userMail);
                    if (!check) {
                        Boolean insert = db.insertUser(userMail, password, phone,"false");
                        if (insert) {
                            Toast.makeText(register.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(register.this, "Registered Failed ", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(register.this, "E-Mail Already Exists!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(register.this, "Passwords Not Matching!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}