package com.example.fitnessnearme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final String LOGIN_URL = "https://fitnessnearmee.000webhostapp.com/login.php";
    private static final String REGISTER_URL = "YOUR_PHP_REGISTER_SCRIPT_URL";

    private EditText editTextTextEmailAddress;
    private EditText editTextTextPassword;
    private Button login;
    private Button register;
    private ImageView logo;

    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        logo = findViewById(R.id.logo);

        client = new OkHttpClient();

        // Request location permission when the activity loads
        requestLocationPermission();

        // Set initial visibility of logo to invisible
        logo.setVisibility(View.INVISIBLE);

        // Fade-in animation for the logo
        Animation fadeInAnimation = new AlphaAnimation(0f, 1f);
        fadeInAnimation.setDuration(1000);
        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation started
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Animation ended
                logo.setVisibility(View.VISIBLE); // Set the visibility to VISIBLE when the animation ends
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Animation repeated
            }
        });
        logo.startAnimation(fadeInAnimation);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the fields are empty
                if (isFieldsEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Perform login
                performLogin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the registration activity
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean hasLocationPermission() {
        int permissionState = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Explain why location permission is needed
            Toast.makeText(this, "Location permission is required for this app", Toast.LENGTH_SHORT).show();
        } else {
            // Request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void performLogin() {
        String enteredEmail = editTextTextEmailAddress.getText().toString().trim();
        String enteredPassword = editTextTextPassword.getText().toString().trim();

        RequestBody requestBody = new FormBody.Builder()
                .add("username", enteredEmail)
                .add("password", enteredPassword)
                .build();

        Request request = new Request.Builder()
                .url(LOGIN_URL)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();

                if (responseBody.equals("Login Success")) {
                    // Login successful, navigate to the landing page
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, LandingPage.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    // Login failed, display an error message
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private boolean isFieldsEmpty() {
        String email = editTextTextEmailAddress.getText().toString().trim();
        String password = editTextTextPassword.getText().toString().trim();
        return email.isEmpty() || password.isEmpty();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Call handleLocationPermissionResult method from LocationUtils class
        LocationUtils.handleLocationPermissionResult(requestCode, grantResults, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Request location permission when the activity resumes
        if (!LocationUtils.hasLocationPermission(this)) {
            LocationUtils.requestLocationPermission(this);
        }
    }
}
