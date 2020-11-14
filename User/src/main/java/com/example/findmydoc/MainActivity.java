package com.example.findmydoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(),UserTypeChooser.class));

            }
        };
        handler.postDelayed(runnable,2000);
    }
}