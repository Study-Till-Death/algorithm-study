package com.example.algorithmstudy.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BreakWall {
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        // 계속해서 이동 , 도착시 현재까지 부순 벽 값 입력, 단 각 위치마다 도달시 필요한 최소 파괴벽 수 넣어주고 어케하면 되지 않을까
        int[][] breakCounts = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                breakCounts[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0,0));
        int[]dx = {-1, 0, 1, 0};
        int[]dy = {0, -1, 0, 1};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == board.length - 1 && point.y == board[0].length - 1) {
                answer = Math.min(answer, point.breakCount);
            }

            // 4방향 이동
            for (int i = 0; i < 4; i++) {
                int x = point.x + dx[i];
                int y = point.y + dy[i];
                int breakCount = point.breakCount;
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) { // 이동 가능하면
                    if (board[x][y] == 1) { // 벽이면 부수고 이동할 것임
                        breakCount ++;
                    }
                    // 그 위치에 도달할 때 가장 최소한으로 벽 부순 것인지 확인 후 이동
                    if (breakCount < breakCounts[x][y]) {
                        breakCounts[x][y] = breakCount; // 해당 위치로 오기위한 최소 벽 부수기 횟수 갱신
                        queue.add(new Point(x,y,breakCount)); // queue 추가
                    }
                }
            }
        }

        // 도달 못 하는 경우는 절대 없으므로 별도로 answer 가 Integer.MAX_VALUE 일 경우에 대한 체크는 하지 않음
        return answer;
    }

    record Point (
            int x,
            int y,
            int breakCount
    ){};

    public static void main(String[] args){
        BreakWall T = new BreakWall();
        System.out.println(T.solution(new int[][]{{0, 1, 1, 0}, {1, 0, 0, 1}, {0, 1, 0, 0}}));
        System.out.println(T.solution(new int[][]{{0, 1, 1, 0},{1, 1, 0, 1},{0, 0, 1, 0}, {0, 1, 1, 1}, {0, 1, 1, 0}}));
        System.out.println(T.solution(new int[][]{{0, 1, 1, 0, 1, 1},{0, 1, 1, 1, 1, 1},{1, 0, 0, 0, 1, 1}, {1, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 0}, {1, 0, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 0}}));
        System.out.println(T.solution(new int[][]{{0, 1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 0, 1, 1}, {1, 1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 0}}));
        System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 1, 1, 1},{1, 1, 0, 0, 1, 1, 1},{1, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1}, {1, 0, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1, 0}}));
    }
}
