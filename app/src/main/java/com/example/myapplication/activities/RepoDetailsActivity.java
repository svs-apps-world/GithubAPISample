package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.constants.UserRepoConstants;
import com.example.myapplication.repository.models.UserRepo;
import com.google.gson.Gson;

import org.w3c.dom.Text;

public class RepoDetailsActivity extends AppCompatActivity {

    private UserRepo mUserRepo;
    private TextView mRepoName, mRepoUrl, mStarCount, mForkCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);

        findViews();
        if (getIntent() != null && getIntent().hasExtra(UserRepoConstants.USER_DETAILS)) {
            String json = getIntent().getStringExtra(UserRepoConstants.USER_DETAILS);
            mUserRepo = new Gson().fromJson(json, UserRepo.class);
        }
        setValues();
    }

    private void setValues() {
        mRepoName.setText(mUserRepo.getName());
        mRepoUrl.setText(mUserRepo.getDescription());
        mStarCount.setText(String.valueOf(mUserRepo.getStargazers_count()));
        mForkCount.setText(String.valueOf(mUserRepo.getForks()));
    }

    private void findViews() {
        mRepoName = findViewById(R.id.repo_name_value);
        mRepoUrl = findViewById(R.id.repo_url_value);
        mStarCount = findViewById(R.id.star_count_value);
        mForkCount = findViewById(R.id.fork_count_value);
    }
}