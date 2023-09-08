package com.example.fitnessnearme;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ExercisePlanActivity extends AppCompatActivity {

    private RecyclerView exerciseRecyclerView;
    private ExerciseAdapter exerciseAdapter;
    private List<Exercise> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_exercise_plan);

        // Initialize RecyclerView
        exerciseRecyclerView = findViewById(R.id.exerciseRecyclerView);
        exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the exercise list
        exerciseList = new ArrayList<>();

        // Set up ExerciseAdapter
        exerciseAdapter = new ExerciseAdapter(exerciseList);
        exerciseRecyclerView.setAdapter(exerciseAdapter);

        // Fetch exercise data from PHP script
        fetchExerciseData();
    }

    private void fetchExerciseData() {
        String url = "https://fitnessnearmee.000webhostapp.com/exercise.php";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject exerciseJson = response.getJSONObject(i);

                                String name = exerciseJson.getString("name");
                                String description = exerciseJson.getString("description");
                                String imageUrl = exerciseJson.getString("image_url");
                                String repRange = exerciseJson.getString("rep_range");
                                int gifResource = R.drawable.exercise1; // Replace with the appropriate GIF resource
                                exerciseList.add(new Exercise(name, description, imageUrl, repRange, gifResource));

                            }

                            // Notify the adapter that the data has changed
                            exerciseAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        // Handle error case
                    }
                });

        // Add the request to the RequestQueue
        requestQueue.add(request);
    }
}
