package com.example.kyle.uwitimemanagemeent;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Database1.db";
    public static final String TABLE_NAME = "task";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "STARTDATE";
    public static final String COL_4 = "ENDDATE";
    public static final String COL_5 = "STARTTIME";
    public static final String COL_6 = "ENDTIME";
    public static final String COL_7 = "NOTE";
//        public static final String COL_7 = "";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TITLE TEXT, " +
                "STARTDATE INTEGER," +
                "ENDDATE INTEGER," +
                "STARTTIME INTEGER," +
                "ENDTIME INTEGER," +
                "NOTE TEXT" +
                ")");

    }

   @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title,long startdate,long enddate,long starttime, long endtime,String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,startdate);
        contentValues.put(COL_4,enddate);
        contentValues.put(COL_5,starttime);
        contentValues.put(COL_6,endtime);
        contentValues.put(COL_7,note);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(int id,String title,long startdate,long enddate,long starttime, long endtime,String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,startdate);
        contentValues.put(COL_4,enddate);
        contentValues.put(COL_5,starttime);
        contentValues.put(COL_6,endtime);
        contentValues.put(COL_7,note);

        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] {String.valueOf(id)});
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
        //db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //onCreate(db);
    }


}


