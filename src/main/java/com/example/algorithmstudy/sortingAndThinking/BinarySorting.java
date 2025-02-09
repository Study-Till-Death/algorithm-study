package com.example.algorithmstudy.sortingAndThinking;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class BinarySorting {
    public int[] solution(int[] nums) {
        int[] answer;
        int[] countArr = new int[nums.length];
        // 2로 나누고 나머지 있으면 카운트 추가 반복 0될 때 까지
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            int count = 0;
            while (n > 0) {
                if (n % 2 != 0) {
                    count++;
                }
                n /= 2; // 반토막
            }
            countArr[i] = count;
        }
        // 정렬
        answer = IntStream.range(0, nums.length)
                .boxed()
                .sorted(Comparator.comparingInt((Integer i) -> countArr[i])
                        .thenComparingInt(i -> nums[i]))
                .mapToInt(i -> nums[i])
                .toArray();

        return answer;
    }

    public static void main(String[] args) {
        BinarySorting T = new BinarySorting();
        System.out.println(Arrays.toString(T.solution(new int[]{5, 6, 7, 8, 9})));
        System.out.println(Arrays.toString(T.solution(new int[]{5, 4, 3, 2, 1})));
        System.out.println(Arrays.toString(T.solution(new int[]{12, 5, 7, 23, 45, 21, 17})));
    }
}
