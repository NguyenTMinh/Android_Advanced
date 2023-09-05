package com.example.myapplication.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private static Retrofit retrofit;

    public static PrintfulService getService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        }

        return retrofit.create(PrintfulService.class);
    }
}
