package com.example.algorithmstudy.week19;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//백준 1695번 팰린드롬 만들기
public class MyAnswer057 {
    static int numCount;
    static int[] numArr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numCount = Integer.parseInt(br.readLine());
        numArr = new int[numCount];
        dp = new int[numCount][numCount];
        for (int[] nums : dp) {
            Arrays.fill(nums, -1);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numCount; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = numCount - 1;
        System.out.println(checkPalindrome(left, right));
    }
    static int checkPalindrome (int left, int right) {
        if(left > right) return 0;

        if(dp[left][right] != -1) return dp[left][right];

        if(numArr[left] == numArr[right]) {
            dp[left][right] = checkPalindrome(left + 1, right-1);
        }else{
            dp[left][right] = Math.min(checkPalindrome(left + 1, right) + 1, checkPalindrome(left, right-1) + 1);
        }

        return dp[left][right];
    }
}
