package com.example.apakahaku;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity  implements View.OnClickListener {

    String jawabanBenar;
    Random random = new Random();


    TextView totalQuestionTextView,questionTextView;
    Button ans1,ans2,ans3,button_submit;
    ImageView gambar;
    TextToSpeech suara;

    int score = 0;
    int totalQuestion = 5;
    int currentQuestionIndex = 0;
    String selectedAnswer;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        suara = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    suara.setLanguage(new Locale("id","IDN"));
                }
            }
        });

        totalQuestionTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        gambar = findViewById(R.id.gambar);

        ans1 = findViewById(R.id.answer_1);
        ans2 = findViewById(R.id.answer_2);
        ans3 = findViewById(R.id.answer_3);
        button_submit = findViewById(R.id.submit);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        button_submit.setOnClickListener(this);

        loadNewQuestion();
    }


    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

        ans1.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
        ans2.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
        ans3.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));

        if(clickedButton.getId() == R.id.submit){

            if(selectedAnswer.equals(jawabanBenar)){
                score++;
            }

            currentQuestionIndex++;
            loadNewQuestion();

        }else{
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_orange));
            suara.speak(selectedAnswer, TextToSpeech.QUEUE_FLUSH,null);
        }
    }

    @SuppressLint("SetTextI18n")
    void loadNewQuestion(){
        int indexRandom;
        String[] pilihanAcak = new String[3];

        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }

        Bundle extras = getIntent().getExtras();
        String KEY_NAME = "menu";
        String menu = extras.getString(KEY_NAME);

        if(Objects.equals(menu, "hewan")){
            questionTextView.setText("Apa nama hewan ini?");
            indexRandom = random.nextInt(QuestionAnswer.hewan.length);
            jawabanBenar = QuestionAnswer.hewan[indexRandom];
            totalQuestionTextView.setText("Pertanyaan " + (currentQuestionIndex+1) +"/5");

            pilihanAcak = acakPilihan(QuestionAnswer.hewan, jawabanBenar);

            gambar.setImageResource(QuestionAnswer.gambarHewan[indexRandom]);
        }else if(Objects.equals(menu, "buah")){
            questionTextView.setText("Apa nama buah ini?");
            indexRandom = random.nextInt(QuestionAnswer.buah.length);
            jawabanBenar = QuestionAnswer.buah[indexRandom];
            totalQuestionTextView.setText("Pertanyaan " + (currentQuestionIndex+1) +"/5");

            pilihanAcak = acakPilihan(QuestionAnswer.buah, jawabanBenar);

            gambar.setImageResource(QuestionAnswer.gambarBuah[indexRandom]);
        }



        ans1.setText(pilihanAcak[0]);
        ans2.setText(pilihanAcak[1]);
        ans3.setText(pilihanAcak[2]);
    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Lulus";
            suara.speak("Selamat kamu lulus", TextToSpeech.QUEUE_FLUSH,null);
        }else{
            passStatus = "Gagal";
            suara.speak("Maaf kamu gagal", TextToSpeech.QUEUE_FLUSH,null);
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Skore kamu "+ score+" dari "+ totalQuestion)
                .setPositiveButton("Ulangi",(dialogInterface, i) -> restartQuiz() )
                .setNegativeButton("Menu",(dialogInterface, i) -> menuQuiz() )
                .setCancelable(false)
                .show();
    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

    void menuQuiz(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public static String[] acakPilihan(String[] hewan, String jawabanBenar) {
        String[] pilihanAcak = new String[3];
        int index = 0;
        Random random = new Random();
        while (index < 3) {
            int indexRandom = random.nextInt(hewan.length);
            String pilihan = hewan[indexRandom];
            boolean isExist = false;
            for (String s : pilihanAcak) {
                if (s != null && s.equals(pilihan)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                pilihanAcak[index] = pilihan;
                index++;
            }
        }

        int acak = random.nextInt(3);
        pilihanAcak[acak] = jawabanBenar;

        return pilihanAcak;
    }

}