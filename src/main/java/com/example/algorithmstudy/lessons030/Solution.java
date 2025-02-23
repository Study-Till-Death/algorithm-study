package com.example.algorithmstudy.lessons030;

import java.util.*;
class Solution {
    public int solution(int[] nums){
        int answer = 0;

        //nums의 시작과 끝 인덱스 정의하기
        int start = 0;
        int end = nums.length - 1;

        //몸무게 순으로 정렬하기
        Arrays.sort(nums);

        //시작과 끝 인덱스가 만날때까지
        while(start <= end){
            //젤 가벼운 친구, 젤 무거운 친구 같이 탈 수 있으면 같이 태워보내기
            //같이 못타면 무거운 친구만 태워보내기
            // m=5하면 29번 문제와 같다
            if(nums[start] + nums[end] <= 5) {
                answer++;

                start++;
                end--;
            } else {
                answer++;

                end--;
            }
        }

        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{2, 5, 3, 4, 2, 3}));
        System.out.println(T.solution(new int[]{2, 3, 4, 5}));
        System.out.println(T.solution(new int[]{3, 3, 3, 3, 3}));
    }
}