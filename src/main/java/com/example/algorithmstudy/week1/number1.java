package com.example.algorithmstudy.week1;

import java.util.Arrays;

class Solution1 {
    public char[] solution1(int n, int[][] ladder) {
        // 답이 틀려서 권재혁씨한테 뭐가 문제일까요 했는데 권재혁씨가 설명을 해줬는데 이해를 못해서 지피티한테 물어봤어요..

        char[] answer = new char[n];
        for (int i = 0; i < n; i++) {
            answer[i] = (char) ('A' + i);
        }

        for (int[] i : ladder) {
            for (int j = 0; j < i.length; j++) {
                if (i[j] < answer.length) {
                    // 값 임시 저장
                    char temp = answer[i[j] - 1];
                    // 오른쪽 값을 왼쪽으로
                    answer[i[j] - 1] = answer[i[j]];
                    // 임시 저장한 값을 오른쪽으로
                    answer[i[j]] = temp;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution1 T = new Solution1();
        System.out.println(Arrays.toString(T.solution1(5, new int[][]{{1, 3}, {2, 4}, {1, 4}})));
        System.out.println(Arrays.toString(T.solution1(7, new int[][]{{1, 3, 5}, {1, 3, 6}, {2, 4}})));
        System.out.println(Arrays.toString(T.solution1(8, new int[][]{{1, 5}, {2, 4, 7}, {1, 5, 7}, {2, 5, 7}})));
        System.out.println(Arrays.toString(T.solution1(12, new int[][]{{1, 5, 8, 10}, {2, 4, 7}, {1, 5, 7, 9, 11}, {2, 5, 7, 10}, {3, 6, 8, 11}})));
    }
}
