package com.bara.submission1.database;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bara.submission1.models.FavoriteEntity;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    List<FavoriteEntity> getList();

    @Query("SELECT * FROM favorite")
    Cursor selectAll();

    @Query("SELECT * FROM " + FavoriteEntity.TAB_NAME)
    Cursor getCursorAll();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void addUser(FavoriteEntity favoriteEntity);

    @Delete
    void delUser(FavoriteEntity favoriteEntity);

//    @Query("DELETE FROM " + "favorite" + " WHERE " + FavoriteEntity.COLUMN_ID + " = :id")
//    int deleteById(long id);

}
