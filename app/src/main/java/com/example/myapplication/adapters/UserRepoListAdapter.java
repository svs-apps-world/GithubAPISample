package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.UserRepoListListener;
import com.example.myapplication.repository.models.UserRepo;

import java.util.List;

public class UserRepoListAdapter extends RecyclerView.Adapter<UserRepoListAdapter.ItemViewHolder> {

    private final Context mContext;
    private List<UserRepo> mUserRepoList;

    private UserRepoListListener mUserRepoListListener;

    public UserRepoListAdapter(@NonNull Context context, @NonNull UserRepoListListener userRepoListListener, @NonNull List<UserRepo> userRepoList) {
        this.mContext = context;
        this.mUserRepoList = userRepoList;
        this.mUserRepoListListener = userRepoListListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user_repo, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        UserRepo userRepo = mUserRepoList.get(position);
        holder.bind(userRepo, mUserRepoListListener);
    }

    @Override
    public int getItemCount() {
        return mUserRepoList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView titleView, descriptionView;
        ImageView starImage;
        View mItemView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.repo_title);
            descriptionView = itemView.findViewById(R.id.repo_description);
            starImage = itemView.findViewById(R.id.star_image);
            mItemView = itemView;
        }

        public void bind(UserRepo userRepo, UserRepoListListener listListener) {
            titleView.setText(userRepo.getName());
            descriptionView.setText(userRepo.getDescription());
            mItemView.setOnClickListener(v -> {
                listListener.onRepoSelected(userRepo);
            });
            if (userRepo.getStargazers_count() >= 5000) {
                starImage.setVisibility(View.VISIBLE);
            } else {
                starImage.setVisibility(View.GONE);
            }
        }
    }

    public List<UserRepo> getUserRepoList() {
        return mUserRepoList;
    }

    public void setUserRepoList(List<UserRepo> mUserRepoList) {
        this.mUserRepoList = mUserRepoList;
        notifyDataSetChanged();
    }
}
