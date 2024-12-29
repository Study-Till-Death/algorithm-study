package com.example.algorithmstudy.lessons008;

import java.util.*;

class Solution {
    public int[] solution(int[] enter, int[] exit){
        int[] answer = new int[enter.length];

        Set<Integer> room = new HashSet<>();
        HashSet<Integer>[] meetings = new HashSet[enter.length];
        for(int i = 0; i < enter.length; i++){
            meetings[i] = new HashSet<>();
        }

        int exitIdx = 0;
        for(int i=0; i<enter.length; i++) {
            //입장
            room.add(enter[i]);

            //방 안의 사람들끼리 인사
            for(int human : room){
                meetings[human-1].addAll(room);
            }

            //나갈 사람 순서대로 나가기
            for(int j = exitIdx; j < exit.length; j++){
                if(room.contains(exit[j])){
                    room.remove(exit[j]);
                    exitIdx++;
                } else {
                    break;
                }
            }
        }

        for(int i = 0; i < answer.length; i++){
            //항상 자기 자신을 포함함으로 -1
            answer[i] = meetings[i].size()-1;
        }

        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 5, 3, 4}, new int[]{2, 3, 1, 4, 5})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 3, 2, 4, 5, 7, 6, 8}, new int[]{2, 3, 5, 6, 1, 4, 8, 7})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 7, 2, 3, 5, 6}, new int[]{5, 2, 6, 1, 7, 3, 4})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 2, 3}, new int[]{2, 1, 4, 3})));
    }
}
