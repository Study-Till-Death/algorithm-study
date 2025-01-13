package com.example.algorithmstudy.datastructure.week6;

import java.util.Arrays;

public class MaximumLengthContinuousSequence {
    public int solution(int[] nums){
        int answer = 1;
        int tmp = 1;
        // 그냥 정렬 때리고 계속해서 max 값 찾으면 될 거 같은데
        nums = Arrays.stream(nums).sorted().toArray();
        // 오름 차순 정렬
        for (int i=0; i<nums.length-1; i++){
            if (nums[i+1] == nums[i]+1){
                answer = Math.max(++tmp, answer);
                continue;
            }else if(nums[i+1] == nums[i]){
                continue;
            }
            tmp = 1;
        }

        return answer;
    }

    public static void main(String[] args){
        MaximumLengthContinuousSequence T = new MaximumLengthContinuousSequence();
        System.out.println(T.solution(new int[]{8, 1, 9, 3, 10, 2, 4, 0, 2, 3}));
        System.out.println(T.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0}));
        System.out.println(T.solution(new int[]{3, 3, 3, 3, 3, 3, 3, 3}));
        System.out.println(T.solution(new int[]{-3, -1, -2, 0, 3, 3, 5, 6, 2, 2, 1, 1}));
        System.out.println(T.solution(new int[]{-5, -3, -1, -4, 3, 3, 5, 6, 2, 2, 1, 1, 7}));
    }
}
