package com.example.algorithmstudy.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class SoopKnight {
    public int solution(int[][] board){
        int answer = 0;
        // 각각의 산딸기까지 거리 각각 bfs 로 구하고 그 산딸기들에서 기사까지 거리 각각 bfs 로 구하고 더한 값 중 최소값인가
        // 근데 귀찮으니까 그냥 대충 알아서 줍고 도착하는 최소경우 나올때까지 돌릴 생각

        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2) {
                    queue.add(new Point(i,j,false,0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            // 기사
            if (board[point.x][point.y] == 3 && point.hasSanddalgi) {
                return point.moveCount;
            }
            // 산딸기 줍
            if (board[point.x][point.y] == 4 ) {
                point.hasSanddalgi = true;
            }

            // 이동
            if (point.x+1 < board.length && board[point.x+1][point.y] != 1) {
                queue.add(new Point(point.x+1,point.y,point.hasSanddalgi, point.moveCount+1));
            }
            if (point.y+1 < board[0].length && board[point.x][point.y+1] != 1) {
                queue.add(new Point(point.x,point.y+1,point.hasSanddalgi, point.moveCount+1));
            }
            if (point.x-1 >= 0 && board[point.x-1][point.y] != 1) {
                queue.add(new Point(point.x-1,point.y,point.hasSanddalgi, point.moveCount+1));
            }
            if (point.y-1 >= 0 && board[point.x][point.y-1] != 1) {
                queue.add(new Point(point.x,point.y-1,point.hasSanddalgi, point.moveCount+1));
            }
        }

        return answer;
    }

    class Point {
        int x;
        int y;
        boolean hasSanddalgi;
        int moveCount;

        public Point(int x, int y, boolean hasSanddalgi, int moveCount) {
            this.x = x;
            this.y = y;
            this.hasSanddalgi = hasSanddalgi;
            this.moveCount = moveCount;
        }
    }

    public static void main(String[] args){
        SoopKnight T = new SoopKnight();
        System.out.println(T.solution(new int[][]{{4, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 0},
                {0, 2, 1, 1, 3, 0, 4, 0},
                {0, 0, 0, 4, 1, 1, 1, 0}}));
        System.out.println(T.solution(new int[][]{{3, 0, 0, 0, 1, 4, 4, 4},
                {0, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 4, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 1, 1, 0},
                {4, 0, 0, 0, 1, 0, 0, 0},
                {4, 1, 0, 0, 1, 0, 0, 0},
                {4, 0, 0, 0, 0, 0, 1, 2}}));
        System.out.println(T.solution(new int[][]{{4, 1, 0, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 2, 3, 4},
                {0, 1, 0, 1, 0}}));
    }
}
