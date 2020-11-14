package com.example.findmydoc.Patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.findmydoc.R;
import com.example.seller.User;
import com.example.seller.ViewAllDoctors;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class showDoctorList extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ViewAllDoctors.RecycleAdapter recycleAdapter;
    public ArrayList<DoctorsInfo> doctor = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doctor_list);


    }
    }
