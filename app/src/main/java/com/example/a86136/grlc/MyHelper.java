package com.example.a86136.grlc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//创建数据库
public class MyHelper extends SQLiteOpenHelper{
    public MyHelper(Context context){
        super(context,"Mymanger.db",null,2);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table income(_id integer primary key autoincrement, " +
                "name varchar(20), money varchar(20), time varchar(20), " +
                "leib varchar(10),payer varchar(10), other varchar(20))");
        db.execSQL("create table expend(_id integer primary key autoincrement," +
                "name varchar(20), money varchar(20), time varchar(20), " +
                "leib varchar(10), payer varchar(10), other varchar(20))");
        db.execSQL("create table notes(_id integer primary key autoincrement," +
                "name varchar(20), time varchar(20), note varchar(200))");
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
    public void insert(String name, Integer money){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("money",money);
        long id = db.insert("income",null,values);
        db.close();
    }
}