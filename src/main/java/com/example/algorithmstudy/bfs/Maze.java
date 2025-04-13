package com.example.algorithmstudy.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Maze {
    public int solution(int[][] board){
        // 마제소바
        int answer = 0;
        int[][] result = new int[board.length][board[0].length];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0,0));
        while (!queue.isEmpty()){
            Point point = queue.poll();
            if (board[point.x][point.y] == 1){
                // 현재지점이 벽이면 다음 진행 x
                continue;
            }
            if (point.x == 6 && point.y == 6){
                // 도착하면 탈출
                return point.time;
            }
            // 4방향 추가
            if (point.x > 0){
                queue.add(new Point(point.x - 1, point.y, point.time+1));
            }
            if (point.x != board.length-1){
                queue.add(new Point(point.x + 1, point.y, point.time+1));
            }
            if (point.y > 0){
                queue.add(new Point(point.x, point.y - 1, point.time+1));
            }
            if (point.y != board[0].length-1){
                queue.add(new Point(point.x, point.y + 1, point.time+1));
            }
        }

        return -1;
    }

    record Point(int x, int y, int time){
        // x , y, 현재까지 소요시간 정리한 클래스
    }

    public static void main(String[] args){
        Maze T = new Maze();
        int[][] arr={{0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {1, 1, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 0, 0, 0}};
        System.out.println(T.solution(arr));
    }
}
