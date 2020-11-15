package com.example.findmydoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class MySQLiteDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "USER_INFO.db";
    private static final String SHARED_PREF_NAME = "USER_INFO";
    private static final String USER_ID= "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String PHONE_NUMBER="PHONE_NUMBER";
    private static final String USER_TYPE= "USER_TYPE";
    private static final String DEVICE_ID="DEVICE_ID";
    private static final String UNSEEN_MESSAGE="UNSEEN_MESSAGE";
    private static final String ATT_DATE="ATTENDENCE_DATE";
    private HashMap hp;
    private static MySQLiteDatabase mySQLiteDatabase;
    private SQLiteDatabase db;
    public static synchronized MySQLiteDatabase getMySQLiteDatabase(Context context)
    {
        if(mySQLiteDatabase==null){

            mySQLiteDatabase=new MySQLiteDatabase(context);

            return mySQLiteDatabase;
        }
        else{

            return mySQLiteDatabase;
        }
    }
    private MySQLiteDatabase(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        this.db=db;

    }

    public void create_memeber_table(){
        db=this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS Member (USER_ID TEXT PRIMARY KEY,USER_NAME TEXT,PHONE_NUMBER TEXT,USER_TYPE TEXT,DEVICE_ID TEXT)");
    }

    public void  drop_table(String table_name){
        db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate(db);
    }

    public long insert_member_table_data(String user_id, String user_name, String phone_number, String user_type, String device_id) {

        create_memeber_table();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, user_id);
        contentValues.put(USER_NAME, user_name);
        contentValues.put(USER_TYPE, user_type);
        contentValues.put(PHONE_NUMBER, phone_number);
        contentValues.put(DEVICE_ID, device_id);

        delete ("Member");
        long result=db.insert("Member", null, contentValues);
        return result;


    }
    public Cursor getSingleRow(int id, String table_name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from"+table_name+" where id="+id+"", null );
        return res;
    }
    public Cursor getAllRow(String table_name) {

        if(table_name.equalsIgnoreCase("Member")){

            create_memeber_table();
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+table_name,null);
        return res;
    }

    public int numberOfRows(String table_name){

        if(table_name.equalsIgnoreCase("Member")){

            create_memeber_table();
        }

        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, table_name);
        return numRows;
    }

    public void delete (String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(numberOfRows(table_name)>0){
            db.delete(table_name,null,null);
        }

    }


    public ArrayList<String> getAllCotacts(String table_name) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(table_name)));
            res.moveToNext();
        }
        return array_list;
    }
}
