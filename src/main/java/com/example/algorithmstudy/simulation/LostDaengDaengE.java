package com.example.algorithmstudy.simulation;

public class LostDaengDaengE {
    public int solution(int[][] board) {
        int time = 0;
        Animal hyunSu = new Animal(0, 0);
        Animal dog = new Animal(0, 0);
        Animal[] animals = new Animal[]{hyunSu, dog};

        // 현수, 개 위치 지정
        boolean findHyunSuStart = false; // 현수 시작위치
        boolean findDogStart = false; // 댕댕이 시작위치

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (!findHyunSuStart && board[i][j] == 2) {
                    hyunSu.r = i;
                    hyunSu.c = j;
                    board[i][j] = 0;
                    findHyunSuStart = true;
                }
                if (!findDogStart && board[i][j] == 3) {
                    dog.r = i;
                    dog.c = j;
                    board[i][j] = 0;
                    findDogStart = true;
                }
                if (findHyunSuStart && findDogStart) {
                    break;
                }
            }
        }


        // 로봇 청소기 2대 돌려서 10000분 내로 만나나 확인
        while (time < 10000) {
            time++;
            for (Animal animal : animals) {
                if (animal.canMove(board)) {
                    animal.move();
                } else {
                    animal.direction = (animal.direction + 1) % 4;
                }
            }
            if (hyunSu.r == dog.r && hyunSu.c == dog.c) {
                return time;
            }
        }

        return 0;
    }

    private static class Animal {
        private int r, c; // row column
        private int direction; // 0,1,2,3 -> 위 오른 아래 왼
        private final int[] dr = {-1, 0, 1, 0};
        private final int[] dc = {0, 1, 0, -1};

        public Animal(int r, int c) { // 초기 좌표
            this.r = r;
            this.c = c;
            this.direction = 0;
        }

        private boolean canMove(int[][] board) { // 갈수 있는지 체크
            int nextR = this.r + dr[direction];
            int nextC = this.c + dc[direction];
            return !(nextR < 0 || nextR > board.length - 1 ||  // R 좌표가 맵 바깥으로 나갈 경우
                    nextC < 0 || nextC > board[0].length - 1 ||  // C 좌표가 맵 바깥으로 나갈경우
                    board[nextR][nextC] == 1); // 갈 곳이 장애물인 경우
        }

        private void move() {
            r = r + dr[direction];
            c = c + dc[direction];
        }

    }

    public static void main(String[] args) {
        LostDaengDaengE T = new LostDaengDaengE();
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
