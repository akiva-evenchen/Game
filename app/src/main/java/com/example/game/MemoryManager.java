package com.example.game;

import java.util.ArrayList;
import java.util.Random;

public class MemoryManager {

    Random rnd = new Random();
    private MemoryGame mActivity;
    private int[][] matNums;

    public MemoryManager(MemoryGame _mActivity) {
        mActivity = _mActivity;
        matNums = new int[4][5];

        restartGame();
    }

    public void restartGame() {
        int i, j, x;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (i = 0; i <= 9; i++) {
            arr.add(i);
            arr.add(i);
        }
        for (i = 0; i < this.matNums.length; i++) {
            for (j = 0; j < this.matNums[i].length; j++) {
                x = rnd.nextInt(arr.size());
                matNums[i][j] = arr.get(x);
                arr.remove(x);
            }
        }
    }

    public String showMat() {
        String str = "";
        int i,j;
        for (i=0;i<this.matNums.length;i++) {
            for (j=0;j<this.matNums[i].length;j++)
                str+=this.matNums[i][j]+ " ";
            str+="\n";
        }
        return str;
    }

    public boolean isTwin(int oldI, int oldJ, int currI, int currJ) {
        if (matNums[oldI][oldJ] == matNums[currI][currJ]) {
            matNums[oldI][oldJ] = -1;
            matNums[currI][currJ] = -1;
            return true;
        }
        return false;
    }

    public boolean isFinish() {
        boolean finish = true;
        int i,j;
        for (i=0;i<this.matNums.length && finish;i++)
            for (j=0;j<this.matNums[i].length && finish;j++)
            {
                if (this.matNums[i][j]!=-1)
                    finish=false;
            }
        return finish;

    }

    public int getNum(int i ,int j) {
        return this.matNums[i][j];
    }





}
