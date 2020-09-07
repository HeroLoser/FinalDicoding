package com.bara.submission1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bara.submission1.R;
import com.bara.submission1.models.UserFollower;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.ViewHolder> {

    public ArrayList<UserFollower> mData = new ArrayList<>();

    public void setmData(ArrayList<UserFollower> userFollowers){
        mData.clear();
        mData.addAll(userFollowers);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameFollower, typeFollower;
        ImageView avatarFollower;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameFollower = itemView.findViewById(R.id.txt_name_item_follower);
            typeFollower = itemView.findViewById(R.id.txt_location_item_follower);
            avatarFollower = itemView.findViewById(R.id.img_avatar_item_follower);
        }

        void bind(UserFollower userFollower){
            nameFollower.setText(userFollower.getName());
            typeFollower.setText(userFollower.getType());
            Glide.with(itemView.getContext())
                    .load(userFollower.getAvatar())
                    .into(avatarFollower);
        }
    }
}
