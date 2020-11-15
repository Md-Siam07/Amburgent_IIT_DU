package com.example.findmydoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.findmydoc.Doctor.DoctorDashboard;
import com.example.findmydoc.Doctor.SignUpPage;

public class UserTypeChooser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type_chooser);
    }

    public void doctor(View view)
    {
        Intent tnt=new Intent(getApplicationContext(), SignUpPage.class);
        tnt.putExtra("user_type","doctor");
        startActivity(tnt);
    }
    public void patient(View view)
    {
        Intent tnt=new Intent(getApplicationContext(), SignUpPage.class);
        tnt.putExtra("user_type","patient");
        startActivity(tnt);

    }
}