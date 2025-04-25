package com.example.algorithmstudy.week17;
import java.util.*;

public class MyAnswer049 {
    public int solution(int[][] board) {
        int[] dx = {-1, 0, 1, 0}; //x 방향
        int[] dy = {0, 1, 0, -1}; //y 방향

        int n = board.length;
        int m = board[0].length;

        //최소 비용 배열 초기화
        int[][] cost = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        cost[0][0] = 0;

        //비용이 낮은 경로부터
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.add(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll(); //현재 위치 꺼내기
            int x = current[0];
            int y = current[1];
            int currentCost = current[2];

            //이미 처리된 경로는 스킵
            if (currentCost > cost[x][y]) continue;

            //4방향 탐색
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                //범위를 벗어난 경우 무시
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                //이동 가능한 경우 비용 계산
                int newCost = currentCost + (board[nx][ny] == 1 ? 1 : 0);
                if (newCost < cost[nx][ny]) {
                    cost[nx][ny] = newCost;
                    pq.offer(new int[]{nx, ny, newCost});
                }
            }
        }

        //도착점의 최소 비용 리턴
        return cost[n - 1][m - 1];
    }

    public static void main(String[] args){
        MyAnswer049 T = new MyAnswer049();
        System.out.println(T.solution(new int[][]{
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {0, 1, 0, 0}}));
        System.out.println(T.solution(new int[][]{
                {0, 1, 1, 0},
                {1, 1, 0, 1},
                {0, 0, 1, 0},
                {0, 1, 1, 1},
                {0, 1, 1, 0}}));
        System.out.println(T.solution(new int[][]{
                {0, 1, 1, 0, 1, 1},
                {0, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 1, 0, 1, 1, 0},
                {1, 0, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 0}}));
        System.out.println(T.solution(new int[][]{
                {0, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0}}));
        System.out.println(T.solution(new int[][]{
                {0, 0, 1, 0, 1, 1, 1},
                {1, 1, 0, 0, 1, 1, 1},
                {1, 1, 0, 1, 0, 1, 1},
                {0, 0, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1},
                {1, 0, 0, 1, 1, 1, 1},
                {1, 1, 0, 0, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 0}}));
    }
}
