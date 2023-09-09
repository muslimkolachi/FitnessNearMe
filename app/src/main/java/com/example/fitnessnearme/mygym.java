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

public class mygym extends AppCompatActivity {
    ImageView facebookIcon;
    ImageView twitterIcon;
    ImageView instagramIcon;
    CardView bmiCard;
    TextView bmiTextView;
    TextView bmiCategoryTextView;
    public static float calculatedBMI = 0.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mygym);
        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
        int exerciseCount = preferences.getInt(Constants.KEY_EXERCISE_COUNT, 0);


        LineChart lineChart = findViewById(R.id.line_chart);


        float userWeight = preferences.getFloat(Constants.KEY_USER_WEIGHT, 0.0f);
        float userHeight = preferences.getFloat(Constants.KEY_USER_HEIGHT, 0.0f);

        ArrayList<Entry> lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(0, userWeight));
        lineEntries.add(new Entry(1, userHeight));

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Weight and Height");
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setCircleColor(Color.BLUE);
        lineDataSet.setValueTextSize(12f);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Weight", "Height"}));

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });

        // Customize chart appearance
        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.setDrawMarkers(true);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        lineChart.invalidate();

        facebookIcon = findViewById(R.id.facebookIcon);
        twitterIcon = findViewById(R.id.twitterIcon);
        instagramIcon = findViewById(R.id.instagramIcon);

        bmiCard = findViewById(R.id.bmiCard);
        bmiTextView = findViewById(R.id.bmiTextView);
        bmiCategoryTextView = findViewById(R.id.bmiCategoryTextView);

        float userHeightMeters = userHeight / 100.0f;
        float bmi = userWeight / (userHeightMeters * userHeightMeters);

         bmiTextView.setText(String.format("%.2f", bmi));
        calculatedBMI = bmi;
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
        String facebookUrl = "https://www.facebook.com/people/Fitness_Near_Me/61551339500367/?mibextid=ZbWKwL";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
        startActivity(intent);
    }

    public void onTwitterIconClicked(View view) {
        String twitterUrl = "https://instagram.com/weare.responsible?utm_source=qr&igshid=OGU0MmVlOWVjOQ==";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
        startActivity(intent);
    }

    public void onInstagramIconClicked(View view) {
        String instagramUrl = "https://www.youtube.com/@dapoorking";
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

    public void onMealPlanClicked(View view) {
        Intent intent = new Intent(this, ExercisePlanActivity.class);
        startActivity(intent);
    }
}
