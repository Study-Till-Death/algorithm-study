package com.example.algorithmstudy.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BallGulligi {
    public int solution(int[][] board, int[] s, int[] e) {
        int answer = Integer.MAX_VALUE;
        int[][] moveCounts = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                moveCounts[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(s[0], s[1], 0));
        // 귀찮으니 1~4 만 바로 쓰게 0 에 0 넣어줌
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == e[0] && point.y == e[1]) {
                answer = Math.min(answer, point.moveCount);
                continue;
            }

            // 4방향 이동
            for (int i = 0; i < 4; i++) {
                int x = point.x;
                int y = point.y;
                int moveCount = point.moveCount;

                // 그 방향으로 1 또는 맵 끝까지 갈 때까지 moveCount 증가
                while (x + dx[i] < board.length
                        && y + dy[i] < board[0].length
                        && x + dx[i] >= 0
                        && y + dy[i] >= 0
                        && board[x + dx[i]][y + dy[i]] == 0) {
                    x += dx[i];
                    y += dy[i];
                    moveCount++;
                }
                if (moveCount < moveCounts[x][y]) {
                    moveCounts[x][y] = moveCount;
                    queue.add(new Point(x, y, moveCount));
                }
            }
        }

        // 도달 못 하는 경우는 있을 수 있으므로 체크
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    record Point(
            int x,
            int y,
            int moveCount
    ) {
    }

    public static void main(String[] args) {
        BallGulligi T = new BallGulligi();
        System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 0}}, new int[]{1, 0}, new int[]{4, 5}));
        System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{4, 2}));
        System.out.println(T.solution(new int[][]{{1, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}}, new int[]{0, 3}, new int[]{4, 2}));
        System.out.println(T.solution(new int[][]{{0, 1, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{4, 5}));
        System.out.println(T.solution(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{4, 3}));
    }
}
