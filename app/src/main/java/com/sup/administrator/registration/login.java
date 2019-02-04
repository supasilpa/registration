package com.sup.administrator.registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
       TextView t;
    Button b,b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        b1=(Button)findViewById(R.id.pedit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ProfileEdit.class);
                startActivity(i);
            }
        });



        b=(Button)findViewById(R.id.logout);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor ed=getSharedPreferences("login",MODE_PRIVATE).edit();
                ed.clear();
                ed.apply();
                Intent a=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(a);
            }
        });

        t=(TextView)findViewById(R.id.tv) ;

        SharedPreferences sh=getSharedPreferences("login",MODE_PRIVATE);
        String getuser=sh.getString("name",null);
//        Toast.makeText(getApplicationContext(),getuser,Toast.LENGTH_LONG).show();
        t.setText("Hello " +getuser);


    }
}
