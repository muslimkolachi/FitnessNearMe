
package com.example.fitnessnearme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessnearme.MainActivity;
import com.example.fitnessnearme.R;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delayed execution to start the MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish the splash activity
            }
        }, SPLASH_DELAY);
    }
}
