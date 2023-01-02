package com.example.apakahaku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String KEY_NAME = "menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_hewan = findViewById(R.id.butoon_hewan);
        Button button_buah = findViewById(R.id.button_buah);

        button_hewan.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameStarted.class);
            intent.putExtra(KEY_NAME, "hewan");
            startActivity(intent);
        });

        button_buah.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameStarted.class);
            intent.putExtra(KEY_NAME, "buah");
            startActivity(intent);
        });

    }
}