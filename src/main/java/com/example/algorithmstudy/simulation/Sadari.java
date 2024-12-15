package com.example.algorithmstudy.simulation;

import java.util.Arrays;

public class Sadari {
    public char[] solution(int n, int[][] ladder){
        char[] answer = new char[n];

        // 문자들 순서대로 n개 넣어주고
        for (int i = 0; i < n; i++) {
            answer[i] = (char)(65+ i);
        }

        //같은 순서에 자리교환이 일어나는 애들마다 구분
        for (int[] rows : ladder) { // {1,3} {2,4} {1,4} 분리
            //자리교환 (적힌 숫자 -1, 적힌숫자)
            for (int column : rows) {
                char x1 = answer[column - 1]; // 자리 바꿀 자리 1 문자
                char x2 = answer[column]; // 자리 바꿀 자리 2 문자
                answer[column] = x1;
                answer[column - 1] = x2;
            }
        }

        // answer 출력인데 그냥 만들기만 하면 되는 듯
        return answer;
    }

    public static void main(String[] args){
        Sadari T = new Sadari();
        System.out.println(Arrays.toString(T.solution(5, new int[][]{{1, 3}, {2, 4}, {1, 4}})));
        System.out.println(Arrays.toString(T.solution(7, new int[][]{{1, 3, 5}, {1, 3, 6}, {2, 4}})));
        System.out.println(Arrays.toString(T.solution(8, new int[][]{{1, 5}, {2, 4, 7}, {1, 5, 7}, {2, 5, 7}})));
        System.out.println(Arrays.toString(T.solution(12, new int[][]{{1, 5, 8, 10}, {2, 4, 7}, {1, 5, 7, 9, 11}, {2, 5, 7, 10}, {3, 6, 8, 11}})));
    }
}
