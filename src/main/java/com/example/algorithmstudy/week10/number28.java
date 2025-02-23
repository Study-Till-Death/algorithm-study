package com.example.algorithmstudy.week10;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution28 {
    public int solution28(int[][] meetings) {
        if (meetings == null || meetings.length == 0) return 0;

        // 회의 시작 시간을 기준으로 정렬
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 최소 회의실 개수 계산
        for (int[] meeting : meetings) {
            // 회의 중 가장 빠른 종료 시간이 작거나 같으면 회의실 재사용
            if (!pq.isEmpty() && pq.peek() <= meeting[0]) {
                // 기존 회의 종료 시간 제거
                pq.poll();
            }
            // 현재 회의 종료 시간 추가
            pq.add(meeting[1]);
        }

        return pq.size();
    }

    public static void main(String[] args){
        Solution28 T = new Solution28();
        System.out.println(T.solution28(new int[][]{{0, 10}, {20, 25}, {5, 15}, {2, 5}}));
        System.out.println(T.solution28(new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}}));
        System.out.println(T.solution28(new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
        System.out.println(T.solution28(new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}}));
    }
}
