package com.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class a41 {
    public int solution(int[] nums){
        int n = nums.length;
        Queue<Integer> q = new LinkedList<>(); // 현 위치
        boolean[] visited = new boolean[n];
        int[] dist = new int[n]; // 점프 횟수 저장

        q.offer(0); // 시작점 0에서 출발
        visited[0] = true;// 방문 처리
        dist[0] = 0;        // 출발점이니까 점프 0번

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int jump = 1; jump <= nums[now]; jump++) {
                int next = now + jump;
                if (next >= n) continue;

                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[now] + 1;
                    q.offer(next);

                    // 목표 도달 시 바로 리턴
                    if (next == n - 1) return dist[next];
                }
            }
        }

        return -1; // 도달 불가한 경우
    }

    public static void main(String[] args){
        a41 T = new a41();
        System.out.println(T.solution(new int[]{2, 2, 1, 2, 1, 1}));
        System.out.println(T.solution(new int[]{1, 0, 1, 1, 3, 1, 2, 1}));
        System.out.println(T.solution(new int[]{2, 3, 1, 0, 1, 1, 2, 3, 1, 5, 1, 3, 1}));
        System.out.println(T.solution(new int[]{1, 2, 1, 2, 1, 2, 1, 1, 3, 1, 2, 1}));
        System.out.println(T.solution(new int[]{1, 3, 2, 1, 1, 2, 3, 1, 3, 1, 2, 3, 5, 1, 5, 1, 2, 1, 1}));
    }
}
