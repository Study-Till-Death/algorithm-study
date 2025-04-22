package com.algorithm;
import java.util.*;
class a44 {
    // 상, 하, 좌, 우
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] board){
        int[][] dist = new int[7][7]; // 이동 거리 저장
        boolean[][] visited = new boolean[7][7]; // 방문 여부

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            // 도착 지점에 도달하면 거리 반환
            if (x == 6 && y == 6) return dist[x][y];

            // 네 방향으로 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 내 +길이고 아직 방문 안 했을 때
                if (nx >= 0 && nx < 7 && ny >= 0 && ny < 7
                        && board[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[x][y] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        // 도착 못 하면 -1 반환
        return -1;
    }

    public static void main(String[] args){
        a44 T = new a44();
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