package com.bara.submission1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.bara.submission1.adapter.RecyclerAdapter;
import com.bara.submission1.models.Data;
import com.bara.submission1.models.Users;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private ProgressBar progressBar;
    RecyclerAdapter adapter;
//    private ArrayList<Data> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        searchView = findViewById(R.id.searchViewMain);
        progressBar = findViewById(R.id.progressBarMain);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new RecyclerAdapter();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        loading(false);
        searching();
    }

    public void loading(Boolean bool){
        if (bool){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void searching(){
        SearchManager searchManager = (SearchManager) getSystemService(this.SEARCH_SERVICE);
        if (searchManager != null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (query != null){
                        loading(true);
                        setUsersSearch(query);
                    }return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
    }

    private void setUsersSearch(final String name){
        final ArrayList<Users> listUser= new ArrayList<>();

        String url = "https://api.github.com/search/users?q=" + name;

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "878ad115c123a3817f8034f04707fd7f2ab37461");
        client.addHeader("User-Agent", "request");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject reJsonObject = new JSONObject(result);
                    JSONArray list = reJsonObject.getJSONArray("items");

                    for (int i = 0; i<list.length(); i++){
                        JSONObject user = list.getJSONObject(i);
                        Users usersNew = new Users();
                        usersNew.setName(user.getString("login"));
                        usersNew.setType(user.getString("type"));
                        usersNew.setAvatar(user.getString("avatar_url"));
                        listUser.add(usersNew);
                    }
                    adapter.setmData(listUser);
                    loading(false);

                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                    Toast.makeText(MainActivity.this, "eror", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Error", error.getMessage());
                Toast.makeText(MainActivity.this, "gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void favButton(View view) {
        Intent i = new Intent(this, FavoriteActivity.class);
        startActivity(i);
    }

    public void settingButton(View view) {
        Intent i = new Intent(this, AlarmActivity.class);
        startActivity(i);
    }
}
