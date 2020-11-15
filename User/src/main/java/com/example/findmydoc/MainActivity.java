package com.example.findmydoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.findmydoc.Doctor.DoctorDashboard;
import com.example.findmydoc.Doctor.PatientHealth;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(getApplicationContext());
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser==null){
                    startActivity(new Intent(getApplicationContext(),UserTypeChooser.class));
                }
                else{
                    startActivity(new Intent(getApplicationContext(), PatientHealth.class));
                }
                finish();


            }
        };
        handler.postDelayed(runnable,800);
    }
}