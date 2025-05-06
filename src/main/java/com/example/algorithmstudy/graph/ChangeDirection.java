package com.example.algorithmstudy.graph;

import java.util.LinkedList;
import java.util.Queue;

public class ChangeDirection {
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        // 벽부수기와 동일한 방식으로 풀 예정

        int[][] changeCounts = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                changeCounts[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 0));
        // 귀찮으니 1~4 만 바로 쓰게 0 에 0 넣어줌
        int[] dx = {0, 0, 0, 1, -1};
        int[] dy = {0, 1, -1, 0, 0};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == board.length - 1 && point.y == board[0].length - 1) {
                answer = Math.min(answer, point.changeCount);
            }

            // 4방향 이동
            for (int i = 1; i < 5; i++) { // 1부터 4까지
                int x = point.x + dx[i];
                int y = point.y + dy[i];
                int changeCount = point.changeCount;

                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                    if (board[point.x][point.y] != i) { // 원래 방향이동이 아닌 경우의 카운트 증가
                        changeCount++;
                    }
                    if (changeCount < changeCounts[x][y]) {
                        changeCounts[x][y] = changeCount;
                        queue.add(new Point(x, y, changeCount));
                    }
                }
            }
        }

        // 도달 못 하는 경우는 절대 없으므로 별도로 answer 가 Integer.MAX_VALUE 일 경우에 대한 체크는 하지 않음
        return answer;
    }

    record Point(
            int x,
            int y,
            int changeCount
    ) {
    };

    public static void main(String[] args) {
        ChangeDirection T = new ChangeDirection();
        System.out.println(T.solution(new int[][]{{3, 1, 3}, {1, 4, 2}, {4, 2, 3}}));
        System.out.println(T.solution(new int[][]{{3, 2, 1, 3}, {1, 1, 4, 2}, {3, 4, 2, 1}, {1, 2, 2, 4}}));
        System.out.println(T.solution(new int[][]{{3, 2, 1, 3, 1, 2}, {2, 1, 1, 1, 4, 2}, {2, 2, 2, 1, 2, 2}, {1, 3, 3, 4, 4, 4}, {1, 2, 2, 3, 3, 4}}));
        System.out.println(T.solution(new int[][]{{3, 2, 1, 3, 1, 2, 2, 2}, {2, 1, 1, 1, 4, 2, 1, 1}, {2, 2, 2, 1, 2, 2, 3, 4}, {1, 3, 3, 4, 4, 4, 3, 1}, {1, 2, 2, 3, 3, 4, 3, 4}, {1, 2, 2, 3, 3, 1, 1, 1}}));
        System.out.println(T.solution(new int[][]{{1, 2, 3, 2, 1, 3, 1, 2, 2, 2}, {1, 2, 2, 1, 1, 1, 4, 2, 1, 1}, {3, 2, 2, 2, 2, 1, 2, 2, 3, 4}, {3, 3, 1, 3, 3, 4, 4, 4, 3, 1}, {1, 1, 1, 2, 2, 3, 3, 4, 3, 4}, {1, 1, 1, 2, 2, 3, 3, 1, 1, 1}}));
    }
}
