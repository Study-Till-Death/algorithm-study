package com.example.algorithmstudy.hashing.week4;

import java.util.Arrays;
import java.util.HashMap;

public class Same {
    public int[] solution(String s){
        int[] answer = new int[5];
        // 가장 많은 빈도수 구하기
        // 가장 많은 빈도수 - 각 빈도수
        HashMap<Character, Integer> map = new HashMap<>();
        int maxCount = 0;
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
            maxCount = Math.max(maxCount, map.get(c));
        }
        for (int i = 0; i <5; i++) {
            answer[i] = maxCount - map.getOrDefault((char)(i+97) , 0);
        }
        return answer;
    }

    public static void main(String[] args){
        Same T = new Same();
        System.out.println(Arrays.toString(T.solution("aaabc")));
        System.out.println(Arrays.toString(T.solution("aabb")));
        System.out.println(Arrays.toString(T.solution("abcde")));
        System.out.println(Arrays.toString(T.solution("abcdeabc")));
        System.out.println(Arrays.toString(T.solution("abbccddee")));
    }
}
