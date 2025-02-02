package com.example.algorithmstudy.lessons021;

import java.time.LocalTime;
import java.util.*;
class Solution {
    public int solution(int n, int[][] meetings){
        int answer = 0;
        int[] counts = new int[n];
        int[] rooms = new int[n];

        PriorityQueue<ReservationInfo> pq = new PriorityQueue<>();

        //회의시작시간, 소요시간으로 나눠 우선순위큐에 넣기
        for(int[] meeting : meetings){
            pq.add(new ReservationInfo(meeting[0], meeting[1] - meeting[0]));
        }

        int t = 0;
        while(!pq.isEmpty()){
            for(int i = 0 ; i < rooms.length ; i++){
                //앞방부터 다 쓴 곳 채우기
                if(rooms[i] <= t && !pq.isEmpty() && pq.peek().reservationTime <= t) {
                    ReservationInfo reservationInfo = pq.poll();
                    rooms[i] = reservationInfo.meetingTime + t;
                    counts[i]++;
                }
            }
            t++;
        }

        int c = 0;
        for(int i = 0 ; i< counts.length ; i++){
            if(counts[i] > c){
                c = counts[i];
                answer = i;
            }
        }

        return answer;
    }

    static class ReservationInfo implements Comparable<ReservationInfo> {
        int reservationTime;
        int meetingTime;

        public ReservationInfo(int reservationTime, int meetingTime) {
            this.reservationTime = reservationTime;
            this.meetingTime = meetingTime;
        }

        @Override
        public int compareTo(ReservationInfo o) {
            return Integer.compare(reservationTime, o.reservationTime);
        }
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(2, new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}}));
        System.out.println(T.solution(3, new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
        System.out.println(T.solution(3, new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}}));
        System.out.println(T.solution(4, new int[][]{{3, 20}, {1, 25}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
    }
}
