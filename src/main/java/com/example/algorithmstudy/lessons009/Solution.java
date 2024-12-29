package com.example.algorithmstudy.lessons009;

import java.util.HashMap;

public class Solution {
    public int solution(String s){
        int answer = -1;
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();

        for(char c : chars){
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }

        for(int i = 0 ; i < chars.length ; i++){
            if(map.get(chars[i]) == 1){
                answer = i+1;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution("statitsics"));
        System.out.println(T.solution("aabb"));
        System.out.println(T.solution("stringshowtime"));
        System.out.println(T.solution("abcdeabcdfg"));
    }
}
