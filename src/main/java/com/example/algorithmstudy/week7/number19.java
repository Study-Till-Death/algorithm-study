package com.example.algorithmstudy.week7;

import java.util.*;

class Solution19 {
    public int solution19(int[] laser, String[] enter) {
        List<Integer> endTimes = new ArrayList<>();
        int maxWaiting = 0; // 최대 대기실 인원 수

        for (String e : enter) {
            String[] parts = e.split(" ");
            int time = toMinutes(parts[0]); // 환자의 방문 시간
            int type = Integer.parseInt(parts[1]); // 시술 종류
            int endTime = time + laser[type]; // 시술 끝나는 시간

            endTimes.add(endTime);
            int currentWaiting = 0;

            for (int t : endTimes) {
                // 현재 시간보다 끝나는 시간이 크면 아직 대기실에 있음
                if (t > time) {
                    currentWaiting++;
                }
            }

            maxWaiting = Math.max(maxWaiting, currentWaiting - 1);
        }

        return maxWaiting;
    }

    // 분 단위로 변환
    private int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public static void main(String[] args){
        Solution19 T = new Solution19();
        System.out.println(T.solution19(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "11:10 2"}));
        System.out.println(T.solution19(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "15:10 0", "15:20 3", "15:22 1", "15:23 0", "15:25 0"}));
        System.out.println(T.solution19(new int[]{30, 20, 25, 15}, new String[]{"10:20 1", "10:40 1", "11:00 1", "11:20 1", "11:40 1"}));
    }
}