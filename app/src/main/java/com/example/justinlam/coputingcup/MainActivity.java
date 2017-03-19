package com.example.justinlam.coputingcup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newthing(View view){
        Intent open_game1 = new Intent(context, addthingincalendar.class);
        context.startActivity(open_game1);

    }
    public void importtxt(View view){

    }
    public void exporttxt(View view){

    }
    public void viewcalendar(View view){

    }
}
