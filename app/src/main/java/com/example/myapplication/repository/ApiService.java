package com.example.myapplication.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static UsersAPI mApiService = null;
    public static UsersAPI getInstance() {
        if (mApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mApiService = retrofit.create(UsersAPI.class);
        }
        return mApiService;
    }
}
