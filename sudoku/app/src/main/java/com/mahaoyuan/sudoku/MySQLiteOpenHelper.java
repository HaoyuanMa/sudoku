package com.mahaoyuan.sudoku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.logging.Level;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public final static int VERSION = 1;// 版本号
    public final static String TABLE_NAME = "Rank";// 表名
    public final static String ID = "id";// 后面ContentProvider使用
    public final static String DATE = "date";
    public final static String TIME = "time";
    public static final String LEVEL = "level";
    public static final String DATABASE_NAME = "RankList.db";

    public MySQLiteOpenHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String str_sql = "CREATE TABLE " + TABLE_NAME
                + "(" + DATE + " text PRIMARY KEY," + LEVEL + " INTEGER,"
                    + TIME  + " INTEGER);";
        db.execSQL(str_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
