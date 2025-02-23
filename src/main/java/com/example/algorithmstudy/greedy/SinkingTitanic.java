package com.example.algorithmstudy.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class SinkingTitanic {
    public int solution(int[] nums, int m){
        int answer = 0;
        // 가장 무거운 애 처리 가능한지부터
        nums = Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();
        boolean[] isEscaped = new boolean[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            if (isEscaped[i]){ // 이미 탈출한 사람이면 스킵
                continue;
            }
            isEscaped[i] = true; // 일단 구명보트 입장
            for (int j = i + 1; j < nums.length; j++) { // 같이 탈 사람 중 제일 무거운 사람 찾기
                if (isEscaped[j]){ // 이미 탈출한 사람이면 스킵
                    continue;
                }
                if (nums[i] + nums[j] < m) { 
                    isEscaped[j] = true; // 탈출 처리
                    break;
                }
            }
            answer++; // 구명보트는 항상 1개씩만 증가
        }

        return answer;
    }

    // 비상 정답풀이는 가벼운 사람부터 넣음 왜지

    public static void main(String[] args){
        SinkingTitanic T = new SinkingTitanic();
        System.out.println(T.solution(new int[]{90, 50, 70, 100, 60}, 140));
        System.out.println(T.solution(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90}, 100));
        System.out.println(T.solution(new int[]{68, 72, 30, 105, 55, 115, 36, 67, 119, 111, 95, 24, 25, 80, 55, 85, 75, 83, 21, 81}, 120));
    }
}
