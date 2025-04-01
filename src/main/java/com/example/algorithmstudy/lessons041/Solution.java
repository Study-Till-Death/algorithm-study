package com.example.algorithmstudy.lessons041;

import java.util.*;
class Solution {
    int[] road;
    int answer;

    public int solution(int[] nums){
        int n = nums.length;
        int jumps = 0;

        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);

        //queue가 비었다 = 도착했거나, 더 이상 나아가지 못하는 상황이다
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                if (current == n - 1) return jumps; // 상점에 도착하면 점프 횟수 반환

                // 현재 위치에서 점프할 수 있는 범위 탐색
                for (int next = current + 1; next <= Math.min(n - 1, current + nums[current]); next++) {
                    queue.add(next); // 큐에 추가
                }
            }
            jumps++; // 한 번의 점프 완료 후 횟수 증가
        }

        return -1; // 더 이상 갈 수 없으면 -1
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{2, 2, 1, 2, 1, 1}));
        System.out.println(T.solution(new int[]{1, 0, 1, 1, 3, 1, 2, 1}));
        System.out.println(T.solution(new int[]{2, 3, 1, 0, 1, 1, 2, 3, 1, 5, 1, 3, 1}));
        System.out.println(T.solution(new int[]{1, 2, 1, 2, 1, 2, 1, 1, 3, 1, 2, 1}));
        System.out.println(T.solution(new int[]{1, 3, 2, 1, 1, 2, 3, 1, 3, 1, 2, 3, 5, 1, 5, 1, 2, 1, 1}));
    }
}