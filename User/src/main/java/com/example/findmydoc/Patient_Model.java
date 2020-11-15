package com.example.findmydoc;

public class Patient_Model {
    public String id;
    public String name;
    public String age;
    public String phone_number;
    public String image_path;
    public String device_id;


    public Patient_Model(String id, String name, String age, String phone_number, String image_path, String device_id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone_number = phone_number;
        this.image_path = image_path;
        this.device_id = device_id;
    }
}
