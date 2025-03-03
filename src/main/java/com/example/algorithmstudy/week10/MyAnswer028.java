package com.example.algorithmstudy.week10;
import java.util.*;

public class MyAnswer028 {
    public int solution(int[][] meetings){
        PriorityQueue<Integer> meetingRoom = new PriorityQueue<>();

        Arrays.stream(meetings)
                .sorted(Comparator.comparingInt(a -> a[0]))//시작 시간 기준 정렬
                .forEach(meeting -> {
                    if (!meetingRoom.isEmpty() && meetingRoom.peek() <= meeting[0]) {
                        meetingRoom.poll();//종료된 회의 제거
                    }
                    meetingRoom.add(meeting[1]);//현재 회의 종료 시간 추가
                });

        return meetingRoom.size();//총 room 갯수가 정답임
    }

    public static void main(String[] args){
        MyAnswer028 T = new MyAnswer028();
        System.out.println(T.solution(new int[][]{{0, 10}, {20, 25}, {5, 15}, {2, 5}}));
        System.out.println(T.solution(new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}}));
        System.out.println(T.solution(new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
        System.out.println(T.solution(new int[][]{{0, 4}, {0, 4}, {0, 4}, {0, 4}, {0, 4}, {12, 14}, {15, 20}}));
        System.out.println(T.solution(new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}}));
    }
}
