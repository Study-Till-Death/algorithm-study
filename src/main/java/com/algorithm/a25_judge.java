package com.algorithm;

import java.util.Arrays;

public class a25_judge {
    public int solution(int[] score, int k){
        int answer = 0;
        Arrays.sort(score); // 오름차순 정렬
        for (int i = 0; i < score.length-k; i++) {
            if (score[i+k-1]-score[i]<=10) { // 제일큰점수-제일낮은점수 <= 10 인경우만 평균냄
                answer = getAverage(answer, score, i, i+k-1);
            }
        }
        return answer;
    }

    public int getAverage(int answer, int[] score, int start, int end){
        int sum = 0;
        int avg;
        for (int i = start; i <= end; i++) {
            sum += score[i]; // 심사위원들 점수 모두 합
        }

        avg = (int) Math.floor(sum/(end-start+1)); // 평균 구하고 소수점 이하 버림

        if (answer>avg || answer==0) return avg; // answer에 첫 값 넣는 경우거나, avg가 answer보다 작은 경우
        return answer;
    }

    public static void main(String[] args){
        a25_judge T = new a25_judge();
        System.out.println(T.solution(new int[]{99, 97, 80, 91, 85, 95, 92}, 3));
        System.out.println(T.solution(new int[]{92, 90, 77, 91, 70, 83, 89, 76, 95, 92}, 4));
        System.out.println(T.solution(new int[]{77, 88, 78, 80, 78, 99, 98, 92, 93, 89}, 5));
        System.out.println(T.solution(new int[]{88, 99, 91, 89, 90, 72, 75, 94, 95, 100}, 5));
    }
}
