package com.example.algorithmstudy.week4;
import java.util.*;

//같은 빈도수 만들기
public class MyAnswer010 {
    public int[] solution(String s){
        int[] answer = new int[5];

        HashMap<Character, Integer> charCountMap = new HashMap<>();

        for(char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        int maxNum = 0;
        for (char c : charCountMap.keySet()) {
            if (charCountMap.get(c) > maxNum) {
                maxNum = charCountMap.get(c);
            }
        }

        //반복문 돌리려다가 어차피 빈도수는 글자수를 더해서 맞춰야하고 글자수 5개 고정이면 이게 젤 빠르지않나?
        answer[0] = maxNum - charCountMap.getOrDefault('a',0);
        answer[1] = maxNum - charCountMap.getOrDefault('b',0);
        answer[2] = maxNum - charCountMap.getOrDefault('c',0);
        answer[3] = maxNum - charCountMap.getOrDefault('d',0);
        answer[4] = maxNum - charCountMap.getOrDefault('e',0);

        return answer;
    }

    public static void main(String[] args){
        MyAnswer010 T = new MyAnswer010();
        System.out.println(Arrays.toString(T.solution("aaabc")));
        System.out.println(Arrays.toString(T.solution("aabb")));
        System.out.println(Arrays.toString(T.solution("abcde")));
        System.out.println(Arrays.toString(T.solution("abcdeabc")));
        System.out.println(Arrays.toString(T.solution("abbccddee")));
    }
}
