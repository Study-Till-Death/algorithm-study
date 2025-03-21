package com.example.algorithmstudy.week12;

// 가장 가까운 큰 수
class Solution35 {
    public int solution35(int n) {
        char[] digits = String.valueOf(n).toCharArray();

        if (!nextPermutation(digits)) {
            return -1; // 다음 순열이 없으면 -1 반환
        }

        return Integer.parseInt(new String(digits));
    }

    // 사전 순으로 가장 작은거 다음 찾기
    private boolean nextPermutation(char[] nums) {
        int i = nums.length - 2;

        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i < 0) return false; // 가장 큰 숫자면 다음 순열이 없음

        // nums[i]보다 큰 가장 작은 수를 뒤에서 찾기
        int j = nums.length - 1;
        while (nums[j] <= nums[i]) {
            j--;
        }

        swap(nums, i, j);
        reverse(nums, i + 1, nums.length - 1);

        return true;
    }

    // 교환하기
    private void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 뒤집기
    private void reverse(char[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Solution35 T = new Solution35();
        System.out.println(T.solution35(123));
        System.out.println(T.solution35(321));
        System.out.println(T.solution35(20573));
        System.out.println(T.solution35(27711));
        System.out.println(T.solution35(54312));
    }
}