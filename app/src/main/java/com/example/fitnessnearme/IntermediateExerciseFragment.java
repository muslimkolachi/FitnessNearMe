package com.example.fitnessnearme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class IntermediateExerciseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise, container, false);

        // Create a list of Intermediate exercises
        List<Exercise> intermediateExercises = new ArrayList<>();
        intermediateExercises.add(new Exercise("Intermediate Exercise 1"));
        intermediateExercises.add(new Exercise("Intermediate Exercise 2"));
        // Add more intermediate exercises as needed

        // Initialize and set up the RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.exerciseRecyclerView);
        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(intermediateExercises);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }
}
