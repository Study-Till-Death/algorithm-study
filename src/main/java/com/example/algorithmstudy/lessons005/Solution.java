package com.example.algorithmstudy.lessons005;

import java.util.*;

class Solution {
    public int solution(int[] nums){
        int answer = 0;

        ArrayList<Integer> topList = new ArrayList<Integer>();

        for(int i = 0; i < nums.length; i++){
            int prev = i == 0 ? Integer.MAX_VALUE : nums[i-1];
            int next = i == nums.length - 1 ? Integer.MAX_VALUE : nums[i+1];

            if(nums[i] > prev && nums[i] > next){
                topList.add(i);
            }
        }

        for(int top : topList){
            int count = 1;
            for(int i = top+1 ; i < nums.length ; i++){
                if(nums[i-1] <= nums[i]){
                    break;
                }
                count++;
            }

            for(int i = top-1 ; i > 0 ; i--){
                if(nums[i] >= nums[i+1]){
                    break;
                }
                count++;
            }

            if(answer < count){
                answer = count;
            }
        }
      
        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{1, 2, 1, 2, 3, 2, 1}));
        System.out.println(T.solution(new int[]{1, 1, 2, 3, 5, 7, 4, 3, 1, 2}));
        System.out.println(T.solution(new int[]{3, 2, 1, 3, 2, 4, 6, 7, 3, 1}));
        System.out.println(T.solution(new int[]{1, 3, 1, 2, 1, 5, 3, 2, 1, 1}));
    }
}