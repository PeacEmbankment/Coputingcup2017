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
        String CREATE_SQL = "CREATE TABLE IF NOT EXISTS activity ( 'activity_number' INTEGER PRIMARY KEY, 'priority' INTEGER NOT NULL, 'activity_title' TEXT NOT NULL,'duration' INTEGER NOT NULL, 'prerequisite' INTEGER)";
        db.execSQL(CREATE_SQL);
        String CREATE_SQL2 = "CREATE TABLE IF NOT EXISTS available_period ( 'id' INTEGER PRIMARY KEY AUTOINCREMENT, 'activity_number' INTEGER NOT NULL, 'start_time' INTEGER NOT NULL, 'end_time' INTEGER NOT NULL)";
        db.execSQL(CREATE_SQL2);
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
        query = "select activity_number, priority, activity_title, duration, prerequisite from activity";
        query = "select activity.activity_number, activity.priority, activity.activity_title, activity.duration, activity.prerequisite, (select group_concat(available_period.start_time||'-'||available_period.end_time) from available_period where available_period.activity_number=activity.activity_number) as available_period_concat from activity";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                databaseModelList model = new databaseModelList();

                model.setActivityNumber(cursor.getInt(0));
                model.setPriority(cursor.getInt(1));
                model.setActivityTitle(cursor.getString(2));
                model.setDuration(cursor.getInt(3));
                model.setPrerequisite(cursor.getString(4));
                model.setAvailablePeriod(cursor.getString(5));

                modelList.add(model);
            }while (cursor.moveToNext());
        }

        return modelList;
    }

}
