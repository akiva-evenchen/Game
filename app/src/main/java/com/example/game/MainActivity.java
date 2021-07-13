package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;


public class MainActivity extends AppCompatActivity {


    private ImageButton btnStart;
    private EditText edName1, edName2;
    private ImageView imgColor;
    private ConstraintLayout ly;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS)
                {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        ly = (ConstraintLayout)findViewById(R.id.ly);
        edName1 = (EditText) findViewById(R.id.edName1);
        edName2 = (EditText) findViewById(R.id.edName2);
        btnStart= (ImageButton) findViewById(R.id.btnGo);
        imgColor = (ImageView)findViewById(R.id.btnColor);
    }


    public void BackAction(View v)
    {
        //   Intent i = new Intent(this,MenuActivity.class);

        //    startActivity(i);

        Random rnd = new Random();
        ly.setBackgroundColor(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
    }

    public void PlayAction(View v)
    {

        if ((edName1.getText().toString().length()==0)  || (edName2.getText().toString().length()==0)
                || (edName1.getText().toString().toUpperCase().equals(edName2.getText().toString().toUpperCase())))
        {
            Toast.makeText(getApplicationContext(),"ERROR! ", Toast.LENGTH_LONG).show();
        }
        else
        {
            textToSpeech.speak(" Enjoy and Good Luck"+ edName1.getText().toString()+ "And " +edName2.getText().toString() , TextToSpeech.QUEUE_FLUSH, null);
            Intent i = new Intent(this,MenuActivity.class);

            startActivity(i);
        }
    }
}