package com.example.myapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.repository.UserRepoListRepository;
import com.example.myapplication.repository.models.UserDetails;
import com.example.myapplication.repository.models.UserRepo;

import java.util.List;

public class UsersRepoListViewModel extends ViewModel {

    UserRepoListRepository mUserRepoListRepository = new UserRepoListRepository();
    private final MutableLiveData<UserDetails> mUserDetailsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<UserRepo>> mUserReposLiveData = new MutableLiveData<>();

    public void getUserDetails(String userId) {
        mUserRepoListRepository.requestUserDetails(userId);
        mUserRepoListRepository.getUserDetailLiveData().observeForever(mUserDetailsLiveData::postValue);
    }

    public void getUserRepos(String userId) {
        mUserRepoListRepository.requestUserRepos(userId);
        mUserRepoListRepository.getUserReposLiveData().observeForever(mUserReposLiveData::postValue);
    }

    public MutableLiveData<UserDetails> getUserDetailsLiveData() {
        return mUserDetailsLiveData;
    }

    public MutableLiveData<List<UserRepo>> getUserReposLiveData() {
        return mUserReposLiveData;
    }
}
