package com.example.algorithmstudy.lessons011;

import java.util.*;
class Solution {
    public int solution(String s){
        int answer = 0;
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> countMap = new HashMap<>();
        HashMap<Integer, Character> resultMap = new HashMap<>();

        //문자 갯수 세기
        for(char c : chars) {
            int i = countMap.getOrDefault(c, 0);
            countMap.put(c, i+1);
        }

        //채우기
        for(Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            answer = calc(answer, entry.getValue(), entry.getKey(), resultMap);
        }

        return answer;
    }

    private int calc(int answer, int target, char c, HashMap<Integer, Character> resultMap) {
        //같은 갯수의 문자가 없는 경우, resultMap을 채우고 끝
        if(resultMap.getOrDefault(target, null) == null){
            resultMap.put(target, c);
            return answer;
        }

        //이미 다 빼버린 경우
        if(target < 1){
            return answer;
        }

        //횟수+1하고, 하나 빼서 다시 시도
        return calc(answer+1, target-1, c, resultMap);
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution("aaabbbcc"));
        System.out.println(T.solution("aaabbc"));
        System.out.println(T.solution("aebbbbc"));
        System.out.println(T.solution("aaabbbcccde"));
        System.out.println(T.solution("aaabbbcccdddeeeeeff"));
    }
}
