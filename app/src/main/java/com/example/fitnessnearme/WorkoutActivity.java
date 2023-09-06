package com.example.fitnessnearme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity {

    private List<FitnessExercise> exerciseList;
    private int currentExerciseIndex;
    private Button skipButton;
    private boolean isWorkoutRunning;
    private CountDownTimer timer;
    private long timeRemainingInMillis;
    private long startTimeMillis;
    private ImageView exerciseImageView;
    private TextView exerciseNameTextView;
    private TextView timerTextView;
    private Button startButton;
    private Button pauseButton;
    private Button resumeButton;
    private Button nextButton;
    private Button finishButton;
    private RelativeLayout statusBar;
    private TextView statusTextView;

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

        statusBar = findViewById(R.id.statusBar);
        statusTextView = findViewById(R.id.statusTextView);

        exerciseList = new ArrayList<>();
        exerciseList.add(new FitnessExercise("Exercise 1", "Description 1", R.drawable.exercise1, "12 reps"));
        exerciseList.add(new FitnessExercise("Exercise 2", "Description 2", R.drawable.exercise2, "15 reps"));
        exerciseList.add(new FitnessExercise("Exercise 3", "Description 3", R.drawable.exercise3, "10 reps"));
        exerciseList.add(new FitnessExercise("Exercise 4", "Description 4", R.drawable.ezgif_com_crop_5_, "20 reps"));
        exerciseList.add(new FitnessExercise("Exercise 5", "Description 5", R.drawable.ezgif_com_crop_6_, "18 reps"));
        exerciseList.add(new FitnessExercise("Exercise 6", "Description 6", R.drawable.ezgif_com_crop_10_, "15 reps"));
        exerciseList.add(new FitnessExercise("Exercise 7", "Description 7", R.drawable.ezgif_com_crop_9_, "12 reps"));
        exerciseList.add(new FitnessExercise("Exercise 8", "Description 8", R.drawable.ezgif_com_crop_8_, "16 reps"));
        exerciseList.add(new FitnessExercise("Exercise 9", "Description 9", R.drawable.ezgif_com_crop_7_, "14 reps"));
        exerciseList.add(new FitnessExercise("Exercise 10", "Description 10", R.drawable.ezgif_com_crop_12_, "15 reps"));
        exerciseList.add(new FitnessExercise("Exercise 11", "Description 11", R.drawable.ezgif_com_crop_22_, "12 reps"));
        exerciseList.add(new FitnessExercise("Exercise 12", "Description 12", R.drawable.ezgif_com_crop_20_, "13 reps"));
        exerciseList.add(new FitnessExercise("Exercise 13", "Description 13", R.drawable.ezgif_com_crop_24_, "10 reps"));
        exerciseList.add(new FitnessExercise("Exercise 14", "Description 14", R.drawable.ezgif_com_crop_25_, "15 reps"));
        exerciseList.add(new FitnessExercise("Exercise 15", "Description 15", R.drawable.ezgif_com_crop_26_, "20 reps"));

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
                startButton.setVisibility(View.GONE);
                statusBar.setVisibility(View.VISIBLE);
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
            timerTextView.setText("Workout Done STATS UPDATED");

            // Start the mygym activity
            Intent intent = new Intent(this, mygym.class);
            startActivity(intent);

            // Finish the current activity (WorkoutActivity)
            finish();
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
            // Update the status text
            statusTextView.setText("Exercise " + (currentExerciseIndex + 1) + " of " + exerciseList.size());
        } else {
            isWorkoutRunning = false;
            timerTextView.setText("Workout Done");
            // Update the status text when the workout is finished
            statusTextView.setText("Workout Finished");
            timerTextView.setText("Workout Done STATS UPDATED");


                Intent intent = new Intent(this, mygym.class);
                startActivity(intent);
                finish();
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
