package com.example.algorithmstudy.lessons029;

import java.util.*;
class Solution {
    public int solution(int[] nums, int m){
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
            if(nums[start] + nums[end] <= m) {
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
        System.out.println(T.solution(new int[]{90, 50, 70, 100, 60}, 140));
        System.out.println(T.solution(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90}, 100));
        System.out.println(T.solution(new int[]{68, 72, 30, 105, 55, 115, 36, 67, 119, 111, 95, 24, 25, 80, 55, 85, 75, 83, 21, 81}, 120));
    }
}