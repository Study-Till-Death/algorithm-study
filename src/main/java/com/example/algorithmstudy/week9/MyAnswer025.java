package com.example.algorithmstudy.week9;
import java.util.*;

public class MyAnswer025 {
    public int solution(int[] score, int k){
        //스코어 최대값 100000
        int answer = 100000;

        //정렬
        Arrays.sort(score);
        int n = score.length;

        //k개 만큼 뽑아 보면서 10차이 이하면 평균값 내고 이전 평균값 보다 작은지 확인
        for (int i = 0; i <= n-k; i++){
            //arrays 에서 i부터 i+k개 만큼만 뽑아서 새 int 배열을 만듬
            int[] newArrayToGetScore = Arrays.copyOfRange(score, i, i+k);
            int min = newArrayToGetScore[0];
            int max = newArrayToGetScore[k-1];
            //점수 반드시 존재 하니까 예외 처리 필요 없음
            if(max - min <= 10){
                int sum = 0;
                for (int num : newArrayToGetScore){
                    sum += num;
                }
                int avg = sum / k;
                answer = Math.min(answer, avg);
            }
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer025 T = new MyAnswer025();
        System.out.println(T.solution(new int[]{99, 97, 80, 91, 85, 95, 92}, 3));
        System.out.println(T.solution(new int[]{92, 90, 77, 91, 70, 83, 89, 76, 95, 92}, 4));
        System.out.println(T.solution(new int[]{77, 88, 78, 80, 78, 99, 98, 92, 93, 89}, 5));
        System.out.println(T.solution(new int[]{88, 99, 91, 89, 90, 72, 75, 94, 95, 100}, 5));
    }
}
