package com.example.algorithmstudy.week18;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//백준 2073번 수도배관공사
public class MyAnswer054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[] lengths = new int[P];
        int[] capacities = new int[P];
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            lengths[i] = Integer.parseInt(st.nextToken());
            capacities[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[D + 1];
        dp[0] = Integer.MAX_VALUE;

        for (int i = 0; i < P; i++) {
            int length = lengths[i];
            int capacity = capacities[i];
            for (int j = D; j >= length; j--) {
                dp[j] = Math.max(dp[j], Math.min(dp[j - length], capacity));
            }
        }

        System.out.println(dp[D]);
    }
}
