package com.example.fitnessnearme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
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

        holder.exerciseNameTextView.setText(exercise.getName());
        holder.exerciseDescriptionTextView.setText(exercise.getDescription());

        // Load exercise image asynchronously
        new LoadExerciseImageTask(holder.exerciseImageView).execute(exercise.getImageUrl());
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

    private static class LoadExerciseImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageView;

        public LoadExerciseImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(imageUrl).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
