package com.bara.submission1.adapter;

import android.content.Context;
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
import androidx.room.Room;

import com.bara.submission1.DetailSub2Activity;
import com.bara.submission1.R;
import com.bara.submission1.database.AppDatabase;
import com.bara.submission1.models.FavoriteEntity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    public ArrayList<FavoriteEntity> mData;
    public Context mContex;


    public FavoriteAdapter(ArrayList<FavoriteEntity> mData, Context mContex) {
        this.mData = mData;
        this.mContex = mContex;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bind(mData.get(position));
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AppDatabase database;
                database = Room.databaseBuilder(mContex,
                        AppDatabase.class, "favorite").allowMainThreadQueries().build();
                database.favoriteDao().delUser(mData.get(position));
                mData.remove(mData.get(position));
                notifyItemRemoved(position);
                notifyDataSetChanged();
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, type;
        private ImageView avatar;
        private ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name_fav);
            type = itemView.findViewById(R.id.txt_type_fav);
            avatar = itemView.findViewById(R.id.img_avatar_fav);
            layout = itemView.findViewById(R.id.constraint_layout_fav);
        }

        void bind(final FavoriteEntity favoriteEntity){
            name.setText(favoriteEntity.getName());
            type.setText(favoriteEntity.getType());
            Glide.with(itemView.getContext())
                    .load(favoriteEntity.getAvatar())
                    .into(avatar);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("name", favoriteEntity.getName());
                    Intent i = new Intent(v.getContext(), DetailSub2Activity.class);
                    i.putExtras(bundle);
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
