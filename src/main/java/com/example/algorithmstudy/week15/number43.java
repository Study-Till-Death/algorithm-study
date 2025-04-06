package com.example.algorithmstudy.week15;

import java.util.*;

// go 홈
class Solution43 {
    static final int MAX = 200000; // 좌표 최댓값

    public int solution43(int s, int e) {
        boolean[] visited = new boolean[MAX + 1];
        List<Integer> current = new ArrayList<>();
        visited[s] = true;
        current.add(s);

        int time = 0;
        int rabbit = e;

        while (rabbit <= MAX) {
            // 현수가 토끼의 위치를 방문했다면 잡음
            if (visited[rabbit]) return time;

            List<Integer> next = new ArrayList<>();
            for (int curr : current) {
                int[] moves = {curr - 1, curr + 1, curr * 2}; // 현수의 가능 방향
                for (int move : moves) {
                    if (move >= 0 && move <= MAX && !visited[move]) { // 위치가 유효하고 아직 안갔을 경우
                        visited[move] = true; // 이제 갔음
                        next.add(move);
                    }
                }
            }

            time++;
            rabbit += time;
            current = next;
        }

        return -1;
    }

    public static void main(String[] args){
        Solution43 T = new Solution43();
        System.out.println(T.solution43(1, 11));
        System.out.println(T.solution43(10, 3));
        System.out.println(T.solution43(1, 34567));
        System.out.println(T.solution43(5, 6));
        System.out.println(T.solution43(2, 54321));
    }
}
