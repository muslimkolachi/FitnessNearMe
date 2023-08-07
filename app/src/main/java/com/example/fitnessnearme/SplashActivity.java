package com.example.fitnessnearme;
import com.bumptech.glide.Glide;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 3000; // 6 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        // Calculate the progress increment for the given delay
        final int totalProgressTime = 100;
        final int incrementTime = (int) (totalProgressTime / (SPLASH_DELAY / 1000));

        final Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int progress = 0; progress <= totalProgressTime; progress += incrementTime) {
                    final int currentProgress = progress;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(currentProgress);
                        }
                    });
                    try {
                        Thread.sleep(incrementTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Start the MainActivity after the splash delay
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish the splash activity
            }
        }, SPLASH_DELAY);
    }
}
