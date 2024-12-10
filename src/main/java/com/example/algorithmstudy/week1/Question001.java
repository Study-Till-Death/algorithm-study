package com.example.algorithmstudy.week1;
import java.util.*;
class Question001 {
    public char[] solution(int n, int[][] ladder){
        char[] answer = new char[n];

        // 1부터 순서대로 지정 실제 위치 지정
        for (int i = 0; i < n; i++) {
            int location = i;
            for (int[] row : ladder) {
                // row 사다리 가로
                for (int column : row) {
                    if(column-1 == location){
                        location ++;
                    }else if(column == location){
                        location --;
                    }
                }
            }
            System.out.println(location);
            answer[location] = (char) ('A' + i);
        }

        return answer;
    }

    public static void main(String[] args){
        Question001 T = new Question001();
        System.out.println(Arrays.toString(T.solution(5, new int[][]{{1, 3}, {2, 4}, {1, 4}})));
        System.out.println(Arrays.toString(T.solution(7, new int[][]{{1, 3, 5}, {1, 3, 6}, {2, 4}})));
        System.out.println(Arrays.toString(T.solution(8, new int[][]{{1, 5}, {2, 4, 7}, {1, 5, 7}, {2, 5, 7}})));
        System.out.println(Arrays.toString(T.solution(12, new int[][]{{1, 5, 8, 10}, {2, 4, 7}, {1, 5, 7, 9, 11}, {2, 5, 7, 10}, {3, 6, 8, 11}})));
    }
}