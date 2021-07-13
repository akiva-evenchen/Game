package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btnMemory, btnHangman, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnMemory = findViewById(R.id.btnMemory);
        btnHangman = findViewById(R.id.btnHangman);
        btnExit = findViewById(R.id.btnExit);

    }

    public void MemoryAction(View v) {
        Intent i = new Intent(this, MemoryGame.class);
        startActivity(i);
    }

    public void HangmanAction(View v) {
        Intent i = new Intent(this, HangmanGame.class);
        startActivity(i);
    }

}