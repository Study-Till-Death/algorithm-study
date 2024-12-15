package com.example.algorithmstudy.simulation;

import java.util.Arrays;

public class RobotCleaner {
    public int[] solution(int[][] board, int k){
        int[] answer = new int[2];
        // 좌상단 0,0
        Robot robot = new Robot();
        for (int i=0; i < k; i++){
            int nextR = robot.r + robot.dr[robot.direction];
            int nextC = robot.c + robot.dc[robot.direction];
            if(nextR <0 || nextR > board.length-1 ||  // R 좌표가 맵 바깥으로 나갈 경우
                    nextC <0 || nextC > board[0].length-1 ||  // C 좌표가 맵 바깥으로 나갈경우
                    board[nextR][nextC] == 1){ // 갈 곳이 장애물인 경우
                robot.direction = (robot.direction+1)%4; // 방향 +1
                continue;
            }
            // 위 조건 해당 없으면 go
            robot.move();
        }
        answer[0] = robot.r;
        answer[1] = robot.c;
        return answer;
    }

    private static class Robot {
        private int r,c; // row column
        private int direction; // 0,1,2,3 -> 위 오른 아래 왼
        private final int[] dr = {-1,0,1,0};
        private final int[] dc = {0,1,0,-1};

        public Robot() { // 초기 좌표
            this.r = 0;
            this.c = 0;
            this.direction = 1;
        }

        private void move(){
            r = r +dr[direction];
            c = c +dc[direction];
        }

    }

    public static void main(String[] args){
        RobotCleaner T = new RobotCleaner();
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
