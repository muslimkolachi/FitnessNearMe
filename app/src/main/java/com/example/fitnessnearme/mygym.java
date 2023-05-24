package com.example.fitnessnearme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class mygym extends AppCompatActivity {
    ImageView facebookIcon;
    ImageView twitterIcon;
    ImageView instagramIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygym);
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
}