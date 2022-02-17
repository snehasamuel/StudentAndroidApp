package com.example.studentdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchStudentAcitivity extends AppCompatActivity {

    DatabaseHelper helper;


    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1,b2;

    String getAdmission,getName,getNumber,getCollege;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student_acitivity);

        helper=new DatabaseHelper(this);
        helper.getWritableDatabase();
        ed1=(EditText) findViewById(R.id.Admsn);
        ed2=(EditText) findViewById(R.id.name);
        ed3=(EditText) findViewById(R.id.number);
        ed4=(EditText) findViewById(R.id.college);
        b1=(AppCompatButton) findViewById(R.id.search);
        b2=(AppCompatButton) findViewById(R.id.Backto);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdmission=ed1.getText().toString();
                Cursor c= helper.searchData(getAdmission);
                if(c.getCount()==0)
                {
                    ed2.setText("");
                    ed3.setText("");
                    ed4.setText("");
                    Toast.makeText(getApplicationContext(), "Invalid Admission Number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    while(c.moveToNext())
                    {
                        getName=c.getString(1);
                        getNumber=c.getString(2);
                        getCollege=c.getString(4);
                    }

                    ed2.setText(getName);
                    ed3.setText(getNumber);
                    ed4.setText(getCollege);
                }


            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}