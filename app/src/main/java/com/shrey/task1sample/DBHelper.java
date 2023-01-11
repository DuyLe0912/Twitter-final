package com.shrey.task1sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "userinfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table User(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists User");
    }

    public boolean insert(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        long result = db.insert("User",null,cv);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean checkAlreadyExist(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT username FROM User WHERE username = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0)
        {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }
    public boolean checkpassword(String username,String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT password FROM User WHERE username = '"+username+"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())
        {
            String pw = cursor.getString(0);
            if(pass.equals(pw)){
                cursor.close();
                return true;
            }
            else {
                cursor.close();
                return false;
            }
        }
        else{
            cursor.close();
            return false;}
    }

    public boolean updatePassword(String username,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password",pass);
        long result = db.update("User",cv,"username =?",new String[]{username});
        Log.i("result",""+result+"");
        if(result == -1){
            return false;
        }else
            return true;
    }
}
