package com.bara.submission1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bara.submission1.R;
import com.bara.submission1.models.UserFollowing;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewHolder> {

    ArrayList<UserFollowing> mData = new ArrayList<>();
    public void setmData(ArrayList<UserFollowing> userFollowings){
        mData.clear();
        mData.addAll(userFollowings);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_following, parent, false);
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
        TextView name, type;
        ImageView avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name_item_following);
            type = itemView.findViewById(R.id.txt_location_item_following);
            avatar = itemView.findViewById(R.id.img_avatar_item_following);
        }

        void bind(UserFollowing userFollowing){
            name.setText(userFollowing.getName());
            type.setText(userFollowing.getType());
            Glide.with(itemView.getContext())
                    .load(userFollowing.getAvatar())
                    .into(avatar);
        }
    }
}
