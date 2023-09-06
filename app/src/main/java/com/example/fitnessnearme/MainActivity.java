package com.example.fitnessnearme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText inputEmail;
    private Button buttonLogin;
    private EditText inputPassword;
    private TextView forgotPassword;
    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register);
        forgotPassword = findViewById(R.id.forgetPassword);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.pass);
        buttonLogin = findViewById(R.id.login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, forgetpassword.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputEmail.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                } else if (inputPassword.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
            }
        });
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
    private void login() {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<LoginResponseModel> login = networkService.login(inputEmail.getText().toString(), inputPassword.getText().toString());
        login.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                LoginResponseModel responseBody = response.body();

                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Constants.KEY_IS_LOGGED_IN, true);

                        UserDetailModel userDetail = responseBody.getUserDetailObject();
                        if (userDetail != null && userDetail.getUserDetails() != null && !userDetail.getUserDetails().isEmpty()) {
                            UserModel userDetails = userDetail.getUserDetails().get(0);
                            editor.putFloat(Constants.KEY_USER_WEIGHT, userDetails.getWeight());
                            editor.putFloat(Constants.KEY_USER_HEIGHT, userDetails.getHeight());

                            // Storing the FirstName and LastName in SharedPreferences
                            editor.putString(Constants.KEY_USERNAME, userDetails.getUsername());
                            editor.apply();
                            Toast.makeText(MainActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LandingPage.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "User details not available.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
    }

