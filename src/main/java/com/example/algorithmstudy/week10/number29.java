package com.example.algorithmstudy.week10;

import java.util.*;

class Solution29 {
    public int solution29(int[] nums, int m){
        Arrays.sort(nums);

        // 리스트로 변환
        List<Integer> people = new ArrayList<>();
        for (int num : nums) {
            people.add(num);
        }

        int boats = 0;
        int i = 0, j = people.size() - 1; // i - 가장 가벼운 사람의 인덱스, j - 가장 무거운 사람의 인덱스

        while (i <= j) {
            // 둘이 같이 탈 수 있으면 i 증가
            if (people.get(i) + people.get(j) <= m) {
                i++;
            }
            // 무거운 사람은 혼자 타야함
            j--;
            boats++;
        }

        return boats;
    }

    public static void main(String[] args){
        Solution29 T = new Solution29();
        System.out.println(T.solution29(new int[]{90, 50, 70, 100, 60}, 140));
        System.out.println(T.solution29(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90}, 100));
        System.out.println(T.solution29(new int[]{68, 72, 30, 105, 55, 115, 36, 67, 119, 111, 95, 24, 25, 80, 55, 85, 75, 83, 21, 81}, 120));
    }
}