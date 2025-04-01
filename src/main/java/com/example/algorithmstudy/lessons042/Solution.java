package com.example.algorithmstudy.lessons042;

import java.util.*;
class Solution {
    public int solution(int[] pool, int a, int b, int home){
        int jumps = 0;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[10000];

        queue.add(0);

        //queue가 비었다 = 도착했거나, 더 이상 나아가지 못하는 상황이다
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                if (current == home) return jumps; // 집에 도착하면 점프 횟수 반환

                //웅덩이 체크
                boolean checkForward = true;
                boolean checkBackward = true;
                for(int p : pool) {
                    if(current+a == p) {
                        checkForward = false;
                    }
                    if(current-b == p) {
                        checkBackward = false;
                    }
                }

                //웅덩이 체크에 동과했을 경우, poll에 추가
                if(checkForward && current+a < 10000) {
                    if(!visited[current+a]) {
                        visited[current+a] = true;
                        queue.add(current+a);
                    }
                }

                if(checkBackward && current-b > 0) {
                    if(!visited[current-b]) {
                        visited[current-b] = true;
                        queue.add(current-b);
                    }
                }

            }
            jumps++; // 한 번의 점프 완료 후 횟수 증가
        }

        return -1; // 더 이상 갈 수 없으면 -1
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{11, 7, 20}, 3, 2, 10));
        System.out.println(T.solution(new int[]{1, 15, 11}, 3, 2, 5));
        System.out.println(T.solution(new int[]{9, 15, 35, 30, 20}, 2, 1, 25));
        System.out.println(T.solution(new int[]{5, 12, 7, 19, 23}, 3, 5, 18));
        System.out.println(T.solution(new int[]{10, 15, 20}, 3, 2, 2));
    }
}