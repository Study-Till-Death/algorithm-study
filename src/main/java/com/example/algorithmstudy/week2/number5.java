package com.example.algorithmstudy.week2;

class Solution5 {
    public int solution5(int[] nums) {
        int answer = 0;

        int i = 0;
        int n = nums.length;

        while (i < n - 1) {
            int increase = 0;
            int decrease = 0;

            // 증가
            while (i < n - 1 && nums[i] < nums[i + 1]) {
                increase++;
                i++;
            }

            // 감소
            while (i < n - 1 && nums[i] > nums[i + 1]) {
                decrease++;
                i++;
            }

            // 증가 감소 둘 다 있으면 정답 갱신
            if (increase > 0 && decrease > 0) {
                if (increase + decrease + 1 > answer) {
                    answer = increase + decrease + 1;
                }
            }

            // 같은 값일 때는 다음으로
            while (i < n - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution5 T = new Solution5();
        System.out.println(T.solution5(new int[]{1, 2, 1, 2, 3, 2, 1}));
        System.out.println(T.solution5(new int[]{1, 1, 2, 3, 5, 7, 4, 3, 1, 2}));
        System.out.println(T.solution5(new int[]{3, 2, 1, 3, 2, 4, 6, 7, 3, 1}));
        System.out.println(T.solution5(new int[]{1, 3, 1, 2, 1, 5, 3, 2, 1, 1}));
    }
}