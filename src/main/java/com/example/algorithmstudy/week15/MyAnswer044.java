package com.example.algorithmstudy.week15;
import java.util.*;

public class MyAnswer044 {
    static class Point {
        int x, y, dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public int solution(int[][] board){
        int N = 7;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 이동 좌표
        boolean[][] visited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();

        queue.offer(new Point(0, 0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.x == 6 && current.y == 6) return current.dist;

            for (int[] dir : directions) {
                int nx = current.x + dir[0];
                int ny = current.y + dir[1];

                // 범위 내 벽이 아니고 방문한 적 없는 곳
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == 0 && !visited[nx][ny]) {
                    queue.offer(new Point(nx, ny, current.dist + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        return -1; // 탈출 불가
    }

    public static void main(String[] args){
        MyAnswer044 T = new MyAnswer044();
        int[][] arr={{0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {1, 1, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 0, 0, 0}};
        System.out.println(T.solution(arr));
    }
}
