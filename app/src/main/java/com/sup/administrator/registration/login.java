package com.sup.administrator.registration;

import android.content.DialogInterface;
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
    Button b,b1,b2;
    String s;
    AlertDialog.Builder build;
    dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b2=(Button) findViewById(R.id.reac);
        db=new dbhelper(this);
        db.getWritableDatabase();
        SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
         final String dbid=pref.getString("id",null);

        build=new AlertDialog.Builder(this);
        build.setTitle("Confirm");
        build.setMessage("are you sure you want to delete?");
        build.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                boolean status=db.delete(dbid);
                if(status==true)
                {
                    Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                }
                dialogInterface.dismiss();
            }
        });
        build.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"no clicked",Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AlertDialog alert=build.create();
        alert.show();
    }
});

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
