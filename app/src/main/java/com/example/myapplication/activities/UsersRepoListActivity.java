package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.adapters.UserRepoListAdapter;
import com.example.myapplication.constants.UserRepoConstants;
import com.example.myapplication.interfaces.UserRepoListListener;
import com.example.myapplication.repository.models.UserDetails;
import com.example.myapplication.repository.models.UserRepo;
import com.example.myapplication.viewmodel.UsersRepoListViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;

public class UsersRepoListActivity extends AppCompatActivity implements UserRepoListListener {

    UsersRepoListViewModel mViewModel;

    AppCompatButton mSubmitButton;
    TextInputEditText mInputText;
    TextView mUserName;

    ImageView mUserAvatar;
    RecyclerView mRecyclerview;
    UserRepoListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_repo_list);
        mSubmitButton = findViewById(R.id.submit_button);
        mInputText = findViewById(R.id.input_text);
        mUserName = findViewById(R.id.user_name);
        mUserAvatar = findViewById(R.id.user_avatar);
        mRecyclerview = findViewById(R.id.user_repo_recycler_view);
        mAdapter = new UserRepoListAdapter(this, this, new ArrayList<>());
        mRecyclerview.setAdapter(mAdapter);
        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.
        mViewModel = new ViewModelProvider(this).get(UsersRepoListViewModel.class);

        addTextChangeListener();

        addSubmitButtonListener();
    }

    private void addTextChangeListener() {
        mInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mSubmitButton.setEnabled(!s.toString().isEmpty());
            }
        });
    }

    private void addSubmitButtonListener() {
        mSubmitButton.setOnClickListener(v -> {
            if (mInputText.getText() == null) {
                return;
            }
            String userId = mInputText.getText().toString();
            if (userId.isEmpty()) {
                mInputText.setError(getString(R.string.enter_a_github_user_id));
            } else {
                mViewModel.getUserDetails(userId);
                mViewModel.getUserRepos(userId);
                addUserDetailAvailableObserver();
            }
        });
    }

    private void addUserDetailAvailableObserver() {
        mViewModel.getUserDetailsLiveData().observe(this, userDetails -> {
            if (userDetails != null && userDetails.getName() != null) {
                mUserName.setText(userDetails.getName());
            } else {
                mUserName.setText(getString(R.string.user_name_unknown));
            }
            String userAvatar = null;
            if (userDetails != null && userDetails.getAvatar_url() != null) {
                userAvatar = userDetails.getAvatar_url();
            }
            loadUserAvatar(userAvatar);
        });

        mViewModel.getUserReposLiveData().observe(this, userRepos -> {
            if (userRepos != null) {
                mAdapter.setUserRepoList(userRepos);
            } else {
                mAdapter.setUserRepoList(new ArrayList<>());
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        addUserDetailAvailableObserver();
    }

    private void loadUserAvatar(String avatarUrl) {
        Glide.with(this)
                .load(avatarUrl)
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_user_avatar_placeholder) // Placeholder image
                        .error(R.mipmap.ic_user_avatar_placeholder) // Error image in case of loading failure
                )
                .into(mUserAvatar);
    }

    @Override
    public void onRepoSelected(@NonNull UserRepo userRepo) {
        Gson gson = new Gson();
        String json = gson.toJson(userRepo); // serializes target to Json
        Intent intent = new Intent(this, RepoDetailsActivity.class);
        intent.putExtra(UserRepoConstants.USER_DETAILS, json);
        startActivity(intent);
    }
}