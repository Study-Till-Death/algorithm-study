package com.algorithm;
import java.util.*;

class a45 {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] board) {
        int n = board.length;
        int[][] distSum = new int[n][n]; // 총 거리 합 저장
        int[][] reachCount = new int[n][n]; // 각 위치에 도달한 건물 수

        int totalBuildings = 0;

        // 모든건물(1) 위치에서 BFS..
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    totalBuildings++;
                    bfs(i, j, board, distSum, reachCount);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        // 모든 빈칸중 모든 건물에서 도달 가능한 위치의 거리합 최소값 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0 && reachCount[i][j] == totalBuildings) {
                    answer = Math.min(answer, distSum[i][j]);
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private void bfs(int x, int y, int[][] board, int[][] distSum, int[][] reachCount) {
        int n = board.length;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y, 0}); // x, y, 거리
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1], dist = cur[2];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n &&
                        board[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    distSum[nx][ny] += dist + 1;
                    reachCount[nx][ny]++;
                    q.offer(new int[]{nx, ny, dist + 1});
                }
            }
        }
    }

    public static void main(String[] args){
        a45 T = new a45();
        System.out.println(T.solution(new int[][]{
                {1, 0, 2, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 2, 1, 0, 0},
                {2, 0, 0, 2, 2},
                {0, 0, 0, 0, 0}}));

        System.out.println(T.solution(new int[][]{
                {1, 0, 0, 1},
                {0, 0, 2, 0},
                {0, 0, 1, 0},
                {2, 2, 0, 0}}));

        System.out.println(T.solution(new int[][]{
                {1, 2, 0, 0},
                {0, 0, 1, 2},
                {0, 2, 0, 0},
                {0, 2, 1, 0}}));

        System.out.println(T.solution(new int[][]{
                {1, 0, 0, 1},
                {0, 0, 2, 0},
                {0, 0, 1, 0},
                {2, 2, 0, 1}}));
    }
}
