package com.example.myapplication.interfaces;

import androidx.annotation.NonNull;

import com.example.myapplication.repository.models.UserRepo;

public interface UserRepoListListener {
    void onRepoSelected(@NonNull UserRepo userRepo);
}
