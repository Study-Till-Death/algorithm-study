package com.example.algorithmstudy.week15;

import java.util.*;

// 미로 최단거리 통로 (개모르겠는데 지피티 도움)
class Solution45 {
    int N, M;
    int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // 상하좌우

    public int solution45(int[][] board) {
        N = board.length;
        M = board[0].length;

        int[][] distanceSum = new int[N][M];
        int[][] reachableCount = new int[N][M];
        int buildingCount = 0;

        // 모든 빌딩 찾아
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) { // 빌딩일 경우
                    buildingCount++;
                    fakeBFS(i, j, board, distanceSum, reachableCount);
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        // 빌딩이 도달 가능한 빈 땅들 거리 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0 && reachableCount[i][j] == buildingCount) {
                    answer = Math.min(answer, distanceSum[i][j]);
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private void fakeBFS(int sx, int sy, int[][] board, int[][] distanceSum, int[][] reachableCount) {
        boolean[][] visited = new boolean[N][M];
        List<int[]> bfsList = new ArrayList<>();
        bfsList.add(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        int index = 0;

        while (index < bfsList.size()) {
            int[] cur = bfsList.get(index++);
            int x = cur[0], y = cur[1], dist = cur[2];

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                // 범위 안, no 방문, no 장애물
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    distanceSum[nx][ny] += dist + 1;
                    reachableCount[nx][ny]++;
                    bfsList.add(new int[]{nx, ny, dist + 1});
                }
            }
        }
    }

    public static void main(String[] args){
        Solution45 T = new Solution45();
        System.out.println(T.solution45(new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 2, 1, 0, 0}, {2, 0, 0, 2, 2}, {0, 0, 0, 0, 0}}));
        System.out.println(T.solution45(new int[][]{{1, 0, 0, 1}, {0, 0, 2, 0}, {0, 0, 1, 0}, {2, 2, 0, 0}}));
        System.out.println(T.solution45(new int[][]{{1, 2, 0, 0}, {0, 0, 1, 2}, {0, 2, 0, 0}, {0, 2, 1, 0}}));
        System.out.println(T.solution45(new int[][]{{1, 0, 0, 1}, {0, 0, 2, 0}, {0, 0, 1, 0}, {2, 2, 0, 1}}));
    }
}
