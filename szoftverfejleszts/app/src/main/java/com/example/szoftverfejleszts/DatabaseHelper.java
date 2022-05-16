package com.example.szoftverfejleszts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "database.sqlite";
    private static final int DATABASE_VERSION = 1;
    Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public boolean jatekosHozzaad(String vnev, String knev, String csnev){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("VNev", vnev);
        values.put("KNev", knev);
        values.put("CsNev", csnev);
        long insert = db.insert("Player", null, values);
        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean eredmenyHozzaad(String vnev, String knev, int ido){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("VNev", vnev);
        values.put("KNev", knev);
        values.put("Ido", ido);
        long insert = db.insert("LeaderBoard", null, values);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor populateLeaderboard(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor data = db.rawQuery("select * from LeaderBoard ORDER BY Ido ", null);;
        return data;

    }
}
