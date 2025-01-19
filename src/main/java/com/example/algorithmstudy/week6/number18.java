package com.example.algorithmstudy.week6;

import java.util.*;

class Solution18 {
    // 진짜 큐 모르겠어서 3만원만 내면 친구해주는 애가 도와줬어요
    public int[] solution18(int[] arrival, int[] state) {
        int n = arrival.length; // 사원 수
        int[] result = new int[n];

        // 우선순위 큐
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                // 도착 시간 같으면 사원 번호가 작은 순으로
                return Integer.compare(a[1], b[1]);
            }
            // 다르면 시간 빠른 순으로
            return Integer.compare(a[0], b[0]);
        });

        // 모든 사원 정보를 큐에 추가
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{arrival[i], i, state[i]}); // 도착 시간, 사원 번호, 상태
        }

        int currentTime = 0; // 현재 시간
        int lastDirection = 1; // 0 들어가기, 1 나가기
        Queue<int[]> enterQueue = new LinkedList<>(); // 들어가기 큐
        Queue<int[]> exitQueue = new LinkedList<>(); // 나가기 큐

        while (!queue.isEmpty() || !enterQueue.isEmpty() || !exitQueue.isEmpty()) {
            // 현재 시간에 도착한 사람들을 각각의 큐에 추가
            while (!queue.isEmpty() && queue.peek()[0] <= currentTime) {
                int[] person = queue.poll();
                if (person[2] == 0) {
                    enterQueue.add(person);
                } else {
                    exitQueue.add(person);
                }
            }

            // 우선순위에 따라 처리
            if (!exitQueue.isEmpty() && (lastDirection == 1 || enterQueue.isEmpty())) {
                // 나가기 큐
                int[] person = exitQueue.poll();
                result[person[1]] = currentTime;
                lastDirection = 1;
            } else if (!enterQueue.isEmpty()) {
                // 들어가기 큐
                int[] person = enterQueue.poll();
                result[person[1]] = currentTime;
                lastDirection = 0; // 들어가기
            } else {
                // 처리할 사람이 없으면 시간만 증가
                lastDirection = 1; // 기본적으로 나가기
            }

            currentTime++;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution18 T = new Solution18();
        System.out.println(Arrays.toString(T.solution18(new int[]{0, 1, 1, 1, 2, 3, 8, 8}, new int[]{1, 0, 0, 1, 0, 0, 1, 0})));
        System.out.println(Arrays.toString(T.solution18(new int[]{3, 3, 4, 5, 5, 5}, new int[]{1, 0, 1, 0, 1, 0})));
        System.out.println(Arrays.toString(T.solution18(new int[]{2, 2, 2, 3, 4, 8, 8, 9, 10, 10}, new int[]{1, 0, 0, 0, 1, 1, 0, 1, 1, 0})));
    }
}