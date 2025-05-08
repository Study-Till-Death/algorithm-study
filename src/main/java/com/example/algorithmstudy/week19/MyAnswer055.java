package com.example.algorithmstudy.week19;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//백준 2579번 계단 오르기
public class MyAnswer055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int stairCount = Integer.parseInt(br.readLine());
        int[] stairScore = new int[stairCount + 1];
        int[] dp = new int[stairCount + 1];

        for (int i = 1; i <= stairCount; i++) {
            stairScore[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 0;
        dp[1] = stairScore[1];

        if(stairCount >= 2){
            dp[2] = stairScore[1] + stairScore[2];
        }

        for (int i = 3; i <= stairCount; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + stairScore[i - 1]) + stairScore[i];
        }

        System.out.println(dp[stairCount]);
    }
}
