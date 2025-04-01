package com.example.algorithmstudy.week14;

// 타일점프
class Solution41 {
    public int solution41(int[] nums){
        int n = nums.length;
        int jumps = 0;      // 점프 횟수
        int end = 0;        // 현재 점프 구간의 끝
        int farthest = 0;   // 다음 점프 구간의 최대 도달 가능 위치

        for (int i = 0; i < n - 1; i++) {
            // 점프해서 갈 수 있는 가장 먼 곳
            farthest = Math.max(farthest, i + nums[i]);

            // 점프 횟수 증가
            if (i == end) {
                jumps++;
                end = farthest;

                // 더 못가면
                if (end <= i) return -1;
            }
        }

        return end >= n - 1 ? jumps : -1;
    }

    public static void main(String[] args){
        Solution41 T = new Solution41();
        System.out.println(T.solution41(new int[]{2, 2, 1, 2, 1, 1}));
        System.out.println(T.solution41(new int[]{1, 0, 1, 1, 3, 1, 2, 1}));
        System.out.println(T.solution41(new int[]{2, 3, 1, 0, 1, 1, 2, 3, 1, 5, 1, 3, 1}));
        System.out.println(T.solution41(new int[]{1, 2, 1, 2, 1, 2, 1, 1, 3, 1, 2, 1}));
        System.out.println(T.solution41(new int[]{1, 3, 2, 1, 1, 2, 3, 1, 3, 1, 2, 3, 5, 1, 5, 1, 2, 1, 1}));
    }
}