package com.example.module_web.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.module_web.public_data.Constant;

/**
 * Created by Administrator on 2018/2/22.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context) {
        super(context, Constant.DB_NAME, null, Constant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Constant.TABLE_NAME_GROUP + "(" + Constant.COLUMN_GROUP_NAME_ID + " integer primary key autoincrement," +
                Constant.COLUMN_GROUP_NAME_KIND + " text," +
                Constant.COLUMN_GROUP_NAME_TYPE + " text)");

        db.execSQL("create table " + Constant.TABLE_NAME_CHILD +
                "(" + Constant.COLUMN_CHILD_NAME_ID + " integer primary key autoincrement," +
                Constant.COLUMN_CHILD_NAME_MODULE + " text," +
                Constant.COLUMN_CHILD_NAME_TYPE + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void onDrop(SQLiteDatabase db,String tableName){
        db.execSQL("drop table "+tableName);
    }
}
