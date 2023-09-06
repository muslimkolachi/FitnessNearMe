package com.example.fitnessnearme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class mygym extends AppCompatActivity {
    ImageView facebookIcon;
    ImageView twitterIcon;
    ImageView instagramIcon;
    CardView bmiCard;
    TextView bmiTextView;
    TextView bmiCategoryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mygym);

        LineChart lineChart = findViewById(R.id.line_chart);

        // Generate random hard-coded data for calories burned on each day of the week
        List<Entry> entries = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            entries.add(new Entry(i, random.nextInt(500) + 1000)); // Random calories between 1000 and 1500
        }

        LineDataSet dataSet = new LineDataSet(entries, "Calories Burned");
        dataSet.setColor(Color.RED);
        dataSet.setLineWidth(2f);
        dataSet.setValueTextSize(16f);
        dataSet.setCircleRadius(5f);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"}));
        dataSet.setValueTextSize(16f);
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        lineChart.invalidate();

        facebookIcon = findViewById(R.id.facebookIcon);
        twitterIcon = findViewById(R.id.twitterIcon);
        instagramIcon = findViewById(R.id.instagramIcon);

        bmiCard = findViewById(R.id.bmiCard);
        bmiTextView = findViewById(R.id.bmiTextView);
        bmiCategoryTextView = findViewById(R.id.bmiCategoryTextView);

        // Sample BMI calculation
        float userWeight = 70.0f; // Replace with actual user weight
        float userHeight = 170.0f; // Replace with actual user height
        float userHeightMeters = userHeight / 100.0f;
        float bmi = userWeight / (userHeightMeters * userHeightMeters);

        bmiTextView.setText(String.format("%.2f", bmi));

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

    public void onFacebookIconClicked(View view) {
        String facebookUrl = "https://www.facebook.com"; // Replace with your Facebook URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
        startActivity(intent);
    }

    public void onTwitterIconClicked(View view) {
        String twitterUrl = "https://www.twitter.com"; // Replace with your Twitter URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
        startActivity(intent);
    }

    public void onInstagramIconClicked(View view) {
        String instagramUrl = "https://www.instagram.com"; // Replace with your Instagram URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl));
        startActivity(intent);
    }

    public void onAuthenticatorClicked(View view) {
        Intent intent = new Intent(this, QRScan.class); // Replace with your QRScan activity
        startActivity(intent);
    }

    public void onExercisePlanClicked(View view) {
        Intent intent = new Intent(this, WorkoutActivity.class); // Replace with your WorkoutActivity
        startActivity(intent);
    }
    public void onMealPlanClicked(View view) {
        Intent intent = new Intent(this, ExercisePlanActivity.class); // Replace with your WorkoutActivity
        startActivity(intent);
    }
}
