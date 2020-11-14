package com.example.seller;

public class User {
    String user_id,user_name, designation, hospital, phone_number;
    User(String user_id, String user_name, String hospital, String designation, String phone_number )
    {
        this.designation=designation;
        this.phone_number=phone_number;
        this.user_name=user_name;
        this.hospital=hospital;
        this.user_id=user_id;
    }
}
