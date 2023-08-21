package com.example.fitnessnearme;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("username")
    private String username;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("weight") // Add this annotation for weight attribute
    private float weight;

    @SerializedName("height") // Add this annotation for height attribute
    private float height;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }
}