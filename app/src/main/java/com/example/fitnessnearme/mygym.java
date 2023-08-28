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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

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

        BarChart barChart = findViewById(R.id.bar_chart);

        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
        float userWeight = preferences.getFloat(Constants.KEY_USER_WEIGHT, 0.0f);
        float userHeight = preferences.getFloat(Constants.KEY_USER_HEIGHT, 0.0f);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, userWeight));
        barEntries.add(new BarEntry(1, userHeight));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Weight and Height");
        barDataSet.setColors(new int[]{Color.BLUE, Color.GREEN});
        barDataSet.setValueTextSize(12f);

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Weight", "Height"}));

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });

        barChart.invalidate();

        facebookIcon = findViewById(R.id.facebookIcon);
        twitterIcon = findViewById(R.id.twitterIcon);
        instagramIcon = findViewById(R.id.instagramIcon);

        bmiCard = findViewById(R.id.bmiCard);
        bmiTextView = findViewById(R.id.bmiTextView);
        bmiCategoryTextView = findViewById(R.id.bmiCategoryTextView);

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
        String facebookUrl = "https://www.youtube.com/watch?v=5T1_PWX6odY";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
        startActivity(intent);
    }

    public void onTwitterIconClicked(View view) {
        String twitterUrl = "https://www.youtube.com/watch?v=5upQGvf22qA";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
        startActivity(intent);
    }

    public void onInstagramIconClicked(View view) {
        String instagramUrl = "https://www.youtube.com/watch?v=o6jZ7IFt6mQ";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl));
        startActivity(intent);
    }

    public void onAuthenticatorClicked(View view) {
        Intent intent = new Intent(this, QRScan.class);
        startActivity(intent);
    }

    public void onExercisePlanClicked(View view) {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }
}
