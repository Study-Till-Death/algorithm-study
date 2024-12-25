package com.example.algorithmstudy.week2;

import java.util.*;

class Solution4 {
    public int[] solution4(int c, int r, int k) {
        int[] answer = new int[2];

        // 이미 사람이 다 찼다면 [0, 0] 반환
        if (k > c * r)
            return new int[]{0, 0};

        int[][] seat = new int[c][r];
        int x = 0, y = 0;
        int direction = 0; // 0: 우, 1: 하, 2: 좌, 3: 상

        for (int i = 1; i <= k; i++) {
            // 현재 좌표에 사람 넣어
            seat[x][y] = i;

            // k번째 사람이면 답 반환
            if (i == k) {
                answer[0] = x + 1;
                answer[1] = y + 1;
                return answer;
            }

            // 다음 좌표
            int nextX = x, nextY = y;

            if (direction == 0) { // 우
                nextY++;
            } else if (direction == 1) { // 하
                nextX++;
            } else if (direction == 2) { // 좌
                nextY--;
            } else if (direction == 3) { // 상
                nextX--;
            }

            if (nextX < 0 || nextY < 0 || nextX >= c || nextY >= r || seat[nextX][nextY] != 0) {
                // 방향 90도 바꾸기
                direction = (direction + 1) % 4;
                if (direction == 0) {
                    nextX = x;
                    nextY = y + 1;
                } else if (direction == 1) {
                    nextX = x + 1;
                    nextY = y;
                } else if (direction == 2) {
                    nextX = x;
                    nextY = y - 1;
                } else if (direction == 3) {
                    nextX = x - 1;
                    nextY = y;
                }
            }

            x = nextX;
            y = nextY;
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution4 T = new Solution4();
        System.out.println(Arrays.toString(T.solution4(6, 5, 12)));
        System.out.println(Arrays.toString(T.solution4(6, 5, 20)));
        System.out.println(Arrays.toString(T.solution4(6, 5, 30)));
        System.out.println(Arrays.toString(T.solution4(6, 5, 31)));
    }
}
