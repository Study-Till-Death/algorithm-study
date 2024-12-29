package com.example.algorithmstudy.lessons004;

import java.util.Arrays;

public class Solution {
    public int[] solution(int c, int r, int k){
        int[] answer = new int[2];

        int[][] hall = new int[c][r];
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int direction = 0;

        if(k > r*c){
            return new int[] {0,0};
        }

        for(int i = 0 ; i < k-1 ;i++) {
                int nextX = answer[0] + directions[direction%4][0];
                int nextY = answer[1] + directions[direction%4][1];

                if(nextX < c && nextX > -1 && nextY < r && nextY > -1 && hall[nextX][nextY] < 1){
                    hall[answer[0]][answer[1]] = 1;
                    answer = new int[] {nextX, nextY};
                } else {
                    direction++;
                    i--;
                }
        }
      
        return new int[] {answer[0]+1,answer[1]+1};
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(Arrays.toString(T.solution(6, 5, 12)));
        System.out.println(Arrays.toString(T.solution(6, 5, 20)));
        System.out.println(Arrays.toString(T.solution(6, 5, 30)));
        System.out.println(Arrays.toString(T.solution(6, 5, 31)));
    }
}
