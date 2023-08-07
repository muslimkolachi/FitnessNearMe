package com.example.fitnessnearme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StepCounterActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private TextView stepCountTextView;
    private TextView totalStepCountTextView; // New TextView for total steps
    private Button startButton;
    private Button stopButton;
    private Button newac;

    private int stepCount = 0;
    private int totalStepCount = 0; // Total steps variable
    private boolean counting = false;
    private boolean canDetectStep = true;

    private float accelerationThreshold = 10.0f; // Adjust this value for your needs
    private int cooldownMillis = 1000; // Cooldown period in milliseconds

    private SharedPreferences sharedPreferences;
    private static final String PREF_STEP_COUNT = "step_count";
    private static final String PREF_TOTAL_STEP_COUNT = "total_step_count"; // Shared preferences key for total step count

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        newac=findViewById(R.id.newac);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        stepCountTextView = findViewById(R.id.stepCountTextView);
        totalStepCountTextView = findViewById(R.id.totalStepCountTextView); // Initialize the total steps TextView
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        sharedPreferences = getSharedPreferences("StepCounterPrefs", MODE_PRIVATE);

        // Load and display total steps from SharedPreferences
        totalStepCount = sharedPreferences.getInt(PREF_TOTAL_STEP_COUNT, 0);
        totalStepCountTextView.setText("Total Steps: " + totalStepCount);

        startButton.setOnClickListener(view -> startCounting());
        stopButton.setOnClickListener(view -> stopCounting());
        newac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StepCounterActivity.this,WorkoutActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (counting) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    private void startCounting() {
        counting = true;
        stepCount = 0;
        canDetectStep = true;
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void stopCounting() {
        counting = false;

        // Update and save total steps to SharedPreferences
        totalStepCount += stepCount;
        totalStepCountTextView.setText("Total Steps: " + totalStepCount);
        sharedPreferences.edit().putInt(PREF_TOTAL_STEP_COUNT, totalStepCount).apply();

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float acceleration = (float) Math.sqrt(x * x + y * y + z * z);

        if (canDetectStep && acceleration > accelerationThreshold) {
            stepCount++;
            stepCountTextView.setText("Step Count: " + stepCount);
            canDetectStep = false;

            // Set a delay before enabling step detection again
            new Handler().postDelayed(() -> canDetectStep = true, cooldownMillis);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}
