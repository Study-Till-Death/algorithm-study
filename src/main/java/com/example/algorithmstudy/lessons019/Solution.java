package com.example.algorithmstudy.lessons019;

import java.time.LocalTime;
import java.util.*;
class Solution {
    public int solution(int[] laser, String[] enter){
        int answer = 0;

        LocalTime openTime = LocalTime.of(9,0);
        LocalTime closeTime = LocalTime.of(20,0);

        Queue<Info> reservationQueue = new LinkedList<>();
        Queue<Info> readyQueue = new LinkedList<>();

        //도착시간과 레이저종류 split
        for(String enterEntry : enter){
           String[] splitEnterEntry = enterEntry.split(" ");
           reservationQueue.add(new Info(LocalTime.parse(splitEnterEntry[0]), Integer.parseInt(splitEnterEntry[1])));
        }

        Info room = null;
        for(LocalTime currentTime = openTime ; !currentTime.isAfter(closeTime) ; currentTime = currentTime.plusMinutes(1)){

            //도착시간 이상인 데이터는 대기실(readyQueue)에 add
            if(!reservationQueue.isEmpty() && !currentTime.isBefore(reservationQueue.peek().arrivalTime)){
                Info info = reservationQueue.poll();
                readyQueue.add(info);
            }

            //진료실(room)이 비거나 진료가 끝나면, 대기실 poll
            //진료가 끝나는 시간 설정
            if(!readyQueue.isEmpty() && (room == null || !currentTime.isBefore(room.endTime))){
                Info info = readyQueue.poll();
                info.endTime = currentTime.plusMinutes(laser[info.laserType]);
                room = info;
            }

            //대기실 크기
            answer = Math.max(answer, readyQueue.size());
        }

        return answer;
    }

    static class Info {
        public LocalTime arrivalTime;
        public int laserType;
        public LocalTime endTime;

        public Info(LocalTime arrivalTime, int laserType) {
            this.arrivalTime = arrivalTime;
            this.laserType = laserType;
        }
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "11:10 2"}));
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "15:10 0", "15:20 3", "15:22 1", "15:23 0", "15:25 0"}));
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:20 1", "10:40 1", "11:00 1", "11:20 1", "11:40 1"}));
    }
}
