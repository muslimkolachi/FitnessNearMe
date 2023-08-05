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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText inputEmail;
    private  Button buttonLogin;
    private EditText inputPassword;
    private TextView  forgotpassword;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register);

        forgotpassword=findViewById(R.id.forgetPassword);

        inputEmail = findViewById(R.id.email);

        inputPassword = findViewById(R.id.pass);

        buttonLogin = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the registration activity
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the registration activity
                Intent intent = new Intent(MainActivity.this, forgetpassword.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputEmail.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }
                 else if (inputPassword.getText().toString().equals("")) {
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
                        SharedPreferences preferences = getSharedPreferences(com.example.fitnessnearme.Constants.PREFERENCE_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(com.example.fitnessnearme.Constants.KEY_ISE_LOGGED_IN, true);
                        editor.putString(com.example.fitnessnearme.Constants.KEY_USERNAME, responseBody.getUserDetailObject().getUserDetails().get(0).getFirstName() + " " + responseBody.getUserDetailObject().getUserDetails().get(0).getLastName());

                        editor.putString(Constants.KEY_USERNAME, responseBody.getUserDetailObject().getUserDetails().get(0).getUsername());


                        editor.apply();
                        Toast.makeText(MainActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LandingPage.class));
                        finish();
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