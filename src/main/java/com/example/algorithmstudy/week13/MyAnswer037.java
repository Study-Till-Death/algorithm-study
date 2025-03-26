package com.example.algorithmstudy.week13;
import java.util.*;

public class MyAnswer037 {
    int n;
    int [][] person;
    boolean [] selected;
    int minDiff;

    public int solution(int[][] cans){
        n = cans.length;
        selected = new boolean[n];
        person = cans;
        minDiff = Integer.MAX_VALUE;
        teamSelect(0,0);

        return minDiff;
    }

    //팀 선택 함수
    private void teamSelect(int idx, int count){
        if (count == n / 2) {
            getDiff();
            return;
        }
        if (idx >= n) return;

        selected[idx] = true;
        teamSelect(idx + 1, count + 1);

        selected[idx] = false;
        teamSelect(idx + 1, count);
    }

    //차이 계산 함수
    private void getDiff(){
        int whiteSum = 0;
        int blackSum = 0;

        for (int i = 0; i < n; i++) {
            if (selected[i]) whiteSum += person[i][0];
            else blackSum += person[i][1];
        }

        minDiff = Math.min(minDiff, Math.abs(whiteSum - blackSum));
    }

    public static void main(String[] args){
        MyAnswer037 T = new MyAnswer037();
        System.out.println(T.solution(new int[][]{{87, 84}, {66, 78}, {94, 94}, {93, 87}, {72, 92}, {78, 63}}));
        System.out.println(T.solution(new int[][]{{10, 20}, {15, 25}, {35, 23}, {55, 20}}));
        System.out.println(T.solution(new int[][]{{11, 27}, {16, 21}, {35, 21}, {52, 21}, {25, 33},{25, 32}, {37, 59}, {33, 47}}));
    }
}
