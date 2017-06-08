package com.android.ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteHelper
 * Created by HarryWang on 2017/4/30.
 */

public class DBHelper extends SQLiteOpenHelper {
    /**
     * 构造器
     * @param context 上下文
     * @param name 数据库名称
     * @param factory 游标工厂
     * @param version 数据库版本
     */
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    /**
     * 创建DBHelper实例
     * @param context 上下文
     */
    public DBHelper(Context context){
        this(context,DBInfo.DB.DB_NAME,null,DBInfo.DB.VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBInfo.Table.DROP_CLOTHES_TABLE);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBInfo.Table.CREATE_CLOTHES_TABLE);
    }

}
