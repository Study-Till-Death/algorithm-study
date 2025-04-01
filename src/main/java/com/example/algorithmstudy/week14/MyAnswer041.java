package com.example.algorithmstudy.week14;
import java.util.*;

public class MyAnswer041 {
    public int solution(int[] nums){
        int n = nums.length;
        if (n == 0 || nums[0] == 0) return -1;

        int jumpCount = 0; //점프 횟수
        int reachEnd = 0; //갈 수 있는 가장 먼 거리
        int jumpingEndLocation = 0; //점프 끝난 시점

        for (int i = 0; i < n - 1; i++) {
            reachEnd = Math.max(reachEnd, i + nums[i]);

            if (i == jumpingEndLocation) {
                jumpCount++;
                jumpingEndLocation = reachEnd;

                if (jumpingEndLocation >= n - 1) {
                    return jumpCount;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        MyAnswer041 T = new MyAnswer041();
        System.out.println(T.solution(new int[]{2, 2, 1, 2, 1, 1}));
        System.out.println(T.solution(new int[]{1, 0, 1, 1, 3, 1, 2, 1}));
        System.out.println(T.solution(new int[]{2, 3, 1, 0, 1, 1, 2, 3, 1, 5, 1, 3, 1}));
        System.out.println(T.solution(new int[]{1, 2, 1, 2, 1, 2, 1, 1, 3, 1, 2, 1}));
        System.out.println(T.solution(new int[]{1, 3, 2, 1, 1, 2, 3, 1, 3, 1, 2, 3, 5, 1, 5, 1, 2, 1, 1}));
    }
}
