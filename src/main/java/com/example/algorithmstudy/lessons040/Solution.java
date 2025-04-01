package com.example.algorithmstudy.lessons040;

import java.util.*;

class Solution {
    String string;
    int answer;

    public int solution(String s){
        string = s;
        answer = 0;

        dfs(0);

        return answer;
    }

    private void dfs(int index) {
        //0으로 시작하면 중단
        if(index < string.length() && string.charAt(index) == '0') {
            return;
        }

        //성공
        if(index == string.length()-1 || index == string.length()) {
            answer++;
            return;
        }

        //dfs 1
        dfs(index + 1);
        int temp = Integer.parseInt(string.substring(index, index + 2));
        //26이하면 dfs 2
        if(temp <= 26) {
            dfs(index + 2);
        }

    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution("25114"));
        System.out.println(T.solution("23251232"));
        System.out.println(T.solution("21020132"));
        System.out.println(T.solution("21350"));
        System.out.println(T.solution("120225"));
        System.out.println(T.solution("232012521"));
    }
}