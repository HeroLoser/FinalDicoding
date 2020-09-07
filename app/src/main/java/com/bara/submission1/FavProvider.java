package com.bara.submission1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.bara.submission1.database.AppDatabase;
import com.bara.submission1.database.FavoriteDao;
import com.bara.submission1.models.FavoriteEntity;

public class FavProvider extends ContentProvider {

    public static final String AUTHORITY = "com.bara.submission1.contentprovider";
    public static final  Uri URI_FAV = Uri.parse("content://" + AUTHORITY + "/" + FavoriteEntity.TAB_NAME);
    private static final int FAV_DIR = 1;
    private static final int FAV_ITEM = 2;
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private FavoriteDao favoriteDao;

    static {
        MATCHER.addURI(AUTHORITY, FavoriteEntity.TAB_NAME, FAV_DIR);
        MATCHER.addURI(AUTHORITY, FavoriteEntity.TAB_NAME + "/*", FAV_ITEM);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new IllegalArgumentException("Unknown URi: " + uri);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        favoriteDao = AppDatabase.getInstance(getContext())
                .favoriteDao();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        final int code = MATCHER.match(uri);

        Cursor cursor;
        if (code == FAV_DIR) {
            cursor = favoriteDao.getCursorAll();
            if (getContext() != null) {
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
        }
        else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return cursor;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
