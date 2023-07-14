package com.example.fitnessnearme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LocationUtils {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    public static boolean hasLocationPermission(Context context) {
        int permissionState = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestLocationPermission(AppCompatActivity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Explain why location permission is needed
            Toast.makeText(activity, "Location permission is required for this app", Toast.LENGTH_SHORT).show();
        } else {
            // Request the permission
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    public static void handleLocationPermissionResult(int requestCode, int[] grantResults, AppCompatActivity activity) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted
                Toast.makeText(activity, "Location permission granted", Toast.LENGTH_SHORT).show();
            } else {
                // Location permission denied, show a message or take appropriate action
                Toast.makeText(activity, "Location permission denied", Toast.LENGTH_SHORT).show();
                // Optionally, you can open the app settings page for the user to enable location permission manually
                openAppSettings(activity);
            }
        }
    }

    private static void openAppSettings(AppCompatActivity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }
}
