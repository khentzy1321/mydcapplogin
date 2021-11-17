package com.example.mydcapp.api;

import com.example.mydcapp.mine.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestPlaceHolder {

    @POST("login")
    Call<Login> login(@Body Login login);
}
