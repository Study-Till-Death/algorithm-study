package com.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class a16_maxConsecutiveNumberSubSequence {
    public int solution(int[] nums){
        int answer = 1; // 최대연속수열개수
        ArrayList<Integer> list = new ArrayList<>();

        Arrays.sort(nums); // 배열 소트

        for (int i = 0; i < nums.length; i++) {
            if (i==0 || i!=0 && nums[i-1]!=nums[i]) list.add(nums[i]); // 첫번째 숫자거나 이전에 저장한 숫자와 다른 경우
            // 마지막 반복인 경우 정산, 마지막 반복이 아닌 경우 - 현재숫자와 다음숫자가 연속수열이 아니면서 현재숫자!=다음숫자인경우 정산
            if (i == nums.length-1 || (i!=nums.length-1 && nums[i]+1!=nums[i+1] && nums[i]!=nums[i+1])) {
                if (answer<list.size()) answer=list.size();
                list = new ArrayList<>();
            }
        }
        return answer;
    }

    public static void main(String[] args){
        a16_maxConsecutiveNumberSubSequence T = new a16_maxConsecutiveNumberSubSequence();
        System.out.println(T.solution(new int[]{8, 1, 9, 3, 10, 2, 4, 0, 2, 3}));
        System.out.println(T.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0}));
        System.out.println(T.solution(new int[]{3, 3, 3, 3, 3, 3, 3, 3}));
        System.out.println(T.solution(new int[]{-3, -1, -2, 0, 3, 3, 5, 6, 2, 2, 1, 1}));
        System.out.println(T.solution(new int[]{-5, -3, -1, -4, 3, 3, 5, 6, 2, 2, 1, 1, 7}));
    }
}
