package com.algorithm;

import java.util.ArrayList;
import java.util.Collections;

public class a28_minMeetingRoom {
    public int solution(int[][] meetings){
        int answer = 0;
        int now = 0;

        //시작, 종료시간 리스트업
        ArrayList<Integer> startList = new ArrayList<>();
        ArrayList<Integer> endList = new ArrayList<>();

        for (int i = 0; i < meetings.length; i++) {
            startList.add(meetings[i][0]);
            endList.add(meetings[i][1]);
        }

        // 정렬
        Collections.sort(startList);
        Collections.sort(endList);

        int startIdx = 0, endIdx = 0;

        while (startIdx < meetings.length) {
            if (startList.get(startIdx) < endList.get(endIdx)) {
                now++;  // 새 회의 시작
                answer = Math.max(answer, now);
                startIdx++;
            } else {
                now--;  // 기존 회의 종료
                endIdx++;
            }
        }
        //.
        return answer;
    }

    public static void main(String[] args){
        a28_minMeetingRoom T = new a28_minMeetingRoom();
        System.out.println(T.solution(new int[][]{{0, 10}, {20, 25}, {5, 15}, {2, 5}}));
        System.out.println(T.solution(new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}}));
        System.out.println(T.solution(new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
        System.out.println(T.solution(new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}}));
    }
}
