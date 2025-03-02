package com.example.algorithmstudy.greedy;

import java.util.List;
import java.util.stream.IntStream;

public class flower {
    public int solution(int[] plantTime, int[] growTime) {
        int answer = 0;
        // 일단 느낌상은
        // 자라는 시간이 긴 것부터 심어야 할 듯
        // 자라는 시간이 같다면 심는 시간이 적은 것
        List<Integer> seeds = IntStream.range(0, plantTime.length)
                .boxed()
                .sorted((a, b) -> growTime[b] != growTime[a] ? growTime[b] - growTime[a] : plantTime[a] - plantTime[b])
                .toList();
        int now = 0;
        for (Integer idx : seeds) {
            now += plantTime[idx]; // 심는 시간 추가
            answer = Math.max(answer, now + growTime[idx]); // 자라는 시간 고려
        }


        return answer;
    }

    public static void main(String[] args) {
        flower T = new flower();
        System.out.println(T.solution(new int[]{1, 3, 2}, new int[]{2, 3, 2}));
        System.out.println(T.solution(new int[]{2, 1, 4, 3}, new int[]{2, 5, 3, 1}));
        System.out.println(T.solution(new int[]{1, 1, 1}, new int[]{7, 3, 2}));
        System.out.println(T.solution(new int[]{5, 7, 10, 15, 7, 3, 5}, new int[]{6, 7, 2, 10, 15, 6, 7}));
        System.out.println(T.solution(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{7, 5, 4, 3, 2, 1, 6}));
    }
}
