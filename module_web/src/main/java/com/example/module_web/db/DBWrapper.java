package com.example.module_web.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NavUtils;

import com.example.module_web.public_data.Constant;

import java.security.Key;

/**
 * Created by Administrator on 2018/2/22.
 */

public class DBWrapper {
    private SQLiteOpenHelper mOpenHelper;
    private SQLiteDatabase mDatabase;

    public DBWrapper(Context context){
        //创建数据库
        mOpenHelper = new DBOpenHelper(context);
    }

    public void dropTable(String tableName){
        ((DBOpenHelper)mOpenHelper).onDrop(getReader(),tableName);
    }

    public SQLiteDatabase getWriter(){
        return mOpenHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReader(){
        return mOpenHelper.getReadableDatabase();
    }

    public void insertGroup(String kind,String type){
        mDatabase = getWriter();
//        String sql = "insert into "+ Constant.TABLE_NAME_GROUP + "(" +
//                Constant.COLUMN_GROUP_NAME_KIND + "," + Constant.COLUMN_CHILD_NAME_TYPE +
//                ")" +
//                " values(?,?)";
//        mDatabase.execSQL(sql,new String[]{kind,type});
        ContentValues values = new ContentValues();
        values.put(Constant.COLUMN_GROUP_NAME_KIND, kind);
        values.put(Constant.COLUMN_GROUP_NAME_TYPE,kind);
        mDatabase.insert(Constant.TABLE_NAME_GROUP,null,values);
    }

    public void insertChild(String module,String type){
        mDatabase = getWriter();
//        mDatabase.execSQL("insert into "+ Constant.TABLE_NAME_CHILD +" ("+
//                Constant.COLUMN_CHILD_NAME_MODULE +","+
//                Constant.COLUMN_CHILD_NAME_TYPE +") " +
//                "values(?,?)",new String[]{module,type}
//        );
        ContentValues values = new ContentValues();
        values.put(Constant.COLUMN_CHILD_NAME_MODULE,module);
        values.put(Constant.COLUMN_CHILD_NAME_TYPE,type);
        mDatabase.insert(Constant.TABLE_NAME_CHILD,null,values);
    }

    public Cursor selectFromGroup(){
        mDatabase = getReader();
//        return mDatabase.rawQuery("select * from " + Constant.TABLE_NAME_GROUP, null);
        return mDatabase.query(Constant.TABLE_NAME_GROUP, null,null,null,null,null,null);
    }

    public Cursor selectFromChild(String type){
        mDatabase = getReader();
        return mDatabase.query(Constant.TABLE_NAME_CHILD,null,Constant.COLUMN_CHILD_NAME_TYPE+"=?",
                new String[]{type},null,null,null);
    }
}
