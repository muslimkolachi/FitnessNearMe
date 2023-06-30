package com.example.fitnessnearme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class events extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Event> eventList;
    private EventAdapter eventAdapter;

    ImageView facebookIcon;
    ImageView twitterIcon;
    ImageView instagramIcon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        recyclerView = findViewById(R.id.eventsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventList = new ArrayList<>();
        eventAdapter = new EventAdapter(eventList);
        recyclerView.setAdapter(eventAdapter);

        facebookIcon = findViewById(R.id.facebookIcon);
        twitterIcon = findViewById(R.id.twitterIcon);
        instagramIcon = findViewById(R.id.instagramIcon);

        facebookIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSocialMediaLink("https://www.youtube.com/watch?v=5T1_PWX6odY");
            }
        });

        twitterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSocialMediaLink("https://www.youtube.com/watch?v=5upQGvf22qA");
            }
        });

        instagramIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSocialMediaLink("https://www.youtube.com/watch?v=o6jZ7IFt6mQ");
            }
        });

        // Fetch events data from the PHP script
        fetchEventsData();
    }

    private void fetchEventsData() {
        // Start an asynchronous task to fetch the events data
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // URL of the PHP script
                    URL url = new URL("https://fitnessnearmee.000webhostapp.com/events.php");

                    // Open a connection to the URL
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    // Read the response from the connection
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();

                    // Parse the JSON response and add events to the eventList
                    parseEventsResponse(stringBuilder.toString());

                    // Notify the adapter of the data change
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            eventAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseEventsResponse(String jsonResponse) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonResponse);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int eventId = jsonObject.getInt("event_id");
            String title = jsonObject.getString("title");
            String date = jsonObject.getString("date");
            String venue = jsonObject.getString("venue");
            double price = jsonObject.getDouble("price");
            String image = jsonObject.getString("image");

            // Create an Event object and add it to the eventList
            Event event = new Event(eventId, title, date, venue, image, price);
            eventList.add(event);
        }
    }

    private void openSocialMediaLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
