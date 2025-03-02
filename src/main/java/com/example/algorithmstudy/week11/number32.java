package com.example.algorithmstudy.week11;

import java.util.*;

// 꽃이 피는 최단 시간
class Solution32 {
    public int solution32(int[] plantTime, int[] growTime){
        int n = plantTime.length;
        int[][] flowers = new int[n][2];

        for (int i = 0; i < n; i++) {
            flowers[i][0] = growTime[i];
            flowers[i][1] = plantTime[i];
        }

        // 성장 시간이 긴 순서대로 정렬
        Arrays.sort(flowers, (a, b) -> Integer.compare(b[0], a[0]));

        int totalTime = 0;  // 현재까지 소요된 심는 시간
        int maxBloomTime = 0;  // 모든 꽃이 피는데 걸리는 최종 시간

        for (int[] flower : flowers) {
            totalTime += flower[1];  // 심는 시간 누적
            maxBloomTime = Math.max(maxBloomTime, totalTime + flower[0]);  // 최종 시간 업데이트
        }

        return maxBloomTime;
    }

    public static void main(String[] args){
        Solution32 T = new Solution32();
        System.out.println(T.solution32(new int[]{1, 3, 2}, new int[]{2, 3, 2}));
        System.out.println(T.solution32(new int[]{2, 1, 4, 3}, new int[]{2, 5, 3, 1}));
        System.out.println(T.solution32(new int[]{1, 1, 1}, new int[]{7, 3, 2}));
        System.out.println(T.solution32(new int[]{5, 7, 10, 15, 7, 3, 5}, new int[]{6, 7, 2, 10, 15, 6, 7}));
        System.out.println(T.solution32(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{7, 5, 4, 3, 2, 1, 6}));
    }
}
