package com.example.algorithmstudy.week1;

class Question003 {
    public int solution(int[][] board){
        int answer = 0;

        char direction1 = 'U'; // R right L left D down U up
        char direction2 = 'U'; // R right L left D down U up
        int seconds = 0;
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2) {
                    x1 = i;
                    y1 = j;
                }
                if (board[i][j] == 3) {
                    x2 = i;
                    y2 = j;
                }
            }
        }

        while (seconds < 10000){
            seconds++;

            switch(direction1){
                case 'R':
                    if (y1 + 1 < board[0].length && board[x1][y1+1] != 1) {
                        y1++;
                    } else {
                        direction1 = 'D';
                    }
                    break;
                case 'L':
                    if (y1 - 1 >= 0 && board[x1][y1-1] != 1) {
                        y1--;
                    } else {
                        direction1 = 'U';
                    }
                    break;
                case 'D':
                    if (x1 + 1 < board.length && board[x1+1][y1] != 1) {
                        x1++;
                    } else {
                        direction1 = 'L';
                    }
                    break;
                case 'U':
                    if (x1 - 1 >= 0 && board[x1-1][y1] != 1) {
                        x1--;
                    } else {
                        direction1 = 'R';
                    }
                    break;
            }

            switch(direction2){
                case 'R':
                    if (y2 + 1 < board[0].length && board[x2][y2+1] != 1) {
                        y2++;
                    } else {
                        direction2 = 'D';
                    }
                    break;
                case 'L':
                    if (y2 - 1 >= 0 && board[x2][y2-1] != 1) {
                        y2--;
                    } else {
                        direction2 = 'U';
                    }
                    break;
                case 'D':
                    if (x2 + 1 < board.length && board[x2+1][y2] != 1) {
                        x2++;
                    } else {
                        direction2 = 'L';
                    }
                    break;
                case 'U':
                    if (x2 - 1 >= 0 && board[x2-1][y2] != 1) {
                        x2--;
                    } else {
                        direction2 = 'R';
                    }
                    break;
            }

            if(x1 == x2 && y1 == y2){
                return seconds;
            }
        }

        return answer;
    }

    public static void main(String[] args){
        Question003 T = new Question003();
        int[][] arr1 = {{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 2, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0}};
        System.out.println(T.solution(arr1));
        int[][] arr2 = {{1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 2, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 3}};
        System.out.println(T.solution(arr2));
    }
}