package com.algorithm;

import java.util.Arrays;

public class a30_move {
    public int solution(int[] nums){
        int answer = 0;
        int low = 0;
        int high = nums.length - 1;

        Arrays.sort(nums);
        while (low <= high) {
            if (nums[low] + nums[high] <= 5) { // 가장 가벼운 거랑 무거운 거 합이 5보다 작으면
                low++;  // 가장 가벼운 거도 담음
            }
            high--; // 가장 무거운 거를 담음
            answer++; //이동횟수추가 .
        }
        return answer;
    }

    public static void main(String[] args){
        a30_move T = new a30_move();
        System.out.println(T.solution(new int[]{2, 5, 3, 4, 2, 3}));
        System.out.println(T.solution(new int[]{2, 3, 4, 5}));
        System.out.println(T.solution(new int[]{3, 3, 3, 3, 3}));
    }
}
