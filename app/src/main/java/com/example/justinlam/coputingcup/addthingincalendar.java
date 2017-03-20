package com.example.justinlam.coputingcup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addthingincalendar extends AppCompatActivity{

    Context context;
    public static String value;
    public static String value2;
    public static String value3;
    public static String value4;
    public static int editextnum;
    public static int editextnum3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.addthingincalendarxml);
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

        EditText editText2 = (EditText) findViewById(R.id.editText3);
        value2 = editText2.getText().toString();

        EditText editText3 = (EditText) findViewById(R.id.editText5);
        value3 = editText3.getText().toString();
        if(value3.matches("")) {} else{editextnum3 = Integer.parseInt(value3);}

        EditText editText4 = (EditText) findViewById(R.id.editText6);
        value4 = editText4.getText().toString();

        if (value.matches("") || value2.matches("") || value3.matches("") || value4.matches("")) {
            Toast.makeText(getApplicationContext(), "You have not entered all the information", Toast.LENGTH_LONG).show();
        }
        else{
            Log.d("value1234", editextnum + "," + value2 + "," + editextnum3 + "," + value4);
            Intent open_game1 = new Intent(getBaseContext(), MainActivity.class);
            startActivity(open_game1);
        }
    }
}
