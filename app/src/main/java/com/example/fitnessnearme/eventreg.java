package com.example.fitnessnearme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class eventreg extends AppCompatActivity {

    private Spinner eventSpinner;
    private Button registerButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventreg);

        eventSpinner = findViewById(R.id.eventSpinner);
        registerButton = findViewById(R.id.registerButton);

        // Retrieve event titles from the intent
        List<String> eventTitles = getIntent().getStringArrayListExtra("eventTitles");

        // Check if eventTitles is not empty and set up the Spinner
        if (eventTitles != null && !eventTitles.isEmpty()) {
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, eventTitles);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            eventSpinner.setAdapter(spinnerAdapter);
        } else {
            // Handle the case where eventTitles is empty or null
            Toast.makeText(this, "No events available for registration.", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if there are no events
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected event from the Spinner
                String selectedEvent = eventSpinner.getSelectedItem().toString();

                // Implement your registration logic here (e.g., send data to a server, store locally)

                // Display a confirmation message
                Toast.makeText(eventreg.this, "Registered for: " + selectedEvent, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
