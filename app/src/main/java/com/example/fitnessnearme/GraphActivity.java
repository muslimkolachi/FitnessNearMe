package com.example.fitnessnearme;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // Get user weight and height from SharedPreferences
        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
        float userWeight = preferences.getFloat(Constants.KEY_USER_WEIGHT, 0.0f);
        float userHeight = preferences.getFloat(Constants.KEY_USER_HEIGHT, 0.0f);

        // Calculate BMI using user weight and height
        float userHeightMeters = userHeight / 100.0f;
        float bmi = userWeight / (userHeightMeters * userHeightMeters);

        // Find views
        CardView bmiCard = findViewById(R.id.bmiCard);
        TextView bmiTextView = findViewById(R.id.bmiTextView);
        TextView bmiCategoryTextView = findViewById(R.id.bmiCategoryTextView);

        // Set calculated BMI
        bmiTextView.setText(String.format("%.2f", bmi));

        // Set BMI category and card color based on BMI range
        if (bmi < 18.5) {
            bmiCategoryTextView.setText("Underweight");
            bmiCard.setCardBackgroundColor(getResources().getColor(R.color.yellow));
        } else if (bmi >= 18.5 && bmi < 25) {
            bmiCategoryTextView.setText("Healthy");
            bmiCard.setCardBackgroundColor(getResources().getColor(R.color.green));
        } else {
            bmiCategoryTextView.setText("Overweight");
            bmiCard.setCardBackgroundColor(getResources().getColor(R.color.red));
        }
    }
}
