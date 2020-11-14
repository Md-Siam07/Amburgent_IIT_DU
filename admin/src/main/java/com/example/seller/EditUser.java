package com.example.seller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditUser extends AppCompatActivity {
    User user;
    ArrayList<User> users=new ArrayList<>();
    EditText name,phone,hospital, designation;
    String names, phones, hospitals, ids, designations;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        name = findViewById(R.id.name_eu);
        hospital = findViewById(R.id.hospital_eu);
        phone = findViewById(R.id.phone_eu);
        designation = findViewById(R.id.designation_eu);
        names = getIntent().getStringExtra("name");
        phones = getIntent().getStringExtra("phone");
        hospitals = getIntent().getStringExtra("hospital");
        ids = getIntent().getStringExtra("id");
        designations=getIntent().getStringExtra("designation");
        name.setText(names);
        hospital.setText(hospitals);
        designation.setText(designations);
        phone.setText(phones);
    }

    public void edit_use(View view) {
        names = name.getText().toString();
        hospitals = hospital.getText().toString();
        designations = hospital.getText().toString();
        phones = phone.getText().toString();
        DocumentReference documentReference = db.collection("doctors").document();

        Map<String, String> data = new HashMap<>();
        db.collection("doctors").document(ids).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();

            }
        });
        data.put("user_name", names);
        data.put("associated_hospital", hospitals);
        data.put("phone_no", phones);
        data.put("designation",designations);
        data.put("user", ids);
        documentReference.set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Edited Successfully", Toast.LENGTH_LONG).show();
            }
        });
        Intent intent = new Intent(EditUser.this,ViewAllDoctors.class);
        startActivity(intent);

    }
}