package com.example.algorithmstudy.week15;

import java.util.*;

// get 송아지
class Solution44 {
    // 이 비슷한거 하지 않았나
    static int[] dx = {-1, 1, 0, 0}; // x축
    static int[] dy = {0, 0, -1, 1}; // y축

    public int solution44(int[][] board){
        int[][] distance = new int[7][7];
        boolean[][] visited = new boolean[7][7];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0}); // 시작점 (0, 0)
        visited[0][0] = true; // 시작점 갔음

        while (!q.isEmpty()) {
            int[] cur = q.poll(); // 현재 위치 꺼냄
            int x = cur[0], y = cur[1];

            // 도착점에 도달하면 거리 반환
            if (x == 6 && y == 6) return distance[x][y];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i]; // 다음 x
                int ny = y + dy[i]; // 다음 y

                // 안에 있고 벽이 아니고 안 간 경우
                if (nx >= 0 && nx < 7 && ny >= 0 && ny < 7 && board[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    distance[nx][ny] = distance[x][y] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args){
        Solution44 T = new Solution44();
        int[][] arr={{0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {1, 1, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 0, 0, 0}};
        System.out.println(T.solution44(arr));
    }
}
