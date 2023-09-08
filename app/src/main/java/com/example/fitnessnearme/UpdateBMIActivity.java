package com.example.fitnessnearme;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateBMIActivity extends AppCompatActivity {
    EditText heightEditText;
    EditText weightEditText;
    Button updateButton;
    TextView bmiTextView;
    TextView bmiCategoryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_bmiactivity);

        heightEditText = findViewById(R.id.heightEditText);
        weightEditText = findViewById(R.id.weightEditText);
        updateButton = findViewById(R.id.updateButton);
        bmiTextView = findViewById(R.id.bmiTextView);
        bmiCategoryTextView = findViewById(R.id.bmiCategoryTextView);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get height and weight from EditText fields
                float userHeight = Float.parseFloat(heightEditText.getText().toString());
                float userWeight = Float.parseFloat(weightEditText.getText().toString());

                // Calculate BMI
                float userHeightMeters = userHeight / 100.0f;
                float bmi = userWeight / (userHeightMeters * userHeightMeters);

                // Update the BMI TextView with the calculated BMI value and set text color
                bmiTextView.setText(String.format("%.2f", bmi));
                setBmiTextColor(bmi);


                // Update BMI Category
                if (bmi < 18.5) {
                    bmiCategoryTextView.setText("Underweight");
                } else if (bmi >= 18.5 && bmi < 25) {
                    bmiCategoryTextView.setText("Healthy");
                } else {
                    bmiCategoryTextView.setText("Overweight");
                }
            }
        });
    }

    private void setBmiTextColor(float bmi) {
        int textColor;
        if (bmi < 18.5) {
            textColor = getResources().getColor(R.color.colorUnderweight);
        } else if (bmi >= 18.5 && bmi < 25) {
            textColor = getResources().getColor(R.color.colorHealthy);
        } else {
            textColor = getResources().getColor(R.color.colorOverweight);
        }
        bmiTextView.setTextColor(textColor);
    }
}
