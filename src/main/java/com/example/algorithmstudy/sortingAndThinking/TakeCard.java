package com.example.algorithmstudy.sortingAndThinking;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TakeCard {
    public int solution(int[] nums, int k) {
        int answer = 0;
        // 큰 순서로 정렬 + 2개씩 나눠서 차이가 클 때마다 바꾸면 최대값일 듯
        // 큰 순서로 정렬
        int[] sortedNums = Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();

        // 차이가 큰 순서대로 각 라운드별 고를 숫자들 정렬
        PriorityQueue<nums> roundNums = new PriorityQueue<>(
                Comparator.comparingInt(a -> -(a.num1 - a.num2))
        );

        for (int i = 0; i < nums.length; i += 2) {
            roundNums.add(new nums(sortedNums[i], sortedNums[i + 1]));
        }

        while (!roundNums.isEmpty()) {
            if (k > 0) {
                answer += roundNums.poll().num1;
                k--;
                continue;
            }
            answer += roundNums.poll().num2;
        }


        return answer;
    }

    private class nums {
        int num1;
        int num2;

        public nums(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }
    }

    public static void main(String[] args) {
        TakeCard T = new TakeCard();
        System.out.println(T.solution(new int[]{7, 8, 5, 12, 3, 1, 3, 1, 1, 12}, 2));
        System.out.println(T.solution(new int[]{8, 2, 12, 12, 12, 12, 2, 2}, 2));
        System.out.println(T.solution(new int[]{3, 7, 12, 3, 3, 5, 7, 8, 9, 11, 23, 4, 6, 7}, 3));
        System.out.println(T.solution(new int[]{12, 34, 56, 23, 22, 34, 55, 45, 24, 23, 45, 55, 55, 23, 11, 12, 23, 12}, 3));
        System.out.println(T.solution(new int[]{14, 15, 20, 11, 10, 20, 20, 12, 9, 22, 27, 25, 30, 19}, 3));
    }
}
