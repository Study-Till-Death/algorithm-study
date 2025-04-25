package com.example.algorithmstudy.week17;
import java.util.*;

public class MyAnswer050 {
    public int solution(int[][] board) {
        int[] dx = {0, 0, 1, -1}; //x 방향
        int[] dy = {1, -1, 0, 0}; //y 방향

        int n = board.length;
        int m = board[0].length;

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


//            System.out.println("Current Poll: x=" + x + ", y=" + y + ", cost=" + currentCost);

            int dir = board[x][y] - 1;

            //이미 처리된 경로는 스킵
            if (currentCost > cost[x][y]) continue;

            //4방향 탐색
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                //범위를 벗어난 경우 무시
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (k == dir &&  cost[nx][ny] > currentCost) {
                    cost[nx][ny] = currentCost;
                    pq.offer(new int[]{nx, ny, currentCost});
//                    System.out.println("Updated Cost: x=" + nx + ", y=" + ny + ", cost=" + currentCost);
                }
                else if (cost[nx][ny] > currentCost +1){
                    cost[nx][ny] = currentCost + 1;
                    pq.offer(new int[]{nx, ny, currentCost + 1});
//                    System.out.println("Updated Cost: x=" + nx + ", y=" + ny + ", cost=" + (currentCost + 1));
                }
            }
        }

        //도착점의 최소 비용 반환
        return cost[n - 1][m - 1];
    }

    public static void main(String[] args){
        MyAnswer050 T = new MyAnswer050();
        System.out.println(T.solution(new int[][]{{3, 1, 3}, {1, 4, 2}, {4, 2, 3}}));
        System.out.println(T.solution(new int[][]{{3, 2, 1, 3}, {1, 1, 4, 2}, {3, 4, 2, 1}, {1, 2, 2, 4}}));
        System.out.println(T.solution(new int[][]{{3, 2, 1, 3, 1, 2}, {2, 1, 1, 1, 4, 2}, {2, 2, 2, 1, 2, 2}, {1, 3, 3, 4, 4, 4}, {1, 2, 2, 3, 3, 4}}));
        System.out.println(T.solution(new int[][]{{3, 2, 1, 3, 1, 2, 2, 2}, {2, 1, 1, 1, 4, 2, 1, 1}, {2, 2, 2, 1, 2, 2, 3, 4}, {1, 3, 3, 4, 4, 4, 3, 1}, {1, 2, 2, 3, 3, 4, 3, 4}, {1, 2, 2, 3, 3, 1, 1, 1}}));
        System.out.println(T.solution(new int[][]{{1, 2, 3, 2, 1, 3, 1, 2, 2, 2}, {1, 2, 2, 1, 1, 1, 4, 2, 1, 1}, {3, 2, 2, 2, 2, 1, 2, 2, 3, 4}, {3, 3, 1, 3, 3, 4, 4, 4, 3, 1}, {1, 1, 1, 2, 2, 3, 3, 4, 3, 4}, {1, 1, 1, 2, 2, 3, 3, 1, 1, 1}}));
    }
}
