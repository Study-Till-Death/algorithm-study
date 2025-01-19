package com.example.algorithmstudy.week6;
import java.util.HashSet;

class Solution16 {
    public int solution16(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            // 중복 숫자 제거하고 set에 추가
            set.add(num);
        }

        int maxLength = 0;

        for (int num : set) {
            // 배열의 가장 작은 숫자인지 확인
            if (!set.contains(num - 1)) {
                int currentNum = num; // 현재 숫자 시작점으로
                int currentLength = 1; // 현재 수열 길이 1로 초기화

                // 연속된 숫자가 있으면 증가
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }

                // 최대 길이 갱신
                maxLength = Math.max(maxLength, currentLength);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Solution16 T = new Solution16();
        System.out.println(T.solution16(new int[]{8, 1, 9, 3, 10, 2, 4, 0, 2, 3}));
        System.out.println(T.solution16(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0}));
        System.out.println(T.solution16(new int[]{3, 3, 3, 3, 3, 3, 3, 3}));
        System.out.println(T.solution16(new int[]{-3, -1, -2, 0, 3, 3, 5, 6, 2, 2, 1, 1}));
        System.out.println(T.solution16(new int[]{-5, -3, -1, -4, 3, 3, 5, 6, 2, 2, 1, 1, 7}));
    }
}
