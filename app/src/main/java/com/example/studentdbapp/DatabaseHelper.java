package com.example.studentdbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String DbName="college.db";
    static String TableName="Student";
    static String Col1="Id";
    static String Col2="Name";
    static String Col3="RollNo";
    static String Col4="AdmissionNo";
    static String Col5="College";


    public DatabaseHelper(Context context) {

        super(context, DbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="create table "+TableName+"(" +
                Col1+" integer primary key autoincrement,"+
                Col2+" text,"+
                Col3+" text,"+
                Col4+" text,"+
                Col5+" text)";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String name,String rollno,String admsnno,String clg)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(Col2,name);
        c.put(Col3,rollno);
        c.put(Col4,admsnno);
        c.put(Col5,clg);

        long status=db.insert(TableName,null,c);
        if(status==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    public Cursor searchData(String Admsn)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String retrieve="select * from "+TableName+" where "+
                Col4+"='"+Admsn+"'";
        Cursor c=db.rawQuery(retrieve,null);
        return c;
    }


}
