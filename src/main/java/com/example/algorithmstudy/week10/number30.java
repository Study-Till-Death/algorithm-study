package com.example.algorithmstudy.week10;

import java.util.*;

class Solution30 {
    public int solution30(int[] nums) {
        Arrays.sort(nums);

        boolean[] used = new boolean[nums.length]; // 물건 사용 여부 체크
        int count = 0;

        // 최대한 5kg에 가깝게 묶기
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) { // 이미 사용된 물건이면 스킵
                continue;
            }

            int currentWeight = nums[i];
            used[i] = true; // 현재 물건 사용 처리

            // 같이 옮길 수 있는 물건 찾기
            for (int j = nums.length - 1; j > i; j--) {
                if (!used[j] && currentWeight + nums[j] <= 5) {
                    currentWeight += nums[j];
                    used[j] = true; // 물건 사용 처리
                    break; // 최대치 했으면 끝
                }
            }
            count++;
        }

        return count;
    }

    public static void main(String[] args){
        Solution30 T = new Solution30();
        System.out.println(T.solution30(new int[]{2, 5, 3, 4, 2, 3}));
        System.out.println(T.solution30(new int[]{2, 3, 4, 5}));
        System.out.println(T.solution30(new int[]{3, 3, 3, 3, 3}));
    }
}