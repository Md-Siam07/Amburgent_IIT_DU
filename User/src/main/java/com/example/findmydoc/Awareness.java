package com.example.findmydoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Awareness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness);
    }
    public void skip(View view){
        startActivity(new Intent(getApplicationContext(),UserTypeChooser.class));
    }
}