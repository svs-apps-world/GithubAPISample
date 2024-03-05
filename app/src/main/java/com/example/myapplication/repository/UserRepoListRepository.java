package com.example.myapplication.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.repository.models.UserDetails;
import com.example.myapplication.repository.models.UserRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepoListRepository {

    private final MutableLiveData<UserDetails> mUserDetailLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<UserRepo>> mUserReposLiveData = new MutableLiveData<>();

    public void requestUserDetails(String userId) {
        Call<UserDetails> call = ApiService.getInstance().getUserDetails(userId);
        call.enqueue(new Callback<UserDetails>() {

            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                mUserDetailLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {

            }
        });
    }

    public void requestUserRepos(String userId) {
        Call<List<UserRepo>> call = ApiService.getInstance().getUserRepos(userId);
        call.enqueue(new Callback<List<UserRepo>>() {
            @Override
            public void onResponse(Call<List<UserRepo>> call, Response<List<UserRepo>> response) {
                mUserReposLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<UserRepo>> call, Throwable t) {

            }
        });

    }

    public MutableLiveData<UserDetails> getUserDetailLiveData() {
        return mUserDetailLiveData;
    }

    public MutableLiveData<List<UserRepo>> getUserReposLiveData() {
        return mUserReposLiveData;
    }
}
