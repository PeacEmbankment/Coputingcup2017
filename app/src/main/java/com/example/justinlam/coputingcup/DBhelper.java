package com.example.justinlam.coputingcup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBhelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "computingcupdb";
    //this is rubbish.

    public DBhelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SQL = "CREATE TABLE IF NOT EXISTS activity ( 'activity_number' INTEGER PRIMARY KEY, 'priority' INTEGER, 'activity_title' TEXT,'duration' INTEGER, 'prerequisite' INTEGER)";
        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS activity");

        // Create tables again
        onCreate(db);

    }

    public List<databaseModelList> getDataFromDB() {
        List<databaseModelList> modelList = new ArrayList<databaseModelList>();
        String query;
        query = "select activity_number, priority from activity";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                databaseModelList model = new databaseModelList();

                model.setActivityNumber(cursor.getInt(0));
                model.setPriority(cursor.getInt(1));

                modelList.add(model);
            }while (cursor.moveToNext());
        }

        return modelList;
    }

}
