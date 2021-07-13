package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MemoryGame extends AppCompatActivity implements View.OnClickListener {

    private MemoryManager gManager;
    private Handler handler = new Handler();
    private ImageButton[][] cards;

    private int[] pics = {R.mipmap.pic0,
            R.mipmap.pic1,
            R.mipmap.pic2,
            R.mipmap.pic3,
            R.mipmap.pic4,
            R.mipmap.pic5,
            R.mipmap.pic6,
            R.mipmap.pic7,
            R.mipmap.pic8,
            R.mipmap.pic9
    };

    int i, j;
    private TextView tvPlayers[], tvScores[];
    String player1 = "Player1", player2 = "Player2";

    private Player[] players;
    private int k; // Who's turn
    public static final int ROW = 4;
    public static final int COL = 5;

    private int currI, currJ, oldI, oldJ, NumOfClicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

        NumOfClicks = 0;
        currI = -1;
        currJ = -1;
        oldI = -1;
        oldJ = -1;
        gManager = new MemoryManager(this);
        tvPlayers = new TextView[2];
        tvScores = new TextView[2];
        tvPlayers[0] = (TextView) findViewById(R.id.tvName1);
        tvPlayers[1] = (TextView) findViewById(R.id.tvName2);
        tvScores[0] = (TextView) findViewById(R.id.tvScore1);
        tvScores[1] = (TextView) findViewById(R.id.tvScore2);

        this.players = new Player[2];
        this.k = 0; //player one start

        players[0] = new Player(player1); //player num 1
        players[1] = new Player(player2); // player num 2
        tvPlayers[0].setText(players[0].getPlayerName());
        tvPlayers[1].setText(players[1].getPlayerName());
        tvScores[0].setText("0");
        tvScores[1].setText("0");



        String str1 = "";
        int resId;
        cards = new ImageButton[ROW][COL];
        for (int x = 0; x < cards.length; x++) {
            for (int y = 0; y < cards[x].length; y++) {
                str1 = "img" + x + y;
                resId = getResources().getIdentifier(str1, "id", getPackageName());

                cards[x][y] = (ImageButton) findViewById(resId);
                cards[x][y].setOnClickListener(this);
            }
        }
        Toast.makeText(getApplicationContext(), gManager.showMat().toString(), Toast.LENGTH_LONG).show();
        tvPlayers[0].setTextColor(Color.parseColor("#ff00ff"));
    }

    public void restartGame() {
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards[i].length; j++) {
                cards[i][j].setImageResource(R.mipmap.card);
                cards[i][j].setVisibility(View.VISIBLE);
                cards[i][j].setEnabled(true);
            }
        }
        players[0].setPlayerScore(0);
        players[1].setPlayerScore(0);
        tvScores[0].setText(players[0].getPlayerScore() + " ");
        tvScores[1].setText(players[1].getPlayerScore() + " ");
        tvPlayers[0].setTextColor(Color.parseColor("#ff00ff"));
        tvPlayers[1].setTextColor(Color.parseColor("#000000"));
        Toast.makeText(getApplicationContext(), gManager.showMat().toString(), Toast.LENGTH_LONG).show();
        NumOfClicks = 0;
        k = 0;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                break;
            case R.id.btnBack:
                BackAction(v);
                break;
            case R.id.btnRestart:
                gManager.restartGame();
                restartGame();
                break;
            default:
                boolean found = false;
                int i, j = 0;
                for (i = 0; i < cards.length && !found; i++) {
                    for (j = 0; j < cards[i].length && !found; j++) {
                        if ((ImageButton) v == cards[i][j]) {
                            found = true;
                            cards[i][j].setImageResource(pics[gManager.getNum(i, j)]);
                            currI = i;
                            currJ = j;
                            NumOfClicks++;
                        }
                    }
                }
        }
        if (NumOfClicks == 1) {
            oldI = currI;
            oldJ = currJ;
        }
        if (NumOfClicks == 2) {
            if (oldI == currI && oldJ == currJ) {
                Toast.makeText(getApplicationContext(), "can't press the same card twice!", Toast.LENGTH_LONG).show();
                NumOfClicks--;

            } else {
                if (gManager.isTwin(oldI, oldJ, currI, currJ)) {
                    players[k % 2].incScore();
                    tvScores[k % 2].setText(players[k % 2].getPlayerScore() + " ");
                } else {
                    k++;
                    final Runnable r = new Runnable() {
                        public void run() {
                            cards[currI][currJ].setImageResource(R.mipmap.card);
                            cards[oldI][oldJ].setImageResource(R.mipmap.card);
                        }
                    };
                    handler.postDelayed(r, 2000);
                }
                NumOfClicks = 0;
            }
        }
        if (gManager.isFinish()) {
            String winner;
            if (players[0].getPlayerScore() > players[1].getPlayerScore()) {
                winner = players[0].getPlayerName() + "won!";
            } else if (players[0].getPlayerScore() < players[1].getPlayerScore()) {
                winner = players[1].getPlayerName() + "won!";
            } else {
                winner = "game ended in a tie!";
            }
            Toast.makeText(getApplicationContext(), winner, Toast.LENGTH_LONG).show();
        }
        tvPlayers[k % 2].setTextColor(Color.parseColor("#ff00ff"));
        tvPlayers[(k % 2 + 1) % 2].setTextColor(Color.parseColor("#000000"));
    }

        public void BackAction(View v){
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
        }
    }




