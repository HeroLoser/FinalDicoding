package com.bara.submission1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bara.submission1.adapter.FavoriteAdapter;
import com.bara.submission1.database.AppDatabase;
import com.bara.submission1.models.FavoriteEntity;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView rvFavorite;
    FavoriteAdapter adapterFavorite;
    ArrayList<FavoriteEntity> entityArrayList = new ArrayList<>();
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        rvFavorite = findViewById(R.id.rv_favorite);

        loadDatabase();

        rvFavorite.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapterFavorite = new FavoriteAdapter(entityArrayList, this);
        rvFavorite.setAdapter(adapterFavorite);

    }

    void loadDatabase() {
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "favorite")
                .allowMainThreadQueries().build();
        entityArrayList = (ArrayList<FavoriteEntity>) database.favoriteDao().getList();
    }

}