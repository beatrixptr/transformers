package com.example.szoftverfejleszts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button b1, b2;
    private EditText vnev, knev, csnev;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        vnev = findViewById(R.id.vnev);
        knev = findViewById((R.id.knev));
        csnev = findViewById(R.id.csnev);
        dbHelper = new DatabaseHelper(this);

    }

    public void openLeaderboard(View v){
        Intent i;
        i = new Intent(this, Leaderboard.class);
        startActivity(i);
    }


    public void hozzaadJatekos(View v){
        String vn = vnev.getText().toString();
        String kn = knev.getText().toString();
        String csn = csnev.getText().toString();
        boolean igen = dbHelper.jatekosHozzaad(vn, kn, csn);
        if(igen){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }
        Bundle extras = new Bundle();
        extras.putString("extra_vnev", vn);
        extras.putString("extra_knev", kn);
        Intent i;
        i = new Intent(this, Foprogram.class);
        i.putExtras(extras);
        startActivity(i);
    }
}