package com.example.justinlam.coputingcup;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.IntegerRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
//import java.util.logging.Handler;

import static android.R.attr.data;
import static java.lang.System.out;


public class MainActivity extends AppCompatActivity {
    Context context;
    public ArrayList<String> listArrayList = new ArrayList<String>();
    public int a=1;
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    ProgressDialog progressDialog;
    private Handler progressBarHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        progressBar.dismiss();
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
            File myFile = new File(filepath+"/testimport.txt");
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));

            boolean b=true;
            int c=0;
            int d=0;
            int value;
            boolean e=true;
            String temp_Activity_Number;
            String temp_Priority;
            String temp_Activity_title;
            String temp_Duration;
            String temp_prerequisite[] = new String[100];
            ArrayList<String> listArrayList = new ArrayList<String>();

            String parta[] = new String[5];
            String partb[] = new String[5];
            String partc[] = new String[5];
            String partd[] = new String[100];
            while ((aDataRow = myReader.readLine()) != null) {
                //Log.d("MainActivity","LogaDataRow: " + aDataRow);
                String[] parts = aDataRow.split(",");
                String[] parts1 = aDataRow.split(" ");

                if(b){b=false;Log.d("not b","");value=Integer.parseInt(aDataRow);e=false;}
                else {
                    if (a == 1) {
                        for (int i = 0; i < 4; i++) {
                            if (!parts[i].isEmpty() && !parts[i].equals("")) {
                                parta[i] = parts[i];
                                Log.d("a1", parta[i]);
                            } else {
                                Log.d("for break", "");
                                break;
                            }
                        }
                        a++;
                    } else if(a==2){
                        for (int i = 0; i < 2; i++) {
                            if (!parts1[i].isEmpty() && !parts1[i].equals("")) {
                                partb[i] = parts1[i];
                                Log.d("a2", partb[i]);
                                if(i==0){c=Integer.parseInt(partb[i]);}
                                if(i==1){d=Integer.parseInt(partb[i]);}
                            } else {
                                Log.d("for break", "");
                                break;
                            }
                        }
                        a++;
                    }else if(a==3){
                        for (int i = 0; i < c; i++) {
                            if (!parts[i].isEmpty() && !parts[i].equals("") && !parts[i].equals(" ")) {
                                partc[i] = parts[i];
                                Log.d("a3", partc[i]);
                            } else {
                                Log.d("for break", "");
                                break;
                            }
                        }
                        if(d==0) {
                            a = 1;
                        }else{a=4;}
                    }else if(d>0){
                        listArrayList.clear();
                        for (int i = 0; i < d; i++) {
                            if (!parts[i].isEmpty() && !parts[i].equals("") && !parts[i].equals(" ")) {
                                partd[i] = parts[i];
                                Log.d("d>0", partd[i]);
                                listArrayList.add(parts[i]);
                            } else {
                                Log.d("for break", "");
                                break;
                            }
                        }
                        a=1;
                    }else{Log.d("d=0","");a=1;}
                }
                if(a==1){
                    if(e) {
                        temp_Activity_Number = parta[0];
                        temp_Activity_title = parta[1];
                        temp_Priority = parta[2];
                        temp_Duration = parta[3];
                        String temp_Duration_split[] = temp_Duration.split(":");
                        String temp_Duration_split_second_half[] = temp_Duration_split[1].split("'");
                        temp_Duration = Integer.parseInt(temp_Duration_split[0]) * 60 + Integer.parseInt(temp_Duration_split_second_half[0]) + "";
                        Log.d("AN,AT,Pri,D,Pre", temp_Activity_Number + "," + temp_Activity_title+"," + temp_Priority +","+ temp_Duration +","+ listArrayList);
                        DBhelper dBhelper = new DBhelper(this);

                        SQLiteDatabase db = dBhelper.getReadableDatabase();

                        String insertSQL = "INSERT into activity ('activity_number', 'priority', 'activity_title', 'duration', 'prerequisite') values ('" + temp_Activity_Number + "', '" + temp_Priority + "','" + temp_Activity_title + "','" + temp_Duration + "','" + listArrayList + "')";
                        Log.d("SQL test",insertSQL);
                        db.execSQL(insertSQL);
                        Log.d("","successfully inserted into db");


                        for(int i = 0;i<c;i++) {
                            int partc_int_start[]=new int[1000];
                            Log.d("partc",partc_int_start[i]+"");
                            int partc_int_end[]=new int [1000];
                            String partc_split_space[]=partc[i].split(" ");
                            String partc_split_space_hiven[]=partc_split_space[1].split("-");
                            String partc_split_space_hiven_colon1[]=partc_split_space_hiven[0].split(":");
                            String partc_split_space_hiven_colon2[]=partc_split_space_hiven[1].split(":");
                            if(partc_split_space[0].equals("Mon")){partc_int_start[i] = 1440;partc_int_end[i]=1440 ;}
                            if(partc_split_space[0].equals("Tue")){partc_int_start[i] = 2880;partc_int_end[i]= 2880;}
                            if(partc_split_space[0].equals("Wed")){partc_int_start[i] = 4320;partc_int_end[i]=4320 ;}
                            if(partc_split_space[0].equals("Thr")){partc_int_start[i] = 5760;partc_int_end[i]= 5760;}
                            if(partc_split_space[0].equals("Fri")){partc_int_start[i] = 7200;partc_int_end[i]= 7200;}
                            if(partc_split_space[0].equals("Sat")){partc_int_start[i] = 8640;partc_int_end[i]= 8640;}
                            if(partc_split_space[0].equals("Sun")){partc_int_start[i] = 10080;partc_int_end[i]= 10080;}
                            Log.d("partc",partc_int_start[i]+"");
                            partc_int_start[i]=partc_int_start[i]+Integer.parseInt(partc_split_space_hiven_colon1[0])*60+Integer.parseInt(partc_split_space_hiven_colon1[1]);
                            Log.d("partc",partc_int_start[i]+"");
                            partc_int_end[i]=partc_int_end[i]+Integer.parseInt(partc_split_space_hiven_colon2[0])*60+Integer.parseInt(partc_split_space_hiven_colon2[1]);

                            Log.d("partc",partc_int_start[i]+"");
                            Log.d("mainActivity inport",partc_split_space[0]+" "+ partc_split_space_hiven_colon1[0]+" "+partc_split_space_hiven_colon1[1]+" "+partc_split_space_hiven_colon2[0]+" "+partc_split_space_hiven_colon2[1]);

                            String insertSQL2 = "INSERT into available_period ('activity_number', 'start_time', 'end_time') values ('" + temp_Activity_Number + "','" + partc_int_start[i] + "','" + partc_int_end[i] + "')";
                            Log.d("SQL2 test", insertSQL2);
                            db.execSQL(insertSQL2);

                        }db.close();
                    }else{e=true;}
                }

            }myReader.close();


/*
            DBhelper dBhelper = new DBhelper(ths);

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
        File patternDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/testexport.txt");
        patternDirectory.mkdirs();
        String filename = "testexport.txt";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            Log.d("entered","exporttxt try catch");
            //outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream = new FileOutputStream (new File(patternDirectory.getAbsolutePath().toString()+"/testexport.txt"), true);
            OutputStreamWriter myOutWriter =
                    new OutputStreamWriter(outputStream);
            myOutWriter.append("hello world!");
            outputStream.write(string.getBytes());
            outputStream.write(System.getProperty("line.separator").getBytes());
            outputStream.write("hello world skip lined ".getBytes());
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
    public void clearAllData(View view) {
        DBhelper dBhelper = new DBhelper(this);
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        db.delete("activity", null, null);
        db.delete("available_period",null,null);
        db.delete("export",null,null);
        db.close ();
    }
    public void startAI(View view){
        /*progressBar = new ProgressDialog(view.getContext());
        progressBar.setCancelable(true);
        progressBar.setMessage("planning...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.show();
        progressBarStatus = 0;
        progressDialog = new ProgressDialog(this);*/
        progressBar = new ProgressDialog(view.getContext());
        progressBar.setCancelable(true);
        progressBar.setMessage("Planning ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.show();
        progressBarStatus = 0;

        new Thread(new Runnable(){
            public void run(){
                /*progressBarStatus = 1;*/

                //progressDialog = ProgressDialog.show(context,"","Hello",true);

                progressBarHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //progressBar.setProgress(progressBarStatus);
                    }
                });
                //AI starts here



                //AI ends here

                progressBar.dismiss();
                //progressDialog.dismiss();
            }
        }).start();
    }
}
