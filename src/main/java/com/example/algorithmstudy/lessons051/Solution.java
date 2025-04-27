package com.example.algorithmstudy.lessons051;

import java.util.*;
class Solution {
    public int solution(int[][] board, int[] s, int[] e){
        int answer = Integer.MAX_VALUE;
        int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        boolean[][] visited = new boolean[board.length][board[0].length];
        PriorityQueue<Location> queue = new PriorityQueue<Location>();
        queue.add(new Location(s[0], s[1], 0));

        while(!queue.isEmpty()){
            Location current = queue.poll();

            if(current.x == e[0] && current.y == e[1]){
                answer = Math.min(answer, current.moved);
                return answer;
            }

            for(int[] d : direction){
                int x = current.x + d[0];
                int y = current.y + d[1];

                if(x >= 0 && x < board.length &&
                        y >= 0 && y < board[0].length &&
                        board[x][y] == 0 && !visited[x][y]){
                    visited[x][y] = true;
                    Location next = new Location(x, y, current.moved+1);

                    queue.add(next);
                }
            }
        }

        return -1;
    }

    private static final class Location implements Comparable<Location> {
        int x;
        int y;
        int moved;

        public Location(int x, int y, int moved) {
            this.x = x;
            this.y = y;
            this.moved = moved;
        }

        @Override
        public int compareTo(Location o) {
            return o.moved - moved;
        }
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 0}}, new int[]{1, 0}, new int[]{4, 5}));
        System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{4, 2}));
        System.out.println(T.solution(new int[][]{{1, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}}, new int[]{0, 3}, new int[]{4, 2}));
        System.out.println(T.solution(new int[][]{{0, 1, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{4, 5}));
        System.out.println(T.solution(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{4, 3}));
    }
}

