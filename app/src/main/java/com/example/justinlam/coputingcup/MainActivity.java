package com.example.justinlam.coputingcup;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newthing(View view){
        Log.d("new thing void","");
        //Intent open_game1 = new Intent(context, addthingincalendar.class);

        // Hi Justine,
        // Don't exactly know the reason, "getBaseContext()" should be used instead of "context"
        // Father.
        Intent open_game1 = new Intent(getBaseContext(), addthingincalendar.class);
        Log.d("created intent","");
        startActivity(open_game1);

    }
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    public void importtxt(View view){
     verifyStoragePermissions(MainActivity.this);
        String aDataRow = "";
        try {
            String filepath = Environment.getExternalStorageDirectory().getPath();
            File myFile = new File(filepath+"/download/testimport.txt");
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));

            while ((aDataRow = myReader.readLine()) != null) {
                Log.d("MainActivity","LogaDataRow: " + aDataRow);
            }
/*
            DBhelper dBhelper = new DBhelper(this);

            SQLiteDatabase db = dBhelper.getReadableDatabase();

            String insertSQL = "INSERT into activity ('activity_number', 'priority', 'activity_title', 'duration', 'prerequisite') values ('" + value + "', '" + value3 + "','" + value2 + "','" + editextnum4 + "','" + prerequisite_available_arraylist + "')";
            Log.d("SQL test",insertSQL);
            db.execSQL(insertSQL);
            Log.d("","successfully inserted into db");
            myReader.close();*/

        } catch (IOException e) {
            Log.e("mainActivity",""+e);
        }
    }
    public void exporttxt(View view){
        Log.d("entered","exporttxt");
        File patternDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/sdcard/exporttest.txt");
        patternDirectory.mkdirs();
        String filename = "/sdcard/testexport.txt";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            Log.d("entered","exporttxt try catch");
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream = new FileOutputStream (new File(patternDirectory.getAbsolutePath().toString()), true);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            Log.d("MainActivity","e:"+e);
        }
    }
    public File getTempFile(Context context, String url) {
        File file;
        file = new File("hihihi");
        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, context.getCacheDir());
        } catch (IOException e) {
            // Error while creating file
        }
        return file;
    }
    public void viewcalendar(View view){
        Intent open_game1 = new Intent(getBaseContext(), calendar.class);
        startActivity(open_game1);
    }
}
