package com.example.algorithmstudy.week15;
import java.util.*;

public class MyAnswer043 {
    public int solution(int s, int e){
        int MAX_POSITION = 200000;
        int[][] visited = new int[2][MAX_POSITION + 1];
        Queue<Integer> queue = new LinkedList<>();

        // 시작 위치 방문 처리 및 큐에 추가
        visited[0][s] = 1;
        queue.offer(s);

        int time = 0; // 경과 시간

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            time++;

            // 현수의 이동 처리 (BFS)
            for (int i = 0; i < levelSize; i++) {
                int currentPosition = queue.poll(); // 현재 위치 가져오기

                // 현수가 이동할 수 있는 세 가지 방법 탐색
                for (int nextPosition : new int[]{currentPosition - 1, currentPosition + 1, currentPosition * 2}) {
                    // 이동 범위가 0~200000 사이 방문하지 않은 곳
                    if (nextPosition >= 0 && nextPosition <= MAX_POSITION && visited[time % 2][nextPosition] == 0) {
                        visited[time % 2][nextPosition] = 1; // 방문 체크
                        queue.offer(nextPosition); // 다음 위치 큐에 추가
                    }
                }
            }

            // 송아지의 이동 처리
            e += time;
            if (e > MAX_POSITION) return -1; // 못 잡음
            if (visited[time % 2][e] == 1) return time; // 현수가 송아지와 같은 위치에 도달하면 반환
        }

        return -1;
    }

    public static void main(String[] args){
        MyAnswer043 T = new MyAnswer043();
        System.out.println(T.solution(1, 11));
        System.out.println(T.solution(10, 3));
        System.out.println(T.solution(1, 34567));
        System.out.println(T.solution(5, 6));
        System.out.println(T.solution(2, 54321));
    }
}
