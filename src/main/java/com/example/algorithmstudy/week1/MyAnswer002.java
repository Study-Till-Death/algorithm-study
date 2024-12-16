package com.example.algorithmstudy.week1;
import java.util.*;
class MyAnswer002 {
    public int[] solution(int[][] board, int k){
        int[] answer = new int[2];

        char direction = 'R'; // R right L left D down U up
        int seconds = 0;
        int x = 0;
        int y = 0;
        while(seconds < k){
            //시간초 무조건 1초씩 증가
            seconds++;

            //방향 설정 및 회전 // 방향 회전시에는 1초 추가되므로 + 나 - 진행하지 않음
            switch(direction){
                case 'R':
                    if (y + 1 < board[0].length && board[x][y+1] == 0) {
                        y++;
                    } else {
                        direction = 'D';
                    }
                    break;
                case 'L':
                    if (y - 1 >= 0 && board[x][y-1] == 0) {
                        y--;
                    } else {
                        direction = 'U';
                    }
                    break;
                case 'D':
                    if (x + 1 < board.length && board[x+1][y] == 0) {
                        x++;
                    } else {
                        direction = 'L';
                    }
                    break;
                case 'U':
                    if (x - 1 >= 0 && board[x-1][y] == 0) {
                        x--;
                    } else {
                        direction = 'R';
                    }
                    break;
            }
        }
        answer[0] = x;
        answer[1] = y;

        return answer;
    }

    public static void main(String[] args){
        MyAnswer002 T = new MyAnswer002();
        int[][] arr1 = {{0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(arr1, 10)));
        int[][] arr2 = {{0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(arr2, 20)));
        int[][] arr3 = {{0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(arr3, 25)));

    }
}