package com.example.myfavorite;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bara.submission1.models.FavoriteEntity;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView rvFavorite;
    ProviderAdapter adapterFavorite;
    private static final int LOADER_FAV=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        rvFavorite = findViewById(R.id.rv_favorite);

        rvFavorite.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapterFavorite = new ProviderAdapter();
        rvFavorite.setAdapter(adapterFavorite);

        getSupportLoaderManager().initLoader(LOADER_FAV, null, mLoaderCallback);

    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {
        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
            switch (id){
                case LOADER_FAV:
                    return new CursorLoader(getApplicationContext(),
                            Uri.parse("content://" + "com.bara.submission1.contentprovider" + "/" + FavoriteEntity.TAB_NAME),
                            new String[]{"name"},
                            null,null,null);
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
            switch (loader.getId()) {
                case LOADER_FAV:
                    adapterFavorite.setData(data);
                    break;
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            switch (loader.getId()) {
                case LOADER_FAV:
                    adapterFavorite.setData(null);
                    break;
            }
        }
    };

}