package com.example.algorithmstudy.week14;

// 알파코드
class Solution40 {
    public int solution40(String s){
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // 시작

        for(int i = 1; i <= n; i++) {
            // 한 자리 숫자(1 ~ 9)
            if(s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            // 두 자리 숫자(10 ~ 26)
            if(i >= 2) {
                int num = Integer.parseInt(s.substring(i - 2, i));
                if(num >= 10 && num <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args){
        Solution40 T = new Solution40();
        System.out.println(T.solution40("25114"));
        System.out.println(T.solution40("23251232"));
        System.out.println(T.solution40("21020132"));
        System.out.println(T.solution40("21350"));
        System.out.println(T.solution40("120225"));
        System.out.println(T.solution40("232012521"));
    }
}