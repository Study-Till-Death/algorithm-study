package com.algorithm;

import java.util.*;
class a43 { //송아지
    public int solution(int s, int e) {
        final int MAX = 200000;

        // visited[a][b] - 현수가 시간 b에 좌표 a에 방문했는지
        boolean[][] visited = new boolean[MAX + 1][2];

        //현수 현재 위치, 시간
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{s, 0}); // 시작 현수위치, 시작시간
        visited[s][0] = true;

        int time = 0;

        while (true) {
            int cow = e + time * (time + 1) / 2;
            if (cow > MAX) return -1; // 송아지 범위 넘음

            // 현수가 해당 시간에 송아지 위치를 방문했는지 확인
            if (visited[cow][time % 2]) return time;

            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int pos = cur[0];

                // 다음 위치들
                int[] nextPos = {pos - 1, pos + 1, pos * 2};

                for (int next : nextPos) {
                    if (next >= 0 && next <= MAX && !visited[next][(time + 1) % 2]) {
                        visited[next][(time + 1) % 2] = true;
                        q.offer(new int[]{next, time + 1});
                    }
                }
            }
            time++;
        }
    }

    public static void main(String[] args){
        a43 T = new a43();
        System.out.println(T.solution(1, 11));
        System.out.println(T.solution(10, 3));
        System.out.println(T.solution(1, 34567));
        System.out.println(T.solution(5, 6));
        System.out.println(T.solution(2, 54321));
    }
}
