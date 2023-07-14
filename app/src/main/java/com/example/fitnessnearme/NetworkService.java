package com.example.fitnessnearme;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkService {
    @FormUrlEncoded
    @POST("https://fitnessnearmee.000webhostapp.com/login.php")
    Call<LoginResponseModel> login(@Field("username") String email, @Field("password") String password);
}
