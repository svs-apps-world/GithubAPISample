package com.example.myapplication.repository;

import com.example.myapplication.repository.models.UserDetails;
import com.example.myapplication.repository.models.UserRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsersAPI {
    @GET("users/{userId}")
    Call<UserDetails> getUserDetails(@Path("userId") String userId);

    @GET("users/{userId}/repos")
    Call<List<UserRepo>> getUserRepos(@Path("userId") String userId);
}
