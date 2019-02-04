package com.sup.administrator.registration;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileEdit extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    Button b;
    dbhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        db=new dbhelper(this);
        db.getWritableDatabase();

        e1=(EditText)findViewById(R.id.name1);
        e2=(EditText)findViewById(R.id.email1);
        e3=(EditText)findViewById(R.id.mobile1);
        e4=(EditText)findViewById(R.id.user2);
        e5=(EditText)findViewById(R.id.pass2);
        b=(Button) findViewById(R.id.edit);
        SharedPreferences sh=getSharedPreferences("login",MODE_PRIVATE);
        String dbid=sh.getString("id",null);

       Cursor cur=db.searchid(dbid) ;
        if(cur.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_LONG).show();
        }
        else {
            while(cur.moveToNext())
            {
                String getname=cur.getString(1);
                String getemail=cur.getString(2);
                String getmob=cur.getString(3);
                String getuser=cur.getString(4);
                String getpass=cur.getString(5);
                e1.setText(getname);
                e2.setText(getemail);
                e3.setText(getmob);
                e4.setText(getuser);
                e5.setText(getpass);

            }
        }





        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
