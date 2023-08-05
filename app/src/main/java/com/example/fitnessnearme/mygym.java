package com.example.fitnessnearme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class mygym extends AppCompatActivity {
    ImageView facebookIcon;
    ImageView twitterIcon;
    ImageView instagramIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mygym);

        // Setup the LineChart
        LineChart lineChart = findViewById(R.id.line_chart);
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(182, 84));  // Height: 182, Weight: 84
        entries.add(new Entry(182, 85));  // Height: 182, Weight: 85
        entries.add(new Entry(182, 83));  // Height: 182, Weight: 83
        entries.add(new Entry(182, 81));  // Height: 182, Weight: 81
        entries.add(new Entry(182, 79));  // Height: 182, Weight: 79
        entries.add(new Entry(182, 78));  // Height: 182, Weight: 78
        entries.add(new Entry(182, 76));  // Height: 182, Weight: 76

        LineDataSet dataSet = new LineDataSet(entries, "Weight Change Over Height");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);

        // Customize the X-axis
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);  // Display height as integer
            }
        });

        // Customize the Y-axis
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);  // Display weight as integer
            }
        });

        lineChart.invalidate();

        // Setup the ImageViews
        facebookIcon = findViewById(R.id.facebookIcon);
        twitterIcon = findViewById(R.id.twitterIcon);
        instagramIcon = findViewById(R.id.instagramIcon);
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
        // Open the camera here
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    public void onExercisePlanClicked(View view) {
        Intent intent = new Intent(this, ExercisePlanActivity.class);
        startActivity(intent);
    }
}
