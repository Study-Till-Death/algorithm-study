package com.example.algorithmstudy.simulation.week3;

import java.util.Arrays;

public class Meeting {
    public int[] solution(int[] enter, int[] exit){
        int[] answer = new int[enter.length];
        // 확실히 본 사람만 정리하려면 -> 사람 받고나서 매턴 나갈 수 있는 사람 다 내보냄
        // 사람 들어올 때마다 이미 들어온 사람들 count 증가시켜줌
        // 나가면 카운트 증가시키면 안되므로 안에 들어온 사람들을 따로 저장해서 관리

        // 안에 있는지 체크용
        boolean [] isIn = new boolean[enter.length];
        // 어디까지 나갔는지 exit 인덱스
        int exitIndex = 0;
        for (int i=0; i<enter.length; i++){
            // 입장
            isIn[enter[i]-1] = true;
            // 입장 시 안에 있던 사람의 수 (i = 들어온 사람 , exitIndex = 나간 사람)
            answer[enter[i]-1] = i - exitIndex;
            for (int j=0; j<isIn.length; j++){
                // 안에 있던 사람들 카운트 증가 (본인 제외)
                if(isIn[j] && enter[i]-1!=j){
                    answer[j]++;
                }
            }
            for (int j=exitIndex ; j < exit.length; j++){
                // 나갈 수 있는지 체크
                if(isIn[exit[j]-1]){
                    // 퇴장
                    isIn[exit[j]-1] = false;
                    exitIndex++;
                }else{
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args){
        Meeting T = new Meeting();
        System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 5, 3, 4}, new int[]{2, 3, 1, 4, 5})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 3, 2, 4, 5, 7, 6, 8}, new int[]{2, 3, 5, 6, 1, 4, 8, 7})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 7, 2, 3, 5, 6}, new int[]{5, 2, 6, 1, 7, 3, 4})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 2, 3}, new int[]{2, 1, 4, 3})));
    }
}
