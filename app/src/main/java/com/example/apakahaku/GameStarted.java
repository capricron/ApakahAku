package com.example.apakahaku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameStarted extends AppCompatActivity {

    private String menu;
    private final String KEY_NAME = "menu";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_started);

        TextView judul_start = findViewById(R.id.judul_started);
        Bundle extras = getIntent().getExtras();
        menu = extras.getString(KEY_NAME);
        judul_start.setText("Pada menu ini kamu akan menebak nama " + menu);

        Button button_start = findViewById(R.id.button_start);

        button_start.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra(KEY_NAME, menu);
            startActivity(intent);
        });

    }
}