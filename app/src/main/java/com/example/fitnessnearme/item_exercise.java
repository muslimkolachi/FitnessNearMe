package com.example.fitnessnearme;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class item_exercise extends AppCompatActivity {

    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_exercise);

        eventList = new ArrayList<>();

        fetchEventData();
    }

    private void fetchEventData() {
        String url = "https://your-api-endpoint.com/events";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject eventJson = response.getJSONObject(i);

                                int eventId = eventJson.getInt("event_id");
                                String title = eventJson.getString("title");
                                String date = eventJson.getString("date");
                                String venue = eventJson.getString("venue");
                                String image = eventJson.getString("image");
                                double price = eventJson.getDouble("price");

                                Event event = new Event(eventId, title, date, venue, image, price);
                                eventList.add(event);
                            }

                            // Do something with the eventList
                            // For example, update your RecyclerView or UI
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(item_exercise.this, "Error fetching event data", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
