package com.bara.submission1.models;

import android.content.ContentValues;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = FavoriteEntity.TAB_NAME)
public class FavoriteEntity {

    public static final String TAB_NAME = "favorite";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_AVATAR = "avatar";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(index = true, name = COLUMN_ID)
    private int id;

    @ColumnInfo(name = COLUMN_NAME)
    private String name;

    @ColumnInfo(name = COLUMN_TYPE)
    private String type;

    @ColumnInfo(name = COLUMN_AVATAR)
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
        if (values.containsKey(COLUMN_NAME)) {
            entity.name = values.getAsString(COLUMN_NAME);
        }
        if (values.containsKey(COLUMN_TYPE)) {
            entity.type = values.getAsString(COLUMN_TYPE);
        }
        if (values.containsKey(COLUMN_AVATAR)){
            entity.avatar = values.getAsString(COLUMN_AVATAR);
        }
        return entity;
    }
}
