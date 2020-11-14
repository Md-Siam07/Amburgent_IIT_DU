package com.example.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.test;

public class AdminMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        }
    public void add_doctors(View view)
    {
        Intent intent = new Intent(getApplicationContext(), AddDoctor.class);
        startActivity(intent);
    }
    public void view_all_doctors(View view)
    {
        Intent intent = new Intent(getApplicationContext(), ViewAllDoctors.class);
        startActivity(intent);
    }
}