package com.example.algorithmstudy.week15;
import java.util.*;

public class MyAnswer045 {
    static int N;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 이동

    public int solution(int[][] board){
        N = board.length;
        int[][] distanceSum = new int[N][N]; // 모든 빌딩에서 거리 합
        int[][] reachCount = new int[N][N]; // 도달한 빌딩 개수
        int buildingCount = 0;

        // 모든 빌딩에서 체크
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    buildingCount++;
                    if (!bfs(board, distanceSum, reachCount, i, j)) return -1;
                }
            }
        }

        // 모든 빌딩에서 도달할 수 있는 빈땅 중 최소 거리 찾기
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0 && reachCount[i][j] == buildingCount) {
                    minDistance = Math.min(minDistance, distanceSum[i][j]);
                }
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    // 특정 빌딩에서 0까지 거리 누적
    private static boolean bfs(int[][] board, int[][] distanceSum, int[][] reachCount, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(new int[]{x, y, 0}); // {x좌표, y좌표, 거리}
        visited[x][y] = true;

        int arriveAble = 0; // 도달할 수 있는 빈땅 개수
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0], cy = current[1], dist = current[2];

            for (int[] dir : directions) {
                int nx = cx + dir[0];
                int ny = cy + dir[1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && board[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny, dist + 1});
                    visited[nx][ny] = true;
                    distanceSum[nx][ny] += (dist + 1);
                    reachCount[nx][ny]++;
                    arriveAble++;
                }
            }
        }

        // 도달할 수 있는 빈땅이 없으면 false
        return arriveAble > 0;
    }

    public static void main(String[] args){
        MyAnswer045 T = new MyAnswer045();
        System.out.println(T.solution(new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 2, 1, 0, 0}, {2, 0, 0, 2, 2}, {0, 0, 0, 0, 0}}));
        System.out.println(T.solution(new int[][]{{1, 0, 0, 1}, {0, 0, 2, 0}, {0, 0, 1, 0}, {2, 2, 0, 0}}));
        System.out.println(T.solution(new int[][]{{1, 2, 0, 0}, {0, 0, 1, 2}, {0, 2, 0, 0}, {0, 2, 1, 0}}));
        System.out.println(T.solution(new int[][]{{1, 0, 0, 1}, {0, 0, 2, 0}, {0, 0, 1, 0}, {2, 2, 0, 1}}));
    }
}
