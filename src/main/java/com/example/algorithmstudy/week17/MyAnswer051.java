package com.example.algorithmstudy.week17;
import java.util.*;

public class MyAnswer051 {
    public int solution(int[][] board, int[] s, int[] e){
        int n = board.length;
        int m = board[0].length;
        int[][] distance = new int[n][m];
        for (int[] row : distance) Arrays.fill(row, Integer.MAX_VALUE);

        int[] dx = {-1, 0, 1, 0}; //x 방향
        int[] dy = {0, 1, 0, -1}; //y 방향

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        queue.offer(new int[]{s[0], s[1], 0}); // 시작 지점
        distance[s[0]][s[1]] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if(cur[2] > distance[cur[0]][cur[1]]) continue;

            //4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cur[0];
                int ny = cur[1];
                int step = cur[2];

                //벽 격자 만날 때까지 이동
                while (nx>= 0 && nx < n&& ny >= 0 && ny < m && board[nx][ny] == 0) {
                    nx += dx[i];
                    ny += dy[i];
                    step++;
                }

                //벽 격자 만난 마지막 움직임 롤백
                nx -= dx[i];
                ny -= dy[i];
                step--;

                //멈춘 위치에서 거리 갱신
                if (distance[nx][ny] > step) {
                    distance[nx][ny] = step;
                    queue.offer(new int[]{nx, ny, step});
                }
            }
        }

        //목표지점에 도달할 수 없는 경우
        if(distance[e[0]][e[1]] == Integer.MAX_VALUE) return -1;
        else return distance[e[0]][e[1]];
    }
    public static void main(String[] args){
        MyAnswer051 T = new MyAnswer051();
        System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 0}}, new int[]{1, 0}, new int[]{4, 5}));
        System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{4, 2}));
        System.out.println(T.solution(new int[][]{{1, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}}, new int[]{0, 3}, new int[]{4, 2}));
        System.out.println(T.solution(new int[][]{{0, 1, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{4, 5}));
        System.out.println(T.solution(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{4, 3}));
    }
}
