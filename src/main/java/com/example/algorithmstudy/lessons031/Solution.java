package com.example.algorithmstudy.lessons031;

import java.util.*;
class Solution {
    public int solution(int n, int[] nums){
        int answer = 0;

        int start = 0;
        int end = 0;

        //물이 끝까지 닿을 때까지
        while(end < n) {
            for(int j = start; j < nums.length; j++){
                //시작점에 닿는 스프링쿨러 중 가장 범위가 긴 친구 찾기
                if(j - nums[j] <= start){
                    end = Math.max(end, j + nums[j]);
                }

            }

            //한 바퀴 돌았는데도 end가 그대로면, 조건에 맞는게 없음
            if(start == end){
                return -1;
            }

            answer++;
            start = end;
        }

        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(8, new int[]{1, 1, 1, 2, 1, 1, 2, 1, 1}));
        System.out.println(T.solution(4, new int[]{1, 2, 2, 0, 0}));
        System.out.println(T.solution(5, new int[]{2, 0, 0, 0, 0, 2}));
        System.out.println(T.solution(11, new int[]{1, 2, 3, 1, 2, 1, 1, 2, 1, 1, 1, 1}));
    }
}