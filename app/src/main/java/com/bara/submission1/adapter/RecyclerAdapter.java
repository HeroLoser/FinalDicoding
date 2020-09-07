package com.bara.submission1.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bara.submission1.DetailSub2Activity;
import com.bara.submission1.R;
import com.bara.submission1.models.Users;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public ArrayList<Users> mData = new ArrayList<>();

    public void setmData(ArrayList<Users> users){
        mData.clear();
        mData.addAll(users);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarList;
        TextView nameList;
        TextView typeList;
        ConstraintLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarList = itemView.findViewById(R.id.img_avatar_item);
            nameList = itemView.findViewById(R.id.txt_name_item);
            typeList = itemView.findViewById(R.id.txt_location_item);
            linearLayout = itemView.findViewById(R.id.constraint_layout);
        }

        void bind(final Users users){
            nameList.setText(users.getName());
            typeList.setText(users.getType());
//            avatarList.setImageURI(Uri.parse(users.getAvatar()));
            Glide.with(itemView.getContext())
                    .load(users.getAvatar())
                    .into(avatarList);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("name", users.getName());
                    Intent i = new Intent(v.getContext(), DetailSub2Activity.class);
                    i.putExtras(bundle);
                    v.getContext().startActivity(i);
                }
            });

        }
    }
}
