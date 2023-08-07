package com.example.fitnessnearme;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class giftesting extends AppCompatActivity {
    ImageView gifImageView; // Declare the ImageView here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftesting);


        gifImageView = findViewById(R.id.gifImageView);

        Glide.with(this)
                .asGif()
                .load(R.drawable.testing)
                .into(gifImageView);
    }
}
