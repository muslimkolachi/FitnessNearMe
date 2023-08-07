package com.example.fitnessnearme;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity {

    private List<FitnessExercise> exerciseList;
    private int currentExerciseIndex;
    private Button skipButton;
    private boolean isWorkoutRunning;
    private CountDownTimer timer;
    private long timeRemainingInMillis; // New variable to store remaining time

    private long startTimeMillis; // Variable to store the start time of the timer

    private ImageView exerciseImageView;
    private TextView exerciseNameTextView;
    private TextView timerTextView;
    private Button startButton;
    private Button pauseButton;
    private Button resumeButton;
    private Button nextButton;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_workout);

        exerciseImageView = findViewById(R.id.exerciseImageView);
        exerciseNameTextView = findViewById(R.id.exerciseNameTextView);
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        resumeButton = findViewById(R.id.resumeButton);
        nextButton = findViewById(R.id.nextButton);
        finishButton = findViewById(R.id.finishButton);
        skipButton = findViewById(R.id.skipButton);

        exerciseList = new ArrayList<>();
        exerciseList.add(new FitnessExercise("Exercise 1", "Description 1", R.drawable.exercise1, "12 reps"));
        exerciseList.add(new FitnessExercise("Exercise 2", "Description 2", R.drawable.exercise2, "15 reps"));
        // Add more exercises as needed

        currentExerciseIndex = 0;
        isWorkoutRunning = false;

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipExercise();
            }
        });
        // Set click listeners for buttons
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWorkout();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseWorkout();
            }
        });

        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeWorkout();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipExercise();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWorkout();
            }
        });
    }


    private void startWorkout() {
        isWorkoutRunning = true;
        showNextExercise();
    }

    private void pauseWorkout() {
        if (isWorkoutRunning && timer != null) {
            timer.cancel();
            // Store the remaining time
            timeRemainingInMillis = timeRemainingInMillis - (System.currentTimeMillis() - startTimeMillis);
            // Implement UI update for paused state if needed
            pauseButton.setVisibility(View.GONE);
            resumeButton.setVisibility(View.VISIBLE);
        }
    }

    private void resumeWorkout() {
        if (isWorkoutRunning) {
            // Create a new timer with the remaining time
            timer = new CountDownTimer(timeRemainingInMillis, 1000) {
                public void onTick(long millisUntilFinished) {
                    timeRemainingInMillis = millisUntilFinished;
                    long secondsRemaining = millisUntilFinished / 1000;
                    timerTextView.setText(String.valueOf(secondsRemaining));
                }

                public void onFinish() {
                    currentExerciseIndex++;
                    showNextExercise();
                }
            }.start();
            // Implement UI update for resumed state if needed
            startTimeMillis = System.currentTimeMillis();
            pauseButton.setVisibility(View.VISIBLE);
            resumeButton.setVisibility(View.GONE);
        }
    }

    private void skipExercise() {
        if (isWorkoutRunning && timer != null) {
            timer.cancel();
            currentExerciseIndex++;
            showNextExercise();
        }
    }

    private void finishWorkout() {
        if (isWorkoutRunning && timer != null) {
            timer.cancel();
            isWorkoutRunning = false;
            // Implement UI update for finished workout
            timerTextView.setText("Workout Done");
        }
    }

    private void showNextExercise() {
        if (currentExerciseIndex < exerciseList.size()) {
            final FitnessExercise currentExercise = exerciseList.get(currentExerciseIndex);
            updateUIWithExercise(currentExercise);

            // Set the countdown timer to 30 seconds (in milliseconds)
            final long totalTimeInMillis = 30000;

            timer = new CountDownTimer(totalTimeInMillis, 1000) {
                public void onTick(long millisUntilFinished) {
                    timeRemainingInMillis = millisUntilFinished;
                    long secondsRemaining = millisUntilFinished / 1000;
                    timerTextView.setText(String.valueOf(secondsRemaining));
                }

                public void onFinish() {
                    currentExerciseIndex++;
                    showNextExercise();
                }
            }.start();
            startTimeMillis = System.currentTimeMillis(); // Store the start time
        } else {
            isWorkoutRunning = false;
            timerTextView.setText("Workout Done");
        }
    }

    private void updateUIWithExercise(FitnessExercise currentExercise) {
        exerciseNameTextView.setText(currentExercise.getName());
        exerciseImageView.setImageResource(currentExercise.getGifResourceId());
        timerTextView.setText(""); // Reset timer text

        // Show appropriate buttons based on workout state
        startButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.VISIBLE);
        resumeButton.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
        finishButton.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
