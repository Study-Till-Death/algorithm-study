package com.example.algorithmstudy.lessons025;

import java.util.*;
class Solution {
    public int solution(int[] score, int k){
        int answer = 0;

        //정렬하기
        Arrays.sort(score);

        outerLoop:
        //시작점을 최대한 앞으로 유지하면서
        for(int i = 0; i < score.length; i++){
            //조건에 만족하는 가장 작은 수 찾기
            for(int j = i + k-1 ; j < score.length; j++){
                //조건에 만족하는 수를 찾으면
                if(score[j] - score[i] <= 10){
                    //일단 시작수와 끝수를 더하고
                    answer = score[i] + score[j];
                    for(int jj = i+1; jj < j; jj++){
                        //시작수+1 ~ 시작수+k-1로 가장 작은 중간수의 합을 구한다
                        answer+=score[jj];
                    }
                    break outerLoop;
                }
            }
        }

        return answer/k;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{99, 97, 80, 91, 85, 95, 92}, 3));
        System.out.println(T.solution(new int[]{92, 90, 77, 91, 70, 83, 89, 76, 95, 92}, 4));
        System.out.println(T.solution(new int[]{77, 88, 78, 80, 78, 99, 98, 92, 93, 89}, 5));
        System.out.println(T.solution(new int[]{88, 99, 91, 89, 90, 72, 75, 94, 95, 100}, 5));
    }
}