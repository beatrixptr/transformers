package com.example.szoftverfejleszts;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class Leaderboard extends AppCompatActivity {

    ListView leaderBoard;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        dbHelper = new DatabaseHelper(this);
        leaderBoard = findViewById(R.id.lb);
        populateListView();


    }
    private void populateListView() {

        //get the data and append to a list
        Cursor data = dbHelper.populateLeaderboard();
        ArrayList<String> listData = new ArrayList<>();
        if (data.moveToFirst()) {
            do {
                listData.add(data.getString(1) + " " + data.getString(2) + " " + data.getString(3));
            }while(data.moveToNext());
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        leaderBoard.setAdapter(adapter);
    }
}