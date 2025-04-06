package com.example.algorithmstudy.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BuildHouse {
    //
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        // 어쩌란거여
        // 그냥 간 건물에서 각 0번 타일까지 얼마가 걸리는지 작성하고 다 더한값중 최소값 구하면 될 거 같긴한데 너무 노가다인데
        List<Point> buildings = new ArrayList<>();
        // 못가는 곳 체크
        boolean[][] canGo = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    buildings.add(new Point(i, j, 0));
                }
                if (board[i][j] == 0) {
                    canGo[i][j] = true;
                }
            }
        }

        // 모든 건물들까지의 거리들의 총합을 입력할 곳
        int[][] result = new int[board.length][board[0].length];
        
        for (int i = 0; i < buildings.size(); i++) {
            Point building = buildings.get(i);
            int[][] temp = new int[board.length][board[0].length];
            Queue<Point> queue = new LinkedList<>();
            queue.add(building);
            // 현재 건물 기준으로 bfs 해서 모든 갈 수 있는 0번칸에 대한 거리 정리
            while (!queue.isEmpty()) {
                Point point = queue.poll();
                if (point.x > 0 && board[point.x - 1][point.y] == 0 && temp[point.x - 1][point.y] == 0) {
                    temp[point.x - 1][point.y] = point.distance + 1;
                    queue.add(new Point(point.x - 1, point.y, point.distance + 1));
                }
                if (point.x < board.length - 1 && board[point.x + 1][point.y] == 0 && temp[point.x + 1][point.y] == 0) {
                    temp[point.x + 1][point.y] = point.distance + 1;
                    queue.add(new Point(point.x + 1, point.y, point.distance + 1));
                }
                if (point.y > 0 && board[point.x][point.y - 1] == 0 && temp[point.x][point.y - 1] == 0) {
                    temp[point.x][point.y - 1] = point.distance + 1;
                    queue.add(new Point(point.x, point.y - 1, point.distance + 1));
                }
                if (point.y < board.length - 1 && board[point.x][point.y + 1] == 0 && temp[point.x][point.y + 1] == 0) {
                    temp[point.x][point.y + 1] = point.distance + 1;
                    queue.add(new Point(point.x, point.y + 1, point.distance + 1));
                }
            }
            // bfs 이후 temp 에 저장된 결과값을 result 에 추가
            for (int j = 0; j < board[0].length; j++) {
                for (int k = 0; k < board.length; k++) {
                    if (board[k][j] == 0 && temp[k][j] == 0) { // 빈땅인데 못가는 곳
                        canGo[k][j] = false;
                        continue;
                    }
                    result[k][j] += temp[k][j];
                }
            }
        }

        // 최솟값 정리
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (canGo[i][j]) {
                    answer = Math.min(answer, result[i][j]);
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    record Point(int x, int y, int distance) {
    }

    public static void main(String[] args) {
        BuildHouse T = new BuildHouse();
        System.out.println(T.solution(new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 2, 1, 0, 0}, {2, 0, 0, 2, 2}, {0, 0, 0, 0, 0}}));
        System.out.println(T.solution(new int[][]{{1, 0, 0, 1}, {0, 0, 2, 0}, {0, 0, 1, 0}, {2, 2, 0, 0}}));
        System.out.println(T.solution(new int[][]{{1, 2, 0, 0}, {0, 0, 1, 2}, {0, 2, 0, 0}, {0, 2, 1, 0}}));
        System.out.println(T.solution(new int[][]{{1, 0, 0, 1}, {0, 0, 2, 0}, {0, 0, 1, 0}, {2, 2, 0, 1}}));
    }
}
