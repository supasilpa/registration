package com.sup.administrator.registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2/4/2019.
 */
public class dbhelper extends SQLiteOpenHelper {
    public static  final  String dbname="reg.db";
    public static  final  String tablename="reg";
    public static  final  String col1="id";
    public static  final  String col2="name";
    public static  final  String col3="email";
    public static  final  String col4="mob";
    public static  final  String col5="username";
    public static  final  String col6="pass";
    public dbhelper(Context context) {
        super(context, dbname, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String query="create table "+tablename+"("+col1+ " integer primary key autoincrement, "+col2+ " text, "+col3+ " text, "+col4+ " text, "+col5+ " text, "+col6+ " text )";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query="drop table if exists "+tablename;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

    }
    public boolean insertdata(String name,String email,String mob,String username,String pass){
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2,name);
        cv.put(col3,email);
        cv.put(col4,mob);
        cv.put(col5,username);
        cv.put(col6,pass);
        long status=sq.insert(tablename,null,cv);
        if (status==-1)
        {
            return  false;
        }
        else
        {
            return  true;
        }
    }
    public Cursor search(String username)
    {
        SQLiteDatabase sq=this.getWritableDatabase();
        Cursor cur=sq.rawQuery("SELECT * FROM "+tablename+ " WHERE "+col5+ "='"+username+"'",null);
        return cur;
    }
    public Cursor searchid(String id)
    {
        SQLiteDatabase sq=this.getWritableDatabase();
        Cursor cur=sq.rawQuery("SELECT * FROM "+tablename+ " WHERE "+col1+ "="+id,null);
        return cur;
    }
    public boolean updatedata(String id,String name,String email,String mobile,String user,String pass){
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
      cv.put(col2,name);
        cv.put(col3,email);
        cv.put(col4,mobile);
        cv.put(col5,user);
        cv.put(col6,pass);


        long status=sq.update(tablename,cv,col1 + "=" +id,null);
        if(status==-1)
        {
            return false;

        }
        else
        {
            return true;
        }



    }
    public boolean delete(String id){
        SQLiteDatabase sq=this.getWritableDatabase();
        long status=sq.delete(tablename,col1+"="+id,null);
        if(status==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

}
