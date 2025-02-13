package com.algorithm;

import java.util.ArrayList;
import java.util.Collections;

public class a26_meetingPlace {
    public int solution(int[][] board) {
        int n = board.length;
        ArrayList<Integer> students = new ArrayList<>(); // 1개수
        ArrayList<Integer> lengthArr = new ArrayList<>(); // k명의 학생들이 n*n칸 별 걸리는 시간합 리스트

        for (int i = 0; i < n * n; i++) { // 1개수 찾기
            if (board[i / n][i % n] == 1) students.add(i);
        }

        for (int i = 0; i < n * n; i++) {
            int sum=0; // 해당 칸에 모일 때 걸리는 시간합
            for (int j = 0; j < students.size(); j++) { // 학생별 해당 칸까지 걸리는 시간 구하기
                int y = students.get(j) / n;
                int x = students.get(j) % n;
                int compY = i / n;
                int compX = i % n;
               sum += (Math.abs(y - compY) + Math.abs(x - compX));
            }
            lengthArr.add(sum); //시간합 리스트에 추가
        }

        return Collections.min(lengthArr); //리스트 중 최소값
    }

    public static void main(String[] args) {
        a26_meetingPlace T = new a26_meetingPlace();
        System.out.println(T.solution(new int[][]{{1, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
        System.out.println(T.solution(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}}));
        System.out.println(T.solution(new int[][]{{1, 0, 0, 0, 1, 1}, {0, 1, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 1, 1}}));
    }
}
