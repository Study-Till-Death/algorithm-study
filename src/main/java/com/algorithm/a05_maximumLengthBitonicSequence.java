package com.algorithm;

import java.util.ArrayList;

public class a05_maximumLengthBitonicSequence {
    public int solution(int[] nums){
        int answer = 0; // 가장 긴 바이토닉 수열의 길이
        ArrayList<Integer> temp = new ArrayList<>();
        boolean increasing=false;
        boolean decreasing=false;

        ArrayList<Integer> bitonicSequence = new ArrayList<>();
        // int 배열을 ArrayList로 변환
        for (int num : nums) {
            bitonicSequence.add(num); // Autoboxing (int -> Integer)
        }

        for (int i = 0; i < bitonicSequence.size()-1; i++) {
            if (bitonicSequence.get(i)==bitonicSequence.get(i+1)){
                temp.add(bitonicSequence.get(i));
                if (increasing && decreasing && answer<temp.size()) {
                    answer = temp.size();
                }
                temp = new ArrayList<>();
                increasing=false;
                decreasing=false;
            } else if (bitonicSequence.get(i)<bitonicSequence.get(i+1)) {
                temp.add(bitonicSequence.get(i));
                increasing = true;
            } else {
                if (!increasing) {
                    temp = new ArrayList<>();
                    increasing=false;
                    decreasing=false;
                }
                temp.add(bitonicSequence.get(i));
                decreasing = true;
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