package com.algorithm;

import java.util.Arrays;

public class a10_createTheSameFrequency {
    public int[] solution(String s){
        int maxF = getMaxFrequency(s); // 가장 큰 빈도 수 구하기
        int[] answer = new int[]{maxF,maxF,maxF,maxF,maxF}; // 최대치로 미리 채움
        char[] splitArray = s.toCharArray();
        for (int i = 0; i < splitArray.length; i++) {
            answer[splitArray[i]-97]--; // 배열에 해당 문자가 나올 때마다 채운 수 --
        }
        
        return answer;
    }

    public int getMaxFrequency(String s) {
        int max = 0;
        String[] temp;

        for (int i = 0; i < 5; i++) {
            temp = s.split(String.valueOf((char)(97+i)));
            if (max<temp.length-1) max=temp.length-1;
        }
        
        return max;
    }


    public static void main(String[] args){
        a10_createTheSameFrequency T = new a10_createTheSameFrequency();
        System.out.println(Arrays.toString(T.solution("aaabc")));
        System.out.println(Arrays.toString(T.solution("aabb")));
        System.out.println(Arrays.toString(T.solution("abcde")));
        System.out.println(Arrays.toString(T.solution("abcdeabc")));
        System.out.println(Arrays.toString(T.solution("abbccddee")));
    }
}
