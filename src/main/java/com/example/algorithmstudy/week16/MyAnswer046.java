package com.example.algorithmstudy.week16;
import java.util.*;

public class MyAnswer046 {

    static class Point {
        int x, y, time;
        Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 이동

    private int[][] bfs(int[][] board, int startX, int startY) {
        int n = board.length;
        int m = board[0].length;
        int[][] time = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(time[i], -1);

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY, 0));
        time[startX][startY] = 0;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int[] move : directions) {
                int nx = now.x + move[0];
                int ny = now.y + move[1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (board[nx][ny] != 1 && time[nx][ny] == -1) {
                        time[nx][ny] = now.time + 1;
                        queue.offer(new Point(nx, ny, now.time + 1));
                    }
                }
            }
        }

        return time;
    }

    public int solution(int[][] board){
        int n = board.length;
        int m = board[0].length;

        int start2X = -1, start2Y = -1;
        int start3X = -1, start3Y = -1;
        List<int[]> berryLocations = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 2) {
                    start2X = i;
                    start2Y = j;
                } else if (board[i][j] == 3) {
                    start3X = i;
                    start3Y = j;
                } else if (board[i][j] == 4) {
                    berryLocations.add(new int[]{i, j});
                }
            }
        }

        int[][] timeFrom2to4 = bfs(board, start2X, start2Y);
        int[][] timeFrom3to4 = bfs(board, start3X, start3Y);

        int minTime = Integer.MAX_VALUE;

        for (int[] location : berryLocations) {
            int fx = location[0];
            int fy = location[1];

            int t1 = timeFrom2to4[fx][fy];
            int t2 = timeFrom3to4[fx][fy];

            if (t1 != -1 && t2 != -1) {
                int totalTime = t1 + t2;
                if (totalTime < minTime) {
                    minTime = totalTime;
                }
            }
        }

        return (minTime == Integer.MAX_VALUE) ? -1 : minTime;
    }

    public static void main(String[] args){
        MyAnswer046 T = new MyAnswer046();
        System.out.println(T.solution(new int[][]{
                {4, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 0},
                {0, 2, 1, 1, 3, 0, 4, 0},
                {0, 0, 0, 4, 1, 1, 1, 0}}));
        System.out.println(T.solution(new int[][]{
                {3, 0, 0, 0, 1, 4, 4, 4},
                {0, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 4, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 1, 1, 0},
                {4, 0, 0, 0, 1, 0, 0, 0},
                {4, 1, 0, 0, 1, 0, 0, 0},
                {4, 0, 0, 0, 0, 0, 1, 2}}));
        System.out.println(T.solution(new int[][]{
                {4, 1, 0, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 2, 3, 4},
                {0, 1, 0, 1, 0}}));
    }
}
