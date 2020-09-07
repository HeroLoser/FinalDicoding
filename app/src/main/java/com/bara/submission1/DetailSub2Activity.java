package com.bara.submission1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.bara.submission1.adapter.TabAdapter;
import com.bara.submission1.database.AppDatabase;
import com.bara.submission1.models.FavoriteEntity;
import com.bara.submission1.models.UserDetail;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetailSub2Activity extends AppCompatActivity {

    TextView userName, follower, following, repository;
    ImageView avatar;
    TabAdapter tabAdapter;
    FloatingActionButton fabFavorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sub2);

        userName = findViewById(R.id.NamaDetailSub2);
        follower = findViewById(R.id.countFollowersSub2);
        following = findViewById(R.id.countFollowingSub2);
        repository = findViewById(R.id.countRepoSub2);
        avatar = findViewById(R.id.img_avatar_detail_sub2);
        fabFavorite = findViewById(R.id.fab_detail);

        tabAdapter = new TabAdapter(this, getSupportFragmentManager());
        ViewPager viewPager2 = findViewById(R.id.viewPagerDetail);
        viewPager2.setAdapter(tabAdapter);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager2);

        if (getIntent() != null){
            String nameExtra = getIntent().getStringExtra("name");
            tabAdapter.getName(nameExtra);
            getDetail(nameExtra);
        }
    }

    public void backButton(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finishAffinity();
    }

    private void getDetail(String nama){
        String url = "https://api.github.com/users/" + nama;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "878ad115c123a3817f8034f04707fd7f2ab37461");
        client.addHeader("User-Agent", "request");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);


                    FavoriteEntity favoriteEntity = new FavoriteEntity();
                    UserDetail userDetail = new UserDetail();

                    userDetail.setName(jsonObject.getString("name"));
                    userDetail.setAvatar(jsonObject.getString("avatar_url"));
                    userDetail.setRepository(jsonObject.getString("public_repos"));
                    userDetail.setFollowers(jsonObject.getString("followers"));
                    userDetail.setFollowing(jsonObject.getString("following"));

                    favoriteEntity.setName(jsonObject.getString("login"));
                    favoriteEntity.setAvatar(jsonObject.getString("avatar_url"));
                    favoriteEntity.setType(jsonObject.getString("type"));
                    setComponent(userDetail);
                    addFav(favoriteEntity);
                } catch (Exception e) {
                    Log.d("Error", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Failure", error.getMessage());
            }
        });
    }

    private void setComponent(UserDetail userDetail){
        userName.setText(userDetail.getName());
        follower.setText(userDetail.getFollowers());
        following.setText(userDetail.getFollowing());
        repository.setText(userDetail.getRepository());
        Glide.with(this)
                .load(userDetail.getAvatar())
                .into(avatar);
    }

    private void addFav(final FavoriteEntity favoriteEntity){
        final AppDatabase database;
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "favorite").allowMainThreadQueries().build();

        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.favoriteDao().addUser(favoriteEntity);
                Toast.makeText(DetailSub2Activity.this, "Add to Favorite", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



