package com.example.fitnessnearme;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

import android.Manifest;

public class QRScan extends AppCompatActivity {

    private DecoratedBarcodeView barcodeView;
    private static final int CAMERA_PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);

        // Check for camera permission before initializing the barcode scanner
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            initializeBarcodeScanner();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST);
        }

        Button scanButton = findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (barcodeView != null) {
                    barcodeView.decodeSingle(callback);
                }
            }
        });
    }

    private void initializeBarcodeScanner() {
        barcodeView = findViewById(R.id.barcodeScanner);
        barcodeView.decodeContinuous(callback);
    }

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null) {
                if (result.getText().equalsIgnoreCase("bench")) {
                    navigateToExerciseActivity();
                } else if (result.getText().equalsIgnoreCase("butter")) {
                    navigateToExercise2Activity();
                } else if (result.getText().equalsIgnoreCase("cable")) {
                    navigateToExercise3Activity();
                } else if (result.getText().equalsIgnoreCase("smith")) {
                    navigateToExercise4Activity();
                } else if (result.getText().equalsIgnoreCase("leg")) {
                    navigateToExercise5Activity();
                }
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
            // Optional: Handle possible result points
        }
    };

    private void navigateToExerciseActivity() {
        Intent intent = new Intent(this, workoutR.class);
        startActivity(intent);
        finish(); // Optional: Finish this QRScan activity after navigation
    }

    private void navigateToExercise2Activity() {
        Intent intent = new Intent(this, workout_2.class);
        startActivity(intent);
        finish(); // Optional: Finish this QRScan activity after navigation
    }

    private void navigateToExercise3Activity() {
        Intent intent = new Intent(this, workout_3.class);
        startActivity(intent);
        finish(); // Optional: Finish this QRScan activity after navigation
    }

    private void navigateToExercise4Activity() {
        Intent intent = new Intent(this, workout_4.class);
        startActivity(intent);
        finish(); // Optional: Finish this QRScan activity after navigation
    }

    private void navigateToExercise5Activity() {
        Intent intent = new Intent(this, workout_5.class);
        startActivity(intent);
        finish(); // Optional: Finish this QRScan activity after navigation
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (barcodeView != null) {
            barcodeView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (barcodeView != null) {
            barcodeView.pause();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeBarcodeScanner();
            } else {
                // Handle camera permission denied
            }
        }
    }
}
