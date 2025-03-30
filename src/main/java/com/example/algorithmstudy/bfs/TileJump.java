package com.example.algorithmstudy.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class TileJump {
    public int solution(int[] nums){
        int answer = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        boolean[] visited = new boolean[nums.length];
        visited[0] = true;
        int[] result = new int[nums.length];
        while(!queue.isEmpty()){
            int current = queue.poll();
            int movement = nums[current];
            for (int i = 1; i <= movement; i++){
                int next = current + i;
                if (next >= nums.length){ // indexOutOfRange 방지
                    break;
                }
                if (visited[next]){ // 이미 누가 먼저갈 방법 찾은 목적지면 무시
                    continue;
                }
                if (next ==  nums.length-1){ // 도착
                    return result[current]+1; // 현재까지 점프수 +1 리턴
                }
                result[next] = result[current]+1;
                visited[next] = true;
                queue.add(next);
            }
        }
        return -1;
    }

    public static void main(String[] args){
        TileJump T = new TileJump();
        System.out.println(T.solution(new int[]{2, 2, 1, 2, 1, 1}));
        System.out.println(T.solution(new int[]{1, 0, 1, 1, 3, 1, 2, 1}));
        System.out.println(T.solution(new int[]{2, 3, 1, 0, 1, 1, 2, 3, 1, 5, 1, 3, 1}));
        System.out.println(T.solution(new int[]{1, 2, 1, 2, 1, 2, 1, 1, 3, 1, 2, 1}));
        System.out.println(T.solution(new int[]{1, 3, 2, 1, 1, 2, 3, 1, 3, 1, 2, 3, 5, 1, 5, 1, 2, 1, 1}));
    }
}
