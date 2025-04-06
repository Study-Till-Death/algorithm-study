package com.algorithm;

import java.util.*;

public class a42 {
    public int solution(int[] pool, int a, int b, int home) {
        int MAX = 10001; // 좌표 10000까지들어가야돼서..
        boolean[][] visited = new boolean[MAX][2]; // [0]=앞or처음, [1]=뒤 점프 이후
        boolean[] isPool = new boolean[MAX]; // 웅덩이
        for (int p : pool) isPool[p] = true; // 웅덩이담음

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0}); // 다음위치랑 이동방향 앞:0 or 뒤:1 담음
        visited[0][0] = true;

        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int pos = cur[0];
                int back = cur[1];

                if (pos == home) return level;

                // 앞으로 점프
                int nextA = pos + a; // 현위치 + 점프칸수
                if (nextA < MAX && !visited[nextA][0] && !isPool[nextA]) {
                    visited[nextA][0] = true;
                    q.offer(new int[]{nextA, 0});
                }

                // 뒤로 점프 연속이면 안됨
                int nextB = pos - b;
                if (back == 0 && nextB >= 0 && !visited[nextB][1] && !isPool[nextB]) {
                    visited[nextB][1] = true;
                    q.offer(new int[]{nextB, 1});
                }
            }
            level++;
        }

        return -1;
    }

    public static void main(String[] args){
        a42 T = new a42();
        System.out.println(T.solution(new int[]{11, 7, 20}, 3, 2, 10));
        System.out.println(T.solution(new int[]{1, 15, 11}, 3, 2, 5));
        System.out.println(T.solution(new int[]{9, 15, 35, 30, 20}, 2, 1, 25));
        System.out.println(T.solution(new int[]{5, 12, 7, 19, 23}, 3, 5, 18));
        System.out.println(T.solution(new int[]{10, 15, 20}, 3, 2, 2));
    }
}
