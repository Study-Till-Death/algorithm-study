package com.example.algorithmstudy.lessons049;

import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //이 곳에 도달하기까지 깬 벽의 수
        int[][] cost = new int[board.length][board[0].length];
        
        //cost 초기화
        for(int i = 0 ; i < cost.length ; i++) {
            for(int j = 0 ; j < cost[0].length ; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(0, 0, 0));

        while (!queue.isEmpty()) {
            Location location = queue.poll();

            //도착
            if(location.x == board.length - 1 && location.y == board[0].length - 1) {
                answer = Math.min(answer, location.broken);
                continue;
            }

            for(int[] d : direction) {
                int x = location.x + d[0];
                int y = location.y + d[1];

                //범위내인지 체크
                if(x >= 0 && x < board.length &&
                        y >= 0 && y < board[0].length) {
                    Location nextLocation = new Location(x, y, location.broken);
                    //도착지가 벽인지 체크
                    if(board[x][y] == 1) {
                        nextLocation.broken++;
                    }

                    if(cost[x][y] > nextLocation.broken) {
                        cost[x][y] = nextLocation.broken;
                        queue.add(nextLocation);
                    }
                }
            }
        }

        return answer;
    }

    private static class Location {
        int x;
        int y;
        int broken;

        public Location(int x, int y, int broken) {
            this.x = x;
            this.y = y;
            this.broken = broken;
        }
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[][]{{0, 1, 1, 0}, {1, 0, 0, 1}, {0, 1, 0, 0}}));
        System.out.println(T.solution(new int[][]{{0, 1, 1, 0},{1, 1, 0, 1},{0, 0, 1, 0}, {0, 1, 1, 1}, {0, 1, 1, 0}}));
        System.out.println(T.solution(new int[][]{{0, 1, 1, 0, 1, 1},{0, 1, 1, 1, 1, 1},{1, 0, 0, 0, 1, 1}, {1, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 0}, {1, 0, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 0}}));
        System.out.println(T.solution(new int[][]{{0, 1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 0, 1, 1}, {1, 1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 0}}));
        System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 1, 1, 1},{1, 1, 0, 0, 1, 1, 1},{1, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1}, {1, 0, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1, 0}}));
    }
}