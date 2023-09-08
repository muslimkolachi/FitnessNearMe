package com.example.fitnessnearme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class trysomethingnew extends AppCompatActivity {
    ImageView facebookIcon;
    ImageView twitterIcon;
    ImageView instagramIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trysomethingnew);

        ImageView mappyImageView = findViewById(R.id.mappy);
        ImageView steppyImageView = findViewById(R.id.steppy);
        ImageView BMIImageView=findViewById(R.id.bmi);
        facebookIcon = findViewById(R.id.facebookIcon);
        twitterIcon = findViewById(R.id.twitterIcon);
        instagramIcon = findViewById(R.id.instagramIcon);
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

    }






        public void onFacebookIconClicked (View view){
            String facebookUrl = "https://instagram.com/weare.responsible?utm_source=qr&igshid=OGU0MmVlOWVjOQ==";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
            startActivity(intent);
        }

        public void onTwitterIconClicked (View view){
            String twitterUrl = "https://instagram.com/weare.responsible?utm_source=qr&igshid=OGU0MmVlOWVjOQ==";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
            startActivity(intent);
        }

        public void onInstagramIconClicked (View view){
            String instagramUrl = "https://www.youtube.com/@dapoorking";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl));
            startActivity(intent);
        }
    }


