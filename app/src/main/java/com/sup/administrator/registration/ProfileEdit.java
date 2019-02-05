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
    String getname,getemail,getmob,getuser,getpass,s1,s2,s3,s4,s5,getid;
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
        final String dbid=sh.getString("id",null);


       Cursor cur=db.searchid(dbid) ;
        if(cur.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_LONG).show();
        }
        else {
            while(cur.moveToNext())
            {
                getid=cur.getString(0);

                getname=cur.getString(1);
                 getemail=cur.getString(2);
              getmob=cur.getString(3);
              getuser=cur.getString(4);
               getpass=cur.getString(5);
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
            s1=e1.getText().toString();
                s2=e2.getText().toString();
                s3=e3.getText().toString();
                s4=e4.getText().toString();
                s5=e5.getText().toString();
                boolean result=db.updatedata(dbid,s1,s2,s3,s4,s5);
                if(result==true)
                {
                    Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                }






            }
        });

    }
}
