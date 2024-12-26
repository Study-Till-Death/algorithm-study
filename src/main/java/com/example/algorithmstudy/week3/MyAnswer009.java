package com.example.algorithmstudy.week3;

import java.util.HashMap;

public class MyAnswer009 {
    public int solution(String s){
        int answer = 0;

        HashMap<Character, Integer> charCountMap = new HashMap<>();

        for(char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        for(int i = 0; i < s.length(); i++) {
            if(charCountMap.get(s.charAt(i)) == 1) {
                answer = i+1;
                break;
            }else{
                answer = -1;
            }
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer009 T = new MyAnswer009();
        System.out.println(T.solution("statitsics"));
        System.out.println(T.solution("aabb"));
        System.out.println(T.solution("stringshowtime"));
        System.out.println(T.solution("abcdeabcdfg"));
    }
}
