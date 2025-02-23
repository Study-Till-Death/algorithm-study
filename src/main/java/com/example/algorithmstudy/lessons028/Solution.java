package com.example.algorithmstudy.lessons028;

import java.util.*;
class Solution {
    public int solution(int[][] meetings){
        int answer = 0;
        ArrayList<int[]> rooms = new ArrayList<>();

        //미팅 시작시간순으로 정렬
        Arrays.sort(meetings, (a,b) -> a[0] - b[0]);

        for(int[] meeting : meetings){
            boolean isRoomEmpty = false;

            //사용 중인 미팅룸 중에 사용할 수 있는 미팅룸 찾기
            for(int[] room : rooms){
                if(meeting[0] >= room[1]){
                    room = meeting;
                    isRoomEmpty = true;
                    break;
                }
            }

            //없으면 새 미팅룸 사용하기
            if(!isRoomEmpty){
                rooms.add(meeting);
            }

            //사용 중인 미팅룸 미팅 종료시간순으로 정렬하기
            rooms.sort((a,b) -> a[1] - b[1]);
        }

        answer = rooms.size();

        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[][]{{0, 10}, {20, 25}, {5, 15}, {2, 5}}));
        System.out.println(T.solution(new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}}));
        System.out.println(T.solution(new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
        System.out.println(T.solution(new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}}));
    }
}