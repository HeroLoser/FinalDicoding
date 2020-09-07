package com.example.myfavorite;

import android.content.ContentValues;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "favorite")
public class FavoriteEntity {

    public static final String TAB_NAME = "favorite";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "nama")
    private String name;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "avatar")
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static FavoriteEntity fromContentValues(ContentValues values) {
        final FavoriteEntity entity = new FavoriteEntity();
        if (values.containsKey("name")) {
            entity.name = values.getAsString("name");
        }
        if (values.containsKey("type")) {
            entity.type = values.getAsString("type");
        }
        if (values.containsKey("avatar")){
            entity.avatar = values.getAsString("avatar");
        }
        return entity;
    }
}
