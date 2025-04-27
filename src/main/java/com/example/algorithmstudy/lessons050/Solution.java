package com.example.algorithmstudy.lessons050;

import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        //이 곳에 도달하기까지 방향을 바꾼 수
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
                answer = Math.min(answer, location.changed);
                continue;
            }

            for(int i = 0 ; i < direction.length ; i++) {
                int x = location.x + direction[i][0];
                int y = location.y + direction[i][1];

                //범위내인지 체크
                if(x >= 0 && x < board.length &&
                        y >= 0 && y < board[0].length) {
                    Location nextLocation = new Location(x, y, location.changed);
                    //방향 바꿨는지 체크
                    if(board[location.x][location.y] != i+1) {
                        nextLocation.changed++;
                    }

                    if(cost[x][y] > nextLocation.changed) {
                        cost[x][y] = nextLocation.changed;
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
        int changed;

        public Location(int x, int y, int changed) {
            this.x = x;
            this.y = y;
            this.changed = changed;
        }
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[][]{{3, 1, 3}, {1, 4, 2}, {4, 2, 3}}));
        System.out.println(T.solution(new int[][]{{3, 2, 1, 3}, {1, 1, 4, 2}, {3, 4, 2, 1}, {1, 2, 2, 4}}));
        System.out.println(T.solution(new int[][]{{3, 2, 1, 3, 1, 2}, {2, 1, 1, 1, 4, 2}, {2, 2, 2, 1, 2, 2}, {1, 3, 3, 4, 4, 4}, {1, 2, 2, 3, 3, 4}}));
        System.out.println(T.solution(new int[][]{{3, 2, 1, 3, 1, 2, 2, 2}, {2, 1, 1, 1, 4, 2, 1, 1}, {2, 2, 2, 1, 2, 2, 3, 4}, {1, 3, 3, 4, 4, 4, 3, 1}, {1, 2, 2, 3, 3, 4, 3, 4}, {1, 2, 2, 3, 3, 1, 1, 1}}));
        System.out.println(T.solution(new int[][]{{1, 2, 3, 2, 1, 3, 1, 2, 2, 2}, {1, 2, 2, 1, 1, 1, 4, 2, 1, 1}, {3, 2, 2, 2, 2, 1, 2, 2, 3, 4}, {3, 3, 1, 3, 3, 4, 4, 4, 3, 1}, {1, 1, 1, 2, 2, 3, 3, 4, 3, 4}, {1, 1, 1, 2, 2, 3, 3, 1, 1, 1}}));
    }
}