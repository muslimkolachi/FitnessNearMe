package com.example.fitnessnearme;

import android.os.Bundle;
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

public class RegistrationActivity extends AppCompatActivity {
    private EditText usernamee, emailEditText, weightt, passEditText, passwordAgainEditText, heightt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        // Initialize your views
        usernamee = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        weightt = findViewById(R.id.weight);
        heightt = findViewById(R.id.height);
        passEditText = findViewById(R.id.pass);
        passwordAgainEditText = findViewById(R.id.paswordagain);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input data
                String username = usernamee.getText().toString().trim();
                String height = heightt.getText().toString().trim();
                String weight = weightt.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passEditText.getText().toString().trim();
                String passwordAgain = passwordAgainEditText.getText().toString().trim();

                // Validate input (you may add more validation checks as needed)
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordAgain.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(passwordAgain)) {
                    Toast.makeText(RegistrationActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                int parsedHeight = Integer.parseInt(height);
                int parsedWeight = Integer.parseInt(weight);

                // Send user data to the server
                registerUser(username, email, parsedHeight, parsedWeight, password);
            }
        });
    }

    private void registerUser(final String username, final String email, final int height, final int weight, final String password) {
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
                            // If successful, go back to MainActivity (login page)
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
