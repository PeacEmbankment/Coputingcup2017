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

public class addthingincalendar extends AppCompatActivity{

        Context context;
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
            String value = editText.getText().toString();
            float editextnum=Float.parseFloat(value);

            EditText editText2 = (EditText) findViewById(R.id.editText3);
            String value2 = editText2.getText().toString();

            EditText editText3 = (EditText) findViewById(R.id.editText5);
            String value3 = editText3.getText().toString();
            float editextnum3=Float.parseFloat(value3);

            EditText editText4 = (EditText) findViewById(R.id.editText6);
            String value4 = editText4.getText().toString();

            Log.d("value1234",value+","+value2+","+value3+","+value4);

            Intent open_game1 = new Intent(getBaseContext(), MainActivity.class);
            startActivity(open_game1);
        }
}
