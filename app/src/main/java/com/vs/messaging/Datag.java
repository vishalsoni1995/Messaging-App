package com.vs.messaging;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VS on 10-07-2016.
 */
public class Datag extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Backup";
    public static final String TABLE_NAME = "BDATA";
   public static final String COL_1 = "SENDER";
    public static final String COL_2 = "MESSAGE";


    public Datag(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        SQLiteDatabase sqLiteDatabase1=this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY   AUTOINCREMENT, SENDER TEXT, MESSAGE TEXT )");
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME, null);

    }

    public boolean ins(String sender, String message) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, sender);
        contentValues.put(COL_2, message);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return true;

    }







    public Cursor fet() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }





    public Integer del(String id)
{
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    Integer res= sqLiteDatabase.delete(TABLE_NAME, " ID = ? ", new String[]{id});
    return res;
}




}
