package com.algorithm;

import java.util.Arrays;

public class a29_titanic {
    public int solution(int[] nums, int m){
        int answer = 0;
        int low = 0;
        int high = nums.length - 1;

        Arrays.sort(nums);
        while (low <= high) {
            if (nums[low] + nums[high] <= m) { // 가장 가벼운 애랑 무거운 애 합이 m보다 작으면
                low++;  // 가장 가벼운 애도 담음
            }
            high--; // 가장 무거운 애를 담음
            answer++; //보트추가 .
        }

        return answer;
    }

    public static void main(String[] args){
        a29_titanic T = new a29_titanic();
        System.out.println(T.solution(new int[]{90, 50, 70, 100, 60}, 140));
        System.out.println(T.solution(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90}, 100));
        System.out.println(T.solution(new int[]{68, 72, 30, 105, 55, 115, 36, 67, 119, 111, 95, 24, 25, 80, 55, 85, 75, 83, 21, 81}, 120));
    }
}
