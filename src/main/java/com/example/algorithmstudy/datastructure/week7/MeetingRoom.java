package com.example.algorithmstudy.datastructure.week7;

import java.util.*;
import java.util.stream.Collectors;

public class MeetingRoom {
    public int solution(int n, int[][] meetings) {
        int answer = 0;

        // 방번호 기준으로 사용가능 방 큐
        PriorityQueue<Room> availableRoomQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.no));
        // 종료시간 기준으로 사용중인 방 큐
        PriorityQueue<Room> usingRoomQueue = new PriorityQueue<>(
                Comparator.comparingInt((Room a) -> a.time)
                        .thenComparingInt(a -> a.no)
        );
        //  시작시간 기준 우선순위큐
        Queue<int[]> meetingQueue = Arrays.stream(meetings)
                .sorted(Comparator.comparingInt(a -> a[0]))
                .collect(Collectors.toCollection(LinkedList::new));

        // 사용가능한 방들 전부 방번호 우선순위 큐에 추가
        for (int i = 0; i < n; i++) {
            availableRoomQueue.add(new Room(i, 0, 0));
        }

        while (!meetingQueue.isEmpty()) {
            int[] meeting = meetingQueue.poll(); // 우선순위 높은 회의 꺼내서

            // 일단 그 전에 쓸 수 있는 방 최신화
            while (!usingRoomQueue.isEmpty() && usingRoomQueue.peek().time <= meeting[0]) {
                Room finishedRoom = usingRoomQueue.poll();
                availableRoomQueue.add(finishedRoom);
            }

            if (!availableRoomQueue.isEmpty()) { // 쓸 수 있는 방이 있다면
                Room room = availableRoomQueue.poll(); // 이용 우선순위 높은 방에 넣을 예정
                room.count++;
                room.time =  meeting[1]; // 회의가 끝날 시간 갱신
                usingRoomQueue.add(room); // 사용중인 방 큐에 넣기
            } else { // 없다면 새 방이 나올때까지 존버
                Room room = usingRoomQueue.poll(); // 가장 먼저 끝나는 회의실
                room.count++;
                room.time += meeting[1]-meeting[0]; // 회의가 끝날 시간 갱신
                usingRoomQueue.add(room); // 사용중인 방 큐에 넣기
            }
        }

        // 모든 사용중인 방 종료처리
        while (!usingRoomQueue.isEmpty()) {
            availableRoomQueue.add(usingRoomQueue.poll());
        }

        // 회의 진행한 회의실 번호 찾기
        int maxCount = 0;
        // 가장 많이 회의가 진행된 방을 찾음
        while (!availableRoomQueue.isEmpty()) {
            Room room = availableRoomQueue.poll();
            if (room.count > maxCount) {
                maxCount = room.count;
                answer = room.no;
            }
        }

        return answer;
    }

    private class Room {
        int no;     // 방 번호
        int count;  // 해당 방에서 진행한 회의 수
        int time;   // 마지막 회의 종료 시간

        public Room(int no, int count, int time) {
            this.no = no;
            this.count = count;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        MeetingRoom T = new MeetingRoom();
        System.out.println(T.solution(2, new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}})); // 0
        System.out.println(T.solution(3, new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}})); // 0
        System.out.println(T.solution(3, new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}})); // 1
        System.out.println(T.solution(4, new int[][]{{3, 20}, {1, 25}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}})); // 2
    }
}
