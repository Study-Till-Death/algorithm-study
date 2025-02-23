package com.example.algorithmstudy.sortingAndThinking;

import java.util.ArrayList;
import java.util.List;

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

    // 답지풀이
    // 전체 이벤트를 1 시작 2 종료로 구분해서 하나의 리스트에 넣고 매 이벤트마다 cnt 체크해서 최대값을 답으로 가져감
    public int solution2(int[][] meetings){
        List<int[]> list = new ArrayList<>();
        for(int[] x : meetings){
            list.add(new int[]{x[0], 1});
            list.add(new int[]{x[1], 2});
        }
        list.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int answer = 0, cnt = 0;
        for(int[] x : list){
            if(x[1] == 1) cnt++;
            else cnt--;
            answer = Math.max(answer, cnt);
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
