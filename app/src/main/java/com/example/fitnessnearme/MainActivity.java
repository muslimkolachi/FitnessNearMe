package com.example.fitnessnearme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessnearme.LandingPage;
import com.example.fitnessnearme.R;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button register;
    EditText editTextTextEmailAddress;
    EditText editTextTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = "admin@iqra.com";
                String password = "123";

                String enteredEmail = editTextTextEmailAddress.getText().toString().trim();
                String enteredPassword = editTextTextPassword.getText().toString().trim();

                if (enteredEmail.equals(email) && enteredPassword.equals(password)) {
                    // Login successful, navigate to the landing page
                    Intent intent = new Intent(MainActivity.this, LandingPage.class);
                    startActivity(intent);
                } else {
                    // Login failed, display an error message
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
