package com.example.apakahaku;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final String KEY_NAME = "menu";
    TextToSpeech suara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_hewan = findViewById(R.id.butoon_hewan);
        Button button_buah = findViewById(R.id.button_buah);

        suara = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    suara.setLanguage(new Locale("id","IDN"));
                }
            }
        });

        button_hewan.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameStarted.class);
            intent.putExtra(KEY_NAME, "hewan");
            suara.speak("Pada menu ini kamu akan menebak nama-nama hewan", TextToSpeech.QUEUE_FLUSH,null);
            startActivity(intent);
        });

        button_buah.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameStarted.class);
            intent.putExtra(KEY_NAME, "buah");
            suara.speak("Pada menu ini kamu akan menebak nama-nama buah", TextToSpeech.QUEUE_FLUSH,null);
            startActivity(intent);
        });

    }
}