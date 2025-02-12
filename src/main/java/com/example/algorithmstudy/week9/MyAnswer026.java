package com.example.algorithmstudy.week9;
import java.util.*;

public class MyAnswer026 {
    public int solution(int[][] board){

        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        int n = board.length;

        //x, y 좌표값 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    xList.add(i);
                    yList.add(j);
                }
            }
        }

        //중앙값 구하기 위해 정렬
        Collections.sort(xList);
        Collections.sort(yList);

        //점심 문제 토론회 때 평균이 아니라 중간 값을 말해야 했는데 잘못 말함
        //각 X,Y 중앙 값 구하기
        int centerX = xList.get(xList.size() / 2);
        int centerY = yList.get(yList.size() / 2);

        int shortMoveDistance = 0;
        //중앙 값에서 빼면서 이동 거리 계산
        for (int x : xList) shortMoveDistance += Math.abs(x - centerX);
        for (int y : yList) shortMoveDistance += Math.abs(y - centerY);

        return shortMoveDistance;
    }

    public static void main(String[] args){
        MyAnswer026 T = new MyAnswer026();
        System.out.println(T.solution(new int[][]{{1, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
        System.out.println(T.solution(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}}));
        System.out.println(T.solution(new int[][]{{1, 0, 0, 0, 1, 1}, {0, 1, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 1, 1}}));
    }
}
