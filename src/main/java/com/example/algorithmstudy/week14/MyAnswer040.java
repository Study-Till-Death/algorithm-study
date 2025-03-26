package com.example.algorithmstudy.week14;
import java.util.*;

//문자열을 한자리 혹은 두자리로 자른다 1~26 에 포함되는 숫자가 아니면 안된다.
//02 같이 0으로 시작해서는 안된다.
//위의 조건을 만족하면 answer++ 를 하고 위의 조건을 하나도 만족하지 못하면 0을 리턴한다.
public class MyAnswer040 {
    public int solution(String s){
        if (s == null || s.length() == 0 || s.startsWith("0")) return 0; //불가능한 경우 바로 0 리턴

        int n = s.length();
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;

        for (int i = 2; i <= n; i++) {
            char oneChar = s.charAt(i - 1); //한 자리
            String twoChar = s.substring(i - 2, i); //두 자리

            int oneNumber = oneChar - '0';
            int twoNumber = Integer.parseInt(twoChar);

            if (oneNumber >= 1) { //1~9 체크
                dp[i] += dp[i - 1];
            }
            if (twoNumber >= 10 && twoNumber <= 26) { //10~26 체크
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

    public static void main(String[] args){
        MyAnswer040 T = new MyAnswer040();
        System.out.println(T.solution("25114"));
        System.out.println(T.solution("23251232"));
        System.out.println(T.solution("21020132"));
        System.out.println(T.solution("21350"));
        System.out.println(T.solution("120225"));
        System.out.println(T.solution("232012521"));
    }
}
