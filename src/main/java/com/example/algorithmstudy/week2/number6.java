package com.example.algorithmstudy.week2;

import java.util.*;

class Solution6 {
    public int solution6(int[][] fruit) {
        int answer = 0;
        int n = fruit.length;
        boolean[] visited = new boolean[n]; // 교환했는지 체크

        for (int i = 0; i < n; i++) {
            if (visited[i]) // 교환했으면 패스
                continue;

            int minI = findMin(fruit[i]); // i 학생의 최소 과일의 인덱스
            int originI = fruit[i][minI]; // 그 인덱스의 값

            for (int j = 0; j < n; j++) {
                if (i == j || visited[j]) continue; // 자기 자신 or 교환 이미 했으면 패스

                int minJ = findMin(fruit[j]); // j 학생의 최소 과일의 인덱스
                int originJ = fruit[j][minJ]; // 그 인덱스 값

                // 같은 종류의 과일끼리 교환 막기
                if (minI == minJ)
                    continue;

                // 교환할 과일이 0개 보다 클 경우
                if (fruit[i][minJ] > 0 && fruit[j][minI] > 0) {
                    // 교환하기
                    fruit[i][minI]++;
                    fruit[j][minJ]++;
                    fruit[i][minJ]--;
                    fruit[j][minI]--;

                    // 교환 후 최솟값 인덱스 다시 찾기
                    int newMinI = findMin(fruit[i]);
                    int newMinJ = findMin(fruit[j]);

                    // 교환 후 최솟값이 교환 전보다 줄어들면 원래대로
                    if (fruit[i][newMinI] <= originI || fruit[j][newMinJ] <= originJ) {
                        fruit[i][minI]--;
                        fruit[j][minJ]--;
                        fruit[i][minJ]++;
                        fruit[j][minI]++;
                        continue;
                    }

                    // 교환하면 true로 바꾸기
                    visited[i] = true;
                    visited[j] = true;

                    break;
                }
            }
        }

        // 과일 최솟값 찾아서 더하기
        for (int[] f : fruit) {
            int min = f[0];
            for (int i : f) {
                if (i < min) {
                    min = i;
                }
            }
            answer += min;
        }

        return answer;
    }

    // 최소 과일의 인덱스 찾기
    private int findMin(int[] arr) {
        int minIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        Solution6 T = new Solution6();
        System.out.println(T.solution6(new int[][]{{10, 20, 30}, {12, 15, 20}, {20, 12, 15}, {15, 20, 10}, {10, 15, 10}}));
        System.out.println(T.solution6(new int[][]{{10, 9, 11}, {15, 20, 25}}));
        System.out.println(T.solution6(new int[][]{{0, 3, 27}, {20, 5, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}}));
        System.out.println(T.solution6(new int[][]{{3, 7, 20}, {10, 15, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}, {12, 12, 6}, {10, 20, 0}, {5, 10, 15}}));
    }
}
