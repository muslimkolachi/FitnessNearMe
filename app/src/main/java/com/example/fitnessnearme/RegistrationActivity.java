package com.example.fitnessnearme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, weightEditText, passEditText, passwordAgainEditText, heightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        // Initialize your views
        usernameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        weightEditText = findViewById(R.id.weight);
        heightEditText = findViewById(R.id.height);
        passEditText = findViewById(R.id.pass);
        passwordAgainEditText = findViewById(R.id.paswordagain);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input data
                String username = usernameEditText.getText().toString().trim();
                String height = heightEditText.getText().toString().trim();
                String weight = weightEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passEditText.getText().toString().trim();
                String passwordAgain = passwordAgainEditText.getText().toString().trim();

                // Validate input
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordAgain)) {
                    Toast.makeText(RegistrationActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate email format
                if (!isValidEmail(email)) {
                    Toast.makeText(RegistrationActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate password strength
                if (!isValidPassword(password)) {
                    Toast.makeText(RegistrationActivity.this, "Password is weak", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(passwordAgain)) {
                    Toast.makeText(RegistrationActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if height and weight are empty
                if (TextUtils.isEmpty(height) || TextUtils.isEmpty(weight)) {
                    Toast.makeText(RegistrationActivity.this, "Height and weight are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                int parsedHeight = Integer.parseInt(height);
                float parsedWeight = Float.parseFloat(weight);

                // Send user data to the server
                registerUser(username, email, parsedHeight, parsedWeight, password);
            }
        });
    }

    // Helper method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    // Helper method to validate password strength
    private boolean isValidPassword(String password) {
        // Modify this regex pattern to match your desired password strength criteria.
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return Pattern.compile(passwordRegex).matcher(password).matches();
    }

    private void registerUser(final String username, final String email, final int height, final float weight, final String password) {
        // Replace "https://example.com/registration.php" with the actual URL of your PHP script
        String url = "https://fitnessnearmee.000webhostapp.com/signup.php";

        // Create the request using Volley
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the server response (if needed)
                        Toast.makeText(RegistrationActivity.this, response, Toast.LENGTH_SHORT).show();

                        // Check if the response indicates successful registration (you may customize this based on your PHP script response)
                        if (response.equals("Sign Up Success")) {
                            // Store user weight and height in SharedPreferences
                            SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putFloat("userWeight", weight);
                            editor.putFloat("userHeight", height);
                            editor.apply();

                            // If successful, go to the landing page
                            Intent intent = new Intent(RegistrationActivity.this, LandingPage.class);
                            startActivity(intent);

                            // Finish the current activity (optional)
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response (if needed)
                        Toast.makeText(RegistrationActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("weight", String.valueOf(weight));  // Convert weight to String
                params.put("height", String.valueOf(height));  // Convert height to String
                params.put("password", password);
                return params;
            }
        };

        // Add the request to the RequestQueue.
        // Make sure you have initialized the RequestQueue somewhere in your app (e.g., in the Application class).
        VolleySingleton.getInstance(this).getRequestQueue().add(request);
    }
}
