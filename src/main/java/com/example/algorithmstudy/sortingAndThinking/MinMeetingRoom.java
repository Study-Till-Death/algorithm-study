package com.example.algorithmstudy.sortingAndThinking;

public class MinMeetingRoom {
    public int solution(int[][] meetings){
        int answer = 0;
        // 각 회의 시작할 때마다 지금 몇개인지 세면 될 듯?
        for (int i = 0; i < meetings.length; i++) {
            int startTime = meetings[i][0];
            int count = 1;
            for (int j = 0; j < meetings.length; j++) {
                if (i == j){
                    continue;
                }
                if (startTime > meetings[j][0] &&  startTime < meetings[j][1]){
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }
        return answer;
    }

    public static void main(String[] args){
        MinMeetingRoom T = new MinMeetingRoom();
        System.out.println(T.solution(new int[][]{{0, 10}, {20, 25}, {5, 15}, {2, 5}}));
        System.out.println(T.solution(new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}}));
        System.out.println(T.solution(new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
        System.out.println(T.solution(new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}}));
    }
}
