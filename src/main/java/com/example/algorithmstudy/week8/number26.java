package com.example.algorithmstudy.week8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution26 {
    public int solution26(int[][] board) {
        // 정렬해서 중간값 찾기

        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    x.add(i);
                    y.add(j);
                }
            }
        }

        Collections.sort(x);
        Collections.sort(y);

        int medianX = x.get(x.size() / 2);
        int medianY = y.get(y.size() / 2);

        int distance = 0;
        for (int i = 0; i < x.size(); i++) {
            distance += Math.abs(x.get(i) - medianX);
            distance += Math.abs(y.get(i) - medianY);
        }

        return distance;
    }

    public static void main(String[] args){
        Solution26 T = new Solution26();
        System.out.println(T.solution26(new int[][]{{1, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
        System.out.println(T.solution26(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}}));
        System.out.println(T.solution26(new int[][]{{1, 0, 0, 0, 1, 1}, {0, 1, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 1, 1}}));
    }
}