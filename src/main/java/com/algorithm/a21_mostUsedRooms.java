package com.algorithm;

import java.util.*;

public class a21_mostUsedRooms {
    public int solution(int n, int[][] meetings){
        int answer = 0;
        int[][] frequency = new int[n][2]; // 1. 빈도수 2. 현인덱스 미팅룸 사용끝나는시간
        List<int[]> meetingList= new ArrayList<>();
        int end = 0;

        for (int i = 0; i < meetings.length; i++) {
            meetingList.add(meetings[i]);
        }

        Collections.sort(meetingList, Comparator.comparing((int[] o) -> o[0]));

        while (true) {
            for (int i = 0; i < meetingList.size(); i++) {
                if (meetingList.get(i)[0]<=end) {
                    for (int j = 0; j < frequency.length; j++) {
                        if(frequency[j][1] <= meetingList.get(i)[0]) {
                            frequency[j][0]++; // 빈도 증가
                            frequency[j][1]+=meetingList.get(i)[1];
                            end+=meetingList.get(i)[1];
                            meetingList.remove(i);
                            break;
                        }

                        if (j==frequency.length-1) end++;
                    }
                }
            }
            if (meetingList.size()==0) {
                int max=0;
                for (int i = frequency.length; 0 < i ; i--) {
                    if(frequency[i][0]>max) {
                        max = frequency[i][0];
                        answer=i;
                    }
                }
                break;
            }
        }


        return answer;
    }

    public static void main(String[] args){
        a21_mostUsedRooms T = new a21_mostUsedRooms();
        System.out.println(T.solution(2, new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}}));
        System.out.println(T.solution(3, new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
        System.out.println(T.solution(3, new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}}));
        System.out.println(T.solution(4, new int[][]{{3, 20}, {1, 25}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
    }
}
