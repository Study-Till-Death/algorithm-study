package com.example.algorithmstudy.week8;

import java.util.Arrays;

class Solution25 {
    public int solution25(int[] score, int k) {
        Arrays.sort(score);

        int minAvg = Integer.MAX_VALUE;

        for (int i = 0; i <= score.length - k; i++) {
            int minVal = score[i];
            int maxVal = score[i + k - 1];

            if (maxVal - minVal <= 10) {
                int sum = 0;

                for (int j = 0; j < k; j++) {
                    sum += score[i + j];
                }

                int avg = sum / k;
                minAvg = Math.min(minAvg, avg);
            }
        }

        return minAvg;
    }

    public static void main(String[] args){
        Solution25 T = new Solution25();
        System.out.println(T.solution25(new int[]{99, 97, 80, 91, 85, 95, 92}, 3));
        System.out.println(T.solution25(new int[]{92, 90, 77, 91, 70, 83, 89, 76, 95, 92}, 4));
        System.out.println(T.solution25(new int[]{77, 88, 78, 80, 78, 99, 98, 92, 93, 89}, 5));
        System.out.println(T.solution25(new int[]{88, 99, 91, 89, 90, 72, 75, 94, 95, 100}, 5));
    }
}