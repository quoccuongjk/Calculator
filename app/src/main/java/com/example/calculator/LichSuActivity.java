package com.example.calculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class LichSuActivity extends AppCompatActivity {
    TextView tv_gtri;
    String[] dulieu;
    String text="";
    Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);
        Intent intent = getIntent();
        dulieu = intent.getStringArrayExtra("lichsu");
        for (int i = 0; i<dulieu.length;i++) {
            text = text+dulieu[i]+"\n";
        }

        tv_gtri = findViewById(R.id.tv_gtri);

        tv_gtri.setText(text);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lịch sử");
        actionBar.setDisplayHomeAsUpEnabled(true);


    }



}