package com.example.findmydoc;

public class User {
    public String user_id;
    public String user_name;
    public String phone_number;
    public String user_type;
    public String device_id;


    public User(String user_id, String user_name, String phone_number, String user_type, String device_id) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.phone_number = phone_number;
        this.user_type = user_type;
        this.device_id = device_id;
    }
}
