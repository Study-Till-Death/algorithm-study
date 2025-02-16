package com.example.algorithmstudy.lessons026;

import java.util.*;
class Solution {
    public int solution(int[][] board){
        int answer=Integer.MAX_VALUE;
        List<int[]> list = new ArrayList<>();

        //순회 한번 해서 사람들 위치 모두 수집하기
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]==1){
                    list.add(new int[]{i,j});
                }
            }
        }

        //모든 경우의 위치에 대해서 사람들의 이동거리 구하기
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                int count = 0;
                for(int[] person : list){
                    int x = Math.abs(person[0] - i);
                    int y = Math.abs(person[1] - j);
                    count+=(x+y);
                }
                
                //이동거리가 더 적은 수를 정답으로
                answer = Math.min(answer, count);

            }
        }

        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[][]{{1, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
        System.out.println(T.solution(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}}));
        System.out.println(T.solution(new int[][]{{1, 0, 0, 0, 1, 1}, {0, 1, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 1, 1}}));
    }
}