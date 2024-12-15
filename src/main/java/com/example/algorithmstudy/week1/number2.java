package com.example.algorithmstudy.week1;

import java.util.*;

class Solution2 {
    public int[] solution2(int[][] board, int k){
        int[] answer = new int[2];

        int x = 0;
        int y = 0;
        int direction = 0; // 0 오른쪽 1 아래 2 왼쪽 3 위

        for (int i = 0; i < k; i++) {
            if (direction == 0) y++;
            else if (direction == 1) x++;
            else if (direction == 2) y--;
            else if (direction == 3) x--;

            // x, y가 board 내에서 벗어나거나 장애물이 있을 경우 이전 위치로 돌아감
            if (x < 0 || y < 0 || x >= board.length || y >= board.length || board[x][y] == 1) {
                if (direction == 0) y--;
                else if (direction == 1) x--;
                else if (direction == 2) y++;
                else if (direction == 3) x++;

                // 방향 90도 바꾸기
                direction = (direction + 1) % 4;
            }
        }

        answer[0] = x;
        answer[1] = y;

        return answer;
    }

    public static void main(String[] args){
        Solution2 T = new Solution2();
        int[][] arr1 = {{0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution2(arr1, 10)));
        int[][] arr2 = {{0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution2(arr2, 20)));
        int[][] arr3 = {{0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution2(arr3, 25)));

    }
}
