package com.vs.messaging;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VS on 10-07-2016.
 */
public class Loging extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "VishalDB";
    public static final String TABLE_NAME = "VDATA";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "COLLEGENAME";




    public Loging(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (NAME TEXT, COLLEGENAME TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME, null);

    }

    public boolean ins(String name, String collegename) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, collegename);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return true;

    }

    public Cursor fet() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean upd(String name, String collegename) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, collegename);
        sqLiteDatabase.update(TABLE_NAME,contentValues," NAME = ? ", new String[]{name});
        return true;
    }

    public Integer del(String collegename)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Integer res= sqLiteDatabase.delete(TABLE_NAME," COLLEGENAME = ? ", new String[]{collegename});
        return res;
    }
}
