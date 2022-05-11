package com.example.zuoye;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BookKeeping extends ContentProvider {

    //声明UriMatcher对象
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    //声明并初始化权限
    private static String authority = "com.example.zuoye.bookKeeping";
    //初始化匹配码
    private static final int USER_CODE = 1;
    //声明数据库
    private DataBase database;
    //声明管理数据库
    private SQLiteDatabase db;

    //当MATCHER调用match()方法时，会进行匹配，并返回相应的自定义匹配码，根据匹配码进行操作
    static {
        MATCHER.addURI(authority, null, USER_CODE);
    }

    @Override
    public boolean onCreate() {
        database = new DataBase(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        db = database.getWritableDatabase();
        return db.query("wp", new String[]{"time","billname","money"}, null, null, null, null, s1);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        db = database.getWritableDatabase();
        db.insert("wp",null,contentValues);
        db.close();
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
