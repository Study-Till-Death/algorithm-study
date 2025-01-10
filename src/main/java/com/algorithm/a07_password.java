package com.algorithm;

import java.util.Arrays;

public class a07_password {
    public int solution(int[] keypad, String password){
        int answer = 0;
        int[] start = new int[]{};
        int[] passwordArray = password.chars().map(pw->Character.getNumericValue(pw)).toArray();
        int[][][][] neighbors = {
                // 0행
                {   {{0, 1}, {1, 1}, {1, 0}},                   // 0,0
                    {{0, 0}, {0, 2}, {1, 0}, {1, 1}, {1, 2}},   // 0,1
                    {{0, 1}, {1, 1}, {1, 2}}                    // 0,2
                },
                // 1행
                {   {{0, 0}, {0, 1}, {1, 1}, {2, 0}, {2, 1}}, // 1,0
                    {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1}, {2, 2}}, // 1,1
                    {{0, 1}, {0, 2}, {1, 1}, {2, 1}, {2, 2}}  // 1,2
                },
                // 2행
                {   {{1, 0}, {1, 1}, {2, 1}},             // 2,0
                    {{1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 2}}, // 2,1
                    {{1, 1}, {1, 2}, {2, 1}}              // 2,2
                }
        };
        int[][] realNeighbors = new int[][]{};

        for (int i = 0; i < keypad.length; i++) {
            if (passwordArray[0]==keypad[i]) {
                realNeighbors = neighbors[i / 3][i % 3]; // 3*3 키패드 기준 index
                start = new int[]{i / 3, i % 3};
                break;
            }
        }

        for (int i = 0; i < passwordArray.length; i++) {
            answer+=2;
            for (int j = 0; j < realNeighbors.length; j++) {
                if (passwordArray[i]==keypad[realNeighbors[j][0]*3+realNeighbors[j][1]]) {
                    answer--;
                    break;
                }
            }
            if (passwordArray[i]==keypad[start[0]*3+start[1]]) answer-=2;
        }

        return answer;
    }

    public static void main(String[] args){
        a07_password T = new a07_password();
        System.out.println(T.solution(new int[]{2, 5, 3, 7, 1, 6, 4, 9, 8}, "7596218"));
        System.out.println(T.solution(new int[]{1, 5, 7, 3, 2, 8, 9, 4, 6}, "63855526592"));
        System.out.println(T.solution(new int[]{2, 9, 3, 7, 8, 6, 4, 5, 1}, "323254677"));
        System.out.println(T.solution(new int[]{1, 6, 7, 3, 8, 9, 4, 5, 2}, "3337772122"));
    }
}
