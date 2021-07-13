package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HangmanGame extends AppCompatActivity {
    private Button btnBack;
    private HangmanManger hManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_game);

        btnBack = findViewById(R.id.btnBack);


    }

    public void BackAction(View v) {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}