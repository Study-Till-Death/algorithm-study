package com.algorithm;

import java.util.ArrayList;

public class a05_maximumLengthBitonicSequence {
    public int solution(int[] nums){
        int answer = 0; // 가장 긴 바이토닉 수열의 길이
        ArrayList<Integer> temp; // 현재 세고있는 수열 담을 리스트
        boolean increasing; // 증가여부
        boolean decreasing; // 감소여부

        ArrayList<Integer> bitonicSequence = new ArrayList<>();
        // int 배열을 ArrayList로 변환
        for (int num : nums) {
            bitonicSequence.add(num); // Autoboxing (int -> Integer)
        }

        for (int j = 0; j < bitonicSequence.size()-1; j++) { // 각 index를 시작점으로 체크
            temp = new ArrayList<>();
            increasing=false;
            decreasing=false; // index 갱신 시 초기화
            for (int i = j; i < bitonicSequence.size()-1; i++) { // 바이토닉 체크
                temp.add(bitonicSequence.get(i));
                if (bitonicSequence.get(i)==bitonicSequence.get(i+1)){ // 현재값과 다음값이 같은 경우
                    if (increasing && decreasing && answer<temp.size()) { // 증가,감소 이력이 있고 임시리스트의 길이가 저장된 최대 길이보다 길 때
                        answer = temp.size();
                    }
                    break; // 다음 index로 이동
                } else if (bitonicSequence.get(i)<bitonicSequence.get(i+1)) { // 증가
                    if (!decreasing) { // 감소이력 없을 경우
                        increasing = true;
                    } else { // 감소이력 있을 때 다음 index부터는 바이토닉 성립x
                        if (answer<temp.size()) answer = temp.size();
                        break; // 다음 index로 이동
                    }
                } else { //감소
                    if (!increasing) { // 증가이력 없는데 감소 되었을 경우 어떤 경우도 성립x
                        break;
                    } else {
                        decreasing = true;
                        // 감소예정인데 다음 반복문을 돌지 않을 경우
                        if (i==bitonicSequence.size()-2) temp.add(bitonicSequence.get(i+1));
                        if (answer<temp.size()) answer = temp.size();
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args){
        a05_maximumLengthBitonicSequence T = new a05_maximumLengthBitonicSequence();
        System.out.println(T.solution(new int[]{1, 3, 2, 5, 7, 4, 2, 5, 1}));
        System.out.println(T.solution(new int[]{1, 1, 2, 3, 5, 7, 4, 3, 1, 2}));
        System.out.println(T.solution(new int[]{3, 2, 1, 3, 2, 4, 6, 7, 3, 1}));
        System.out.println(T.solution(new int[]{1, 3, 1, 2, 1, 5, 3, 2, 1, 1}));
    }
}