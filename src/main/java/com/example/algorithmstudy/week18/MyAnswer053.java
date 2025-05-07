package com.example.algorithmstudy.week18;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//백준 4781번 사탕가게
public class MyAnswer053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while (!(line = br.readLine()).equals("0 0.00")) {
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            //100 곱해서 정수로 변경
            int m = (int) Math.round(Double.parseDouble(st.nextToken()) * 100);

            int[] dp = new int[m + 1];
            int[] weightOfSin = new int[n];
            int[] priceOfSin = new int[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                weightOfSin[i] = Integer.parseInt(st.nextToken());
                //100 곱해서 정수로 변경
                priceOfSin[i] = (int) Math.round(Double.parseDouble(st.nextToken()) * 100);
            }

            for (int i = 0; i < n; i++) {
                for (int j = priceOfSin[i]; j <= m; j++) {
                    dp[j] = Math.max(dp[j], dp[j - priceOfSin[i]] + weightOfSin[i]);
                }
            }

            System.out.println(dp[m]);
        }
    }
}
