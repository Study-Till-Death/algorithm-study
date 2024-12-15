package com.example.algorithmstudy.week1;

class Solution3 {
    public int solution3(int[][] board) {
        // 방향 - 0 북쪽 1 동쪽 2 남쪽 3 서쪽
        int answer = 0, hynsuX = 0, hynsuY = 0, hynsuDirection = 0, dogX = 0, dogY = 0, dogDirection = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // 현수 위치
                if (board[i][j] == 2) {
                    hynsuX = i;
                    hynsuY = j;
                }
                // 강아지 위치
                if (board[i][j] == 3) {
                    dogX = i;
                    dogY = j;
                }
            }
        }

        while (answer < 10000) {
            answer++;

            // 현수
            int tempHynsuX = hynsuX;
            int tempHynsuY = hynsuY;

            if (hynsuDirection == 0) tempHynsuX--;
            else if (hynsuDirection == 1) tempHynsuY++;
            else if (hynsuDirection == 2) tempHynsuX++;
            else if (hynsuDirection == 3) tempHynsuY--;

            if (tempHynsuX < 0 || tempHynsuY < 0 || tempHynsuX >= board.length || tempHynsuY >= board.length || board[tempHynsuX][tempHynsuY] == 1) {
                // 방향 90도 바꾸기
                hynsuDirection = (hynsuDirection + 1) % 4;
            } else {
                // 2번 문제랑 똑같이 원래 자리로 돌아가게끔 하려했더니 오류터짐
                hynsuX = tempHynsuX;
                hynsuY = tempHynsuY;
            }

            // 강아지
            int tempDogX = dogX;
            int tempDogY = dogY;

            if (dogDirection == 0) tempDogX--;
            else if (dogDirection == 1) tempDogY++;
            else if (dogDirection == 2) tempDogX++;
            else if (dogDirection == 3) tempDogY--;

            if (tempDogX < 0 || tempDogY < 0 || tempDogX >= board.length || tempDogY >= board.length || board[tempDogX][tempDogY] == 1) {
                // 방향 90도 바꾸기
                dogDirection = (dogDirection + 1) % 4;
            } else {
                dogX = tempDogX;
                dogY = tempDogY;
            }

            // 만나면 시간 반환
            if (hynsuX == dogX && hynsuY == dogY) {
                return answer;
            }
        }

        // 10000분 이상이면 -1 반환
        answer = -1;

        return answer;
    }

    public static void main(String[] args) {
        Solution3 T = new Solution3();
        int[][] arr1 = {
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 2, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0}};
        System.out.println(T.solution3(arr1));

        int[][] arr2 = {
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 2, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 3}};
        System.out.println(T.solution3(arr2));
    }
}
