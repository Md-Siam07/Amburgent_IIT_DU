package com.example.findmydoc;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

public class SharedPrefManager {


    private static final String SHARED_PREF_NAME = "USER_INFO";
    private static final String USER_ID= "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String PHONE_NUMBER="PHONE_NUMBER";
    private static final String USER_TYPE= "USER_TYPE";
    private static final String DEVICE_ID="DEVICE_ID";
    private static final String UNSEEN_MESSAGE="UNSEEN_MESSAGE";
    private static final String ATT_DATE="ATTENDENCE_DATE";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void setAttendence_Date(String date){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ATT_DATE,date);
    }

    public String getAttendence_Date(){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String date=sharedPreferences.getString(ATT_DATE,"");

        return date;
    }

    public int get_increaseUnseenMessage(){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int num_of_message=sharedPreferences.getInt(UNSEEN_MESSAGE,0)+1;
        editor.putInt(UNSEEN_MESSAGE,num_of_message);
        editor.apply();

        return num_of_message;
    }
    public void clearUnseenMessage(){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(UNSEEN_MESSAGE,0);
        editor.apply();
    }
    public void userLogin(User user) {

        MySQLiteDatabase mySQLiteDatabase=MySQLiteDatabase.getMySQLiteDatabase(mCtx);
        mySQLiteDatabase.create_memeber_table();
        mySQLiteDatabase.insert_member_table_data(user.user_id,user.user_name,user.phone_number,user.user_type,user.device_id);

    }


    public User getUser() {

        User user=null;
        MySQLiteDatabase mySQLiteDatabase=MySQLiteDatabase.getMySQLiteDatabase(mCtx);
        Cursor result=mySQLiteDatabase.getAllRow("Member");
        result.moveToFirst();
        while (!result.isAfterLast()){

            user=new User(result.getString(0),result.getString(1),result.getString(4),result.getString(2),result.getString(3));
            result.moveToNext();

        }
       return user;
    }

    public void changeDeviceId(String device_id){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DEVICE_ID,device_id);
    }

    public boolean isLoggedIn(){

        MySQLiteDatabase mySQLiteDatabase=MySQLiteDatabase.getMySQLiteDatabase(mCtx);
        int result=mySQLiteDatabase.numberOfRows("Member");
        int result2=mySQLiteDatabase.numberOfRows("Student");

        if(result>0||result2>0){

            return true;
        }
        else return  false;

    }
    public void logout() {
        MySQLiteDatabase mySQLiteDatabase=MySQLiteDatabase.getMySQLiteDatabase(mCtx);
        mySQLiteDatabase.delete("Member");
    }
}