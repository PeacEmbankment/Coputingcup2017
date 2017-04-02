package com.example.justinlam.coputingcup;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class addthingincalendar extends AppCompatActivity{

    Context context;
    public static String value;
    public static String value2;
    public static String value3;
    public static String value4;
    public static String value5;
    public static String value6;
    public static int editextnum;
    public static int editextnum3;
    public static int editextnum4;
    public static int peroid[];
    public Spinner spinnerDay, spinnerHour, spinnerMin,spinnerAll,spinnerPrerequisite,spinnerEndHour,spinnerEndMin;
    public ArrayList<String> period_available_arraylist = new ArrayList<String>();
    public ArrayList<String> prerequisite_available_arraylist = new ArrayList<String>();
    public ArrayList<String> period_available_end_arraylist = new ArrayList<String>();
    private int period_number = 0;
    private int period_number2 = 0;
    private int peroid_end_number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addthingincalendarxml);

        String start_day[] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        spinnerDay = (Spinner) findViewById(R.id.day_spinner);
        ArrayAdapter<String> spinnerArrayAdapterDay = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, start_day);
        spinnerArrayAdapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerDay.setAdapter(spinnerArrayAdapterDay);

        String start_hour[] = {
                "00","01", "02", "03", "04", "05", "06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
        spinnerHour = (Spinner) findViewById(R.id.hour_spinner);
        ArrayAdapter<String> spinnerArrayAdapterHour = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, start_hour);
        spinnerArrayAdapterHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerHour.setAdapter(spinnerArrayAdapterHour);
        spinnerEndHour = (Spinner) findViewById(R.id.hour_end_spinner);
        ArrayAdapter<String> spinnerArrayAdapterEndHour = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, start_hour);
        spinnerArrayAdapterEndHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerEndHour.setAdapter(spinnerArrayAdapterEndHour);

        String start_min[] = {
                "00","01", "02", "03", "04", "05", "06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
        spinnerMin = (Spinner) findViewById(R.id.minutes_spinner);
        ArrayAdapter<String> spinnerArrayAdapterMin = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, start_min);
        spinnerArrayAdapterMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerMin.setAdapter(spinnerArrayAdapterMin);
        spinnerEndMin = (Spinner) findViewById(R.id.minutes_end_spinner);
        ArrayAdapter<String> spinnerArrayAdapterEndMin = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, start_min);
        spinnerArrayAdapterEndMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerEndMin.setAdapter(spinnerArrayAdapterEndMin);
    }

    public void back(View view){
        Intent open_game1 = new Intent(getBaseContext(), MainActivity.class);
        startActivity(open_game1);
    }
    public void addevent(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        value = editText.getText().toString();
        if(value.matches("")) {}
        else {editextnum = Integer.parseInt(value);}

        EditText editText2 = (EditText) findViewById(R.id.activity_name_edittext);
        value2 = editText2.getText().toString();

        EditText editText3 = (EditText) findViewById(R.id.priority_edittext);
        value3 = editText3.getText().toString();
        if(value3.matches("")) {} else{editextnum3 = Integer.parseInt(value3);}

        EditText editText4 = (EditText) findViewById(R.id.duration_edittext);
        value4 = editText4.getText().toString();
        if(value4.matches("")){}else{editextnum4 = Integer.parseInt(value4);}

        if (value.matches("") || value2.matches("") || value3.matches("") || value4.matches("")) {
            Toast.makeText(getApplicationContext(), "You have not entered all the information", Toast.LENGTH_LONG).show();
        }
        else{
            DBhelper dBhelper = new DBhelper(this);

            SQLiteDatabase db = dBhelper.getReadableDatabase();

            String insertSQL = "INSERT into activity ('activity_number', 'priority', 'activity_title', 'duration', 'prerequisite') values ('" + value + "', '" + value3 + "','" + value2 + "','" + editextnum4 + "','" + prerequisite_available_arraylist + "')";
            Log.d("SQL test",insertSQL);
            db.execSQL(insertSQL);
            Log.d("","successfully inserted into db");

            Log.d("value1234", editextnum + "," + value2 + "," + editextnum3 + "," + value4);

            for(int i = 0;i<period_available_arraylist.size();i++){
                if(period_available_arraylist.get(i).substring(0,3).equals("Mon")){period_number=1440;}
                if(period_available_arraylist.get(i).substring(0,3).equals("Tue")){period_number=2880;}
                if(period_available_arraylist.get(i).substring(0,3).equals("Wed")){period_number=4320;}
                if(period_available_arraylist.get(i).substring(0,3).equals("Thr")){period_number=5760;}
                if(period_available_arraylist.get(i).substring(0,3).equals("Fri")){period_number=7200;}
                if(period_available_arraylist.get(i).substring(0,3).equals("Sat")){period_number=8640;}
                if(period_available_arraylist.get(i).substring(0,3).equals("Sun")){period_number=10080;}
                period_number2=period_number;
                Log.d("period ava arraylist03 ",period_available_arraylist.get(i).substring(0,3));
                period_number=period_number+Integer.valueOf(period_available_arraylist.get(i).substring(4,6))*60;
                Log.d("period ava arraylist46 ",Integer.parseInt(period_available_arraylist.get(i).substring(4,6))*60+"");
                period_number=period_number+Integer.valueOf(period_available_arraylist.get(i).substring(7,9));
                Log.d("period ava arraylist79 ",period_available_arraylist.get(i).substring(7,9));
                Log.d("paa",period_available_arraylist+"");
                Log.d("period number",period_number+"");

                peroid_end_number=period_number2;
                peroid_end_number=peroid_end_number+Integer.valueOf(period_available_arraylist.get(i).substring(10,12))*60;
                Log.d("period ava arraylist911",Integer.parseInt(period_available_arraylist.get(i).substring(10,12))*60+"");
                peroid_end_number=peroid_end_number+Integer.valueOf(period_available_arraylist.get(i).substring(13,15));
                Log.d("period ava arrayli.1315",period_available_arraylist.get(i).substring(13,15));
                Log.d("paa",period_available_arraylist+"");
                Log.d("period number",peroid_end_number+"");
                String insertSQL2 = "INSERT into available_period ('activity_number', 'start_time', 'end_time') values ('" + editextnum + "','" + period_number + "','" + peroid_end_number + "')";
                Log.d("SQL2 test",insertSQL2);
                db.execSQL(insertSQL2);

            }


            Intent open_game1 = new Intent(getBaseContext(), MainActivity.class);
            startActivity(open_game1);
        }
    }

    public void add_period(View view){
        period_available_end_arraylist.add(spinnerEndHour.getSelectedItem()+":"+spinnerEndMin.getSelectedItem());
        period_available_arraylist.add(spinnerDay.getSelectedItem()+" "+ spinnerHour.getSelectedItem()+":"+spinnerMin.getSelectedItem()+"-"+spinnerEndHour.getSelectedItem()+":"+spinnerEndMin.getSelectedItem());
        spinnerAll = (Spinner) findViewById(R.id.all_period_spinner);
        ArrayAdapter<String> spinnerArrayAdapterAll = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, period_available_arraylist);
        spinnerArrayAdapterAll.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerAll.setAdapter(spinnerArrayAdapterAll);

        Log.d("day,hour,min",spinnerDay.getSelectedItem()+""+ spinnerHour.getSelectedItem()+spinnerMin.getSelectedItem()+"");
    }

    public void add_prerequisite(View view){
        EditText editText4 = (EditText) findViewById(R.id.prerequisite_edittext);
        value4 = editText4.getText().toString();
        if(value4.matches("")) {} else{editextnum4 = Integer.parseInt(value4);}
        spinnerPrerequisite = (Spinner) findViewById(R.id.prerequisite_spinner);
        prerequisite_available_arraylist.add(value4);
        ArrayAdapter<String> spinnerArrayAdapterPrerequisite = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, prerequisite_available_arraylist);
        spinnerArrayAdapterPrerequisite.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerPrerequisite.setAdapter(spinnerArrayAdapterPrerequisite);
        editText4.setText("");
    }
}

