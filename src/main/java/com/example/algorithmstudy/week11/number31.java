package com.example.algorithmstudy.week11;

import java.util.*;

// 스프링쿨러
class Solution31 {
    public int solution31(int n, int[] nums) {
        int max = 1000000;
        int[] kler = new int[n + 1];
        Arrays.fill(kler, max); // 초기값을 큰 값으로 설정
        kler[0] = 0;  // 0번 위치를 덮기 위한 스프링쿨러 개수는 0

        // 스프링쿨러의 범위를 계산하여 리스트에 저장
        List<int[]> sprinklers = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (nums[i] > 0) { // 범위가 0보다 큰 스프링쿨러만 사용
                int left = Math.max(0, i - nums[i]);
                int right = Math.min(n, i + nums[i]);
                sprinklers.add(new int[]{left, right});
            }
        }

        // 시작 왼쪽 위치 기준으로 정렬
        sprinklers.sort(Comparator.comparingInt(a -> a[0]));

        // 최소 스프링쿨러 개수 계산
        for (int[] sprinkler : sprinklers) {
            int left = sprinkler[0];
            int right = sprinkler[1];

            // 덮을 수 있는 범위 내에서 값 갱신
            for (int j = left; j <= right; j++) {
                kler[j] = Math.min(kler[j], kler[left] + 1); // 최소 개수 갱신
            }
        }

        // 마지막 위치를 덮을 수 없는 경우
        return kler[n] == max ? -1 : kler[n];
    }

    public static void main(String[] args) {
        Solution31 T = new Solution31();
        System.out.println(T.solution31(8, new int[]{1, 1, 1, 2, 1, 1, 2, 1, 1}));
        System.out.println(T.solution31(4, new int[]{1, 2, 2, 0, 0}));
        System.out.println(T.solution31(5, new int[]{2, 0, 0, 0, 0, 2}));
        System.out.println(T.solution31(11, new int[]{1, 2, 3, 1, 2, 1, 1, 2, 1, 1, 1, 1}));
    }
}