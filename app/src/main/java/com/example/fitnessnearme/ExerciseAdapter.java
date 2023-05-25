package com.example.fitnessnearme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private List<Exercise> exerciseList;

    public ExerciseAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);

        holder.exerciseImageView.setImageResource(exercise.getImageResId());
        holder.exerciseNameTextView.setText(exercise.getName());
        holder.exerciseDescriptionTextView.setText(exercise.getDescription());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        ImageView exerciseImageView;
        TextView exerciseNameTextView;
        TextView exerciseDescriptionTextView;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseImageView = itemView.findViewById(R.id.exerciseImageView);
            exerciseNameTextView = itemView.findViewById(R.id.exerciseNameTextView);
            exerciseDescriptionTextView = itemView.findViewById(R.id.exerciseDescriptionTextView);
        }
    }
}
