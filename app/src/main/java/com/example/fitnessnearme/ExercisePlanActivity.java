package com.example.fitnessnearme;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExercisePlanActivity extends AppCompatActivity {

    private RecyclerView exerciseRecyclerView;
    private ExerciseAdapter exerciseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_plan);

        // Initialize RecyclerView
        exerciseRecyclerView = findViewById(R.id.exerciseRecyclerView);
        exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up exercise data
        List<Exercise> exerciseList = generateExerciseData();

        // Set up ExerciseAdapter
        exerciseAdapter = new ExerciseAdapter(exerciseList);
        exerciseRecyclerView.setAdapter(exerciseAdapter);
    }

    private List<Exercise> generateExerciseData() {
        List<Exercise> exerciseList = new ArrayList<>();

        // Add sample exercises
        exerciseList.add(new Exercise("Jumping Jacks 12x8", "Description 1", R.drawable._3837_jumping_jack));
        exerciseList.add(new Exercise("Exercise 2", "Description 2", R.drawable._3837_jumping_jack));
        exerciseList.add(new Exercise("Exercise 3", "Description 3", R.drawable._ef5b5_be04454269d4483897bc791495770f0b));
        // Add more exercises as needed

        return exerciseList;
    }
}
