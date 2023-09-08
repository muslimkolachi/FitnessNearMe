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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StepCounterActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private TextView stepCountTextView;
    private TextView totalStepCountTextView;
    private Button newac;

    private int stepCount = 0;
    private int totalStepCount = 0;
    private boolean counting = true;
    private boolean canDetectStep = true;

    private float accelerationThreshold = 10.0f;
    private int cooldownMillis = 1000;

    private SharedPreferences sharedPreferences;
    private static final String PREF_TOTAL_STEP_COUNT = "total_step_count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        newac = findViewById(R.id.newac);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        stepCountTextView = findViewById(R.id.stepCountTextView);
        totalStepCountTextView = findViewById(R.id.totalStepCountTextView);

        sharedPreferences = getSharedPreferences("StepCounterPrefs", MODE_PRIVATE);

        totalStepCount = sharedPreferences.getInt(PREF_TOTAL_STEP_COUNT, 0);
        totalStepCountTextView.setText("Total Steps: " + totalStepCount);

        newac.setOnClickListener(view -> {
            totalStepCount = 0;
            totalStepCountTextView.setText("Total Steps: " + totalStepCount);
            Intent intent = new Intent(StepCounterActivity.this, WorkoutActivity.class);
            startActivity(intent);
        });

        startCounting();
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

        // Save total step count to SharedPreferences
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(PREF_TOTAL_STEP_COUNT, totalStepCount);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void startCounting() {
        counting = true;
        stepCount = 0;
        canDetectStep = true;
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
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

            new Handler().postDelayed(() -> canDetectStep = true, cooldownMillis);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}
