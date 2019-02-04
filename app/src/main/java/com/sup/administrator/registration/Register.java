package com.sup.administrator.registration;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
EditText  e1,e2,e3,e4,e5;
    Button b1,b2;
    String s1,s2,s3,s4,s5,getuser,getpass;
    dbhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.email);
        e3=(EditText)findViewById(R.id.mobile);
        e4=(EditText)findViewById(R.id.user1);
        e5=(EditText)findViewById(R.id.pass1);
        b1=(Button) findViewById(R.id.log);
        b2=(Button) findViewById(R.id.register1);
        db=new dbhelper(this);
        db.getWritableDatabase();
      b1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              s1=e1.getText().toString();
              s2=e2.getText().toString();
              s3=e3.getText().toString();
              s4=e4.getText().toString();
              s5=e5.getText().toString();
              Intent u=new Intent(getApplicationContext(),MainActivity.class);
              startActivity(u);
          }
      });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=e1.getText().toString();
                s2=e2.getText().toString();
                s3=e3.getText().toString();
                s4=e4.getText().toString();
                s5=e5.getText().toString();
                boolean result=db.insertdata(s1,s2,s3,s4,s5);
                if(result==true)
                {
                    Toast.makeText(getApplicationContext(),"inserted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
