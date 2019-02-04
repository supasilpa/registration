package com.sup.administrator.registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText ed1,ed2;
    Button b1,b2;
    String s1,s2,dbpass,dbname,id;
    dbhelper db;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.user);
        ed2=(EditText)findViewById(R.id.pass);
        b1=(Button)findViewById(R.id.loginbutton);
        b2=(Button)findViewById(R.id.registerbutton);
        db=new dbhelper(this);
        db.getWritableDatabase();

        SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
        username=pref.getString("name",null);
        if(username!=null)
        {
            Intent i=new Intent(getApplicationContext(),login.class);
            startActivity(i);
        }


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=ed1.getText().toString();
                s2=ed2.getText().toString();
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=ed1.getText().toString();
                s2=ed2.getText().toString();
                Cursor cur=db.search(s1);
                if(cur.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"Invalid username",Toast.LENGTH_LONG).show();
                }
                else
                {
                    while(cur.moveToNext())
                    {
                        dbpass=cur.getString(5);
                        dbname=cur.getString(1);
                        id=cur.getString(0);
                        if(dbpass.equals(s2))
                        {
                            SharedPreferences.Editor ed=getSharedPreferences("login",MODE_PRIVATE).edit();
                            ed.putString("name",dbname);
                            ed.putString("id",id);

                            ed.apply();
                            Intent y=new Intent(getApplicationContext(),login.class);
                            startActivity(y);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Invalid username and password",Toast.LENGTH_LONG).show();

                        }
                    }
                }
            }
        });
    }
}
