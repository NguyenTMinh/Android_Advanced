package com.example.myapplication.retrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PrintfulService {
    @GET("/users")
    Call<List<User>> getUsers();
}
