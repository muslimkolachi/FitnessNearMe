package com.example.fitnessnearme;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CustomExercise extends AppCompatActivity {

    private Spinner exerciseSpinner;
    private ImageView gifImageView;
    private Button startCountdownButton;
    private CountDownTimer countdownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_exercise);

        exerciseSpinner = findViewById(R.id.exerciseSpinner);
        gifImageView = findViewById(R.id.gifImageView);
        startCountdownButton = findViewById(R.id.startCountdownButton);

        // Create an ArrayAdapter for the Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.exercises,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter for the Spinner
        exerciseSpinner.setAdapter(adapter);

        // Spinner item selection listener
        exerciseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Load the corresponding GIF for the selected exercise
                loadExerciseGif(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        // Button click listener
        startCountdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCountdown();
            }
        });
    }

    private void loadExerciseGif(int exerciseIndex) {
        // Replace with your logic to load the GIF for the selected exercise
        // You can store GIFs in the "res/raw" folder and load them based on the exerciseIndex.
        // Example: int gifResource = R.raw.exercise1;
        // gifImageView.setImageResource(gifResource);

        // Make the ImageView visible
        gifImageView.setVisibility(View.VISIBLE);
    }

    private void startCountdown() {
        countdownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update countdown text (optional)
                // countdownTextView.setText("Time remaining: " + millisUntilFinished / 1000 + " seconds");
            }

            @Override
            public void onFinish() {
                // Countdown finished, handle the event (e.g., play a sound)
            }
        }.start();
    }
}
