package com.example.algorithmstudy.week14;

import java.util.*;

// 집으로 이동
class Solution42 {
    public int solution42(int[] pool, int a, int b, int home) {
        int MAX = 10001; // 최대 수직선 좌표
        boolean[][] visited = new boolean[MAX][2];
        boolean[] water = new boolean[MAX]; // 웅덩이 여부
        for (int p : pool) water[p] = true;

        List<int[]> nodes = new ArrayList<>();
        nodes.add(new int[]{0, 0, 0});
        visited[0][0] = true;

        int idx = 0;
        while (idx < nodes.size()) {
            int[] cur = nodes.get(idx++);
            int pos = cur[0];       // 현재 위치
            int jumps = cur[1];     // 점프 횟수
            int back = cur[2];      // 이전 점프가 뒤인지 체크

            if (pos == home) return jumps; // 목적지 도착하면 리턴

            // 앞점
            int forward = pos + a;
            if (forward < MAX && !water[forward] && !visited[forward][0]) {
                visited[forward][0] = true;
                nodes.add(new int[]{forward, jumps + 1, 0});
            }

            // 뒷점 (이전 점프가 뒤가 아닐 경우만 가능)
            int backward = pos - b;
            if (back == 0 && backward >= 0 && !water[backward] && !visited[backward][1]) {
                visited[backward][1] = true;
                nodes.add(new int[]{backward, jumps + 1, 1});
            }
        }

        return -1;
    }

    public static void main(String[] args){
        Solution42 T = new Solution42();
        System.out.println(T.solution42(new int[]{11, 7, 20}, 3, 2, 10));
        System.out.println(T.solution42(new int[]{1, 15, 11}, 3, 2, 5));
        System.out.println(T.solution42(new int[]{9, 15, 35, 30, 20}, 2, 1, 25));
        System.out.println(T.solution42(new int[]{5, 12, 7, 19, 23}, 3, 5, 18));
        System.out.println(T.solution42(new int[]{10, 15, 20}, 3, 2, 2));
    }
}
