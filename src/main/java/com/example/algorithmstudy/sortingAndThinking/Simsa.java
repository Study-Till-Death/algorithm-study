package com.example.algorithmstudy.sortingAndThinking;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Simsa {
    public int solution(int[] score, int k) {
        // 일단 작은 순 정렬
        int[] sortedScore = Arrays.stream(score).sorted().toArray();
        
        return IntStream.range(0, score.length - k + 1) // 돌면서
                .filter(i -> sortedScore[i + k - 1] - sortedScore[i] <= 10) // 조건 만족
                .map(i ->(int) Math.floor(Arrays.stream(score, i, i + k).average().orElse(0))) // 거기서 부터 평균의 소숫점 버리고
                .findFirst().orElse(0);
    }

    public static void main(String[] args) {
        Simsa T = new Simsa();
        System.out.println(T.solution(new int[]{99, 97, 80, 91, 85, 95, 92}, 3));
        System.out.println(T.solution(new int[]{92, 90, 77, 91, 70, 83, 89, 76, 95, 92}, 4));
        System.out.println(T.solution(new int[]{77, 88, 78, 80, 78, 99, 98, 92, 93, 89}, 5));
        System.out.println(T.solution(new int[]{88, 99, 91, 89, 90, 72, 75, 94, 95, 100}, 5));
    }
}
