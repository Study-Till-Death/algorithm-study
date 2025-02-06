package com.example.algorithmstudy.week7;

import java.util.*;

class Solution21 {
    public int solution21(int n, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0])); // 회의 시작 시간 기준 정렬

        int[] roomEndTime = new int[n]; // 각 회의실 종료 시간을 저장
        int[] roomUsage = new int[n]; // 각 회의실 사용 횟수

        for (int[] meeting : meetings) {
            int startTime = meeting[0];
            int endTime = meeting[1];

            int minEndTime = Integer.MAX_VALUE;
            int selectedRoom = -1;

            for (int i = 0; i < n; i++) {
                if (roomEndTime[i] <= startTime) { // 바로 사용 가능한 회의실 찾기
                    selectedRoom = i;
                    break;
                }
                if (roomEndTime[i] < minEndTime) { // 가장 빨리 끝나는 회의실 찾기
                    minEndTime = roomEndTime[i];
                    selectedRoom = i;
                }
            }

            // 회의실 배정 (대기해야하면 종료 시간 이후부터 회의)
            roomEndTime[selectedRoom] = Math.max(roomEndTime[selectedRoom], startTime) + (endTime - startTime);
            roomUsage[selectedRoom]++;
        }

        // 가장 많이 사용된 회의실 찾기
        int maxUsage = 0, mostUsedRoom = 0;
        for (int i = 0; i < n; i++) {
            if (roomUsage[i] > maxUsage) {
                maxUsage = roomUsage[i];
                mostUsedRoom = i;
            }
        }

        return mostUsedRoom;
    }

    public static void main(String[] args) {
        Solution21 sol = new Solution21();
        System.out.println(sol.solution21(2, new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}})); // 0
        System.out.println(sol.solution21(3, new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}})); // 0
        System.out.println(sol.solution21(3, new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}})); // 1
        System.out.println(sol.solution21(4, new int[][]{{3, 20}, {1, 25}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}})); // 2
    }
}