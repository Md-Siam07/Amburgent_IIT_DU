package com.example.seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddDoctor extends AppCompatActivity {
    EditText user_name_et, associated_hospital_et, designtation_et, phone_et;
    String user_name, associated_hospital, phone, designtation;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        user_name_et = findViewById(R.id.name);
        associated_hospital_et = findViewById(R.id.hospital_ass);
        designtation_et = findViewById(R.id.designation);
        phone_et = findViewById(R.id.phone);
        progressDialog = new ProgressDialog(AddDoctor.this);
        progressDialog.setMessage("Uploading...");
    }
    public void submit(View view)
    {
        user_name = user_name_et.getText().toString();
        associated_hospital = associated_hospital_et.getText().toString();
        phone = phone_et.getText().toString();
        designtation = designtation_et.getText().toString();
        progressDialog.show();
        DocumentReference documentReference = db.collection("doctors").document();
        Map<String, String> data = new HashMap<>();
        data.put("user_name",user_name);
        data.put("associated_hospital",associated_hospital);
        data.put("designation",designtation);
        data.put("phone_no",phone);
        String user_id = documentReference.getId();
        data.put("user",user_id);
        //commit visible hote hobe
        documentReference.set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Uploaded Successfully",Toast.LENGTH_LONG).show();
            }
        });
        //hudai();
    }

    public void hudai()
    {
        Log.d("Msg: ", "andaji");
    }
}