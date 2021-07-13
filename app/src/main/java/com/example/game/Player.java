package com.example.game;

public class Player {
    // player class
    private String playerName;
    private int score;

    public Player(String name) {
        this.playerName=name;
        this.score=0;
    }

    public int getPlayerScore() {
        return this.score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void incScore () {
        this.score++;
    }

    public void setPlayerScore(int _score) {
        score = _score;
    }
    @Override
    public String toString() {
        return (this.playerName+""+this.score);
    }
}
