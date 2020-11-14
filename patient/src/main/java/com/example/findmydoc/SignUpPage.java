package com.example.findmydoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpPage extends AppCompatActivity {

    EditText username,phone;
    Button signup;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        signup = findViewById(R.id.signup);



    }

    public void signup(View v){
        //you have to add country code
        phoneNumber ="+88"+phone.getText().toString();


        Intent intent = new Intent(getApplicationContext(), OTP_page.class);
        intent.putExtra("phoneNumber",phoneNumber);
        intent.putExtra("name",username.getText().toString());
        startActivity(intent);
    }

}