package com.example.game;

import java.util.Random;

public class HangmanManger {

    private String arr[] = {
            "icebox",
            "buffalo",
            "oxygen",
            "flopping",
            "kilobyte",
            "jackpot",
            "peekaboo",
            "thumbscrew",
            "abruptly",
            "razzmatazz"
    };

    public String getWord() {
        Random rnd = new Random();
        return arr[rnd.nextInt(9)];
    }

    public String displayWord(String word) {
        String disWrd = "";
        for (int i=0; i<word.length() - 1 ; i++)
            disWrd += "_ ";
        disWrd += "_";
        return disWrd;
    }


}
