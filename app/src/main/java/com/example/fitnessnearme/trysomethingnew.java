package com.example.fitnessnearme;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class trysomethingnew extends AppCompatActivity {

    private LineChart lineChart;
    private ImageView facebookIcon;
    private ImageView twitterIcon;
    private ImageView instagramIcon;
    private ImageView getfit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_trysomethingnew);
        getfit=findViewById(R.id.say1);
        // Initialize the LineChart
        lineChart = findViewById(R.id.line_chart);
        ImageView mappyImageView = findViewById(R.id.mappy);
        ImageView steppyImageView = findViewById(R.id.steppy);
        ImageView BMIImageView=findViewById(R.id.bmi);

        mappyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to MapsActivity
                Intent intent = new Intent(trysomethingnew.this, NearbyGymsActivity.class);
                startActivity(intent);
            }
        });
        BMIImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to StepsActivity
                Intent intent = new Intent(trysomethingnew.this, UpdateBMIActivity.class);
                startActivity(intent);
            }
        });
        steppyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to StepsActivity
                Intent intent = new Intent(trysomethingnew.this, StepCounterActivity.class);
                startActivity(intent);
            }
        });
        getfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to StepsActivity
                Intent intent = new Intent(trysomethingnew.this, CustomExercise.class);
                startActivity(intent);
            }
        });
        // Create data for the next 6 days, including today
        ArrayList<Entry> entries = generateDataForNextWeek();

        // Create a LineDataSet
        LineDataSet dataSet = new LineDataSet(entries, "Weight");
        dataSet.setColor(Color.BLUE);
        dataSet.setCircleColor(Color.BLUE);
        dataSet.setLineWidth(2f);

        // Create a LineData object from the LineDataSet
        LineData lineData = new LineData(dataSet);

        // Set the data to the LineChart
        lineChart.setData(lineData);

        // Configure the X-axis (date)
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new DateAxisValueFormatter()); // Custom X-axis date formatter

        // Configure the Y-axis (weight)
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0); // Set the minimum value for the Y-axis

        // Refresh the chart
        lineChart.invalidate();

        // Initialize the image views
        facebookIcon = findViewById(R.id.facebookIcon);
        twitterIcon = findViewById(R.id.twitterIcon);
        instagramIcon = findViewById(R.id.instagramIcon);
    }

    // Custom X-axis date formatter
    private class DateAxisValueFormatter extends ValueFormatter {
        private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            // Convert the float value (which represents date) to a formatted date string
            long millis = (long) value;
            return sdf.format(new Date(millis));
        }
    }

    // Generate data for the next 6 days, including today
    private ArrayList<Entry> generateDataForNextWeek() {
        ArrayList<Entry> entries = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        long today = calendar.getTimeInMillis();

        for (int i = 0; i <= 6; i++) {
            entries.add(new Entry(today + (i * 86400000L), 65.0f + i)); // Incrementing weight for each day
        }

        return entries;
    }

    // Implement your social media click event handlers here
    public void onFacebookIconClicked(View view) {
        String facebookUrl = "https://www.facebook.com/yourpage";
        openSocialMediaProfile(facebookUrl);
    }

    public void onTwitterIconClicked(View view) {
        String twitterUrl = "https://twitter.com/yourprofile";
        openSocialMediaProfile(twitterUrl);
    }

    public void onInstagramIconClicked(View view) {
        String instagramUrl = "https://www.instagram.com/yourprofile/";
        openSocialMediaProfile(instagramUrl);
    }

    private void openSocialMediaProfile(String profileUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(profileUrl));
        startActivity(intent);
    }

}
