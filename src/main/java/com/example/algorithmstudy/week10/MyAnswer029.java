package com.example.algorithmstudy.week10;
import java.util.*;

public class MyAnswer029 {
    public int solution(int[] nums, int m){
        Arrays.sort(nums);
        int answer = 0;
        int left = 0;
        int right = nums.length-1;

        //정렬 후 최소값 최대값 가지고 비교 하면서 푸는 문제
        while (left <= right) {
            if (nums[left] + nums[right] <= m) {
                left++;
                right--;
            } else {
                //최소 최대 값의 합이 m을 넘는 경우는 오른쪽 값만 빼면 됨
                right--;
            }
            answer++;
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer029 T = new MyAnswer029();
        System.out.println(T.solution(new int[]{90, 50, 70, 100, 60}, 140));
        System.out.println(T.solution(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90}, 100));
        System.out.println(T.solution(new int[]{68, 72, 30, 105, 55, 115, 36, 67, 119, 111, 95, 24, 25, 80, 55, 85, 75, 83, 21, 81}, 120));
    }
}
