package com.example.algorithmstudy.graph;

import java.util.LinkedList;
import java.util.Queue;

public class MinAirfare {
    public int solution(int n, int[][] flights, int s, int e, int k){
        int answer = Integer.MAX_VALUE;
        // 대충 돌리면서 최소값 기록해주는 식으로 진행해야할 거 같
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(s,0,0));

        while (!queue.isEmpty()){
            Point point = queue.poll();
            if (point.transferCount > k+1){ // 환승횟수 넘으면 탐색 중지
                return answer==Integer.MAX_VALUE?-1:answer;
            }
            if (point.now == e){ // 도착하면 최솟값만 기록
                answer = Math.min(answer,point.cost);
                continue;
            }

            for (int[] flight : flights) { // 가능한 여행편 queue 에 추가
                if (flight[0] == point.now){
                    queue.add(new Point(flight[1], point.cost + flight[2],point.transferCount+1));
                }
            }
        }

        return answer;
    }

    private record Point (
            int now, // 위치
            int cost,   // 요금
            int transferCount   // 환승 횟수
            ){
    }

    public static void main(String[] args){
        MinAirfare T = new MinAirfare();
        System.out.println(T.solution(5, new int[][]{{0, 1, 10}, {1, 2, 20}, {0, 2, 70}, {0, 3, 100}, {1, 3, 80}, {2, 3, 10}, {2, 4, 30}, {3, 4, 10}}, 0, 3, 1));
        System.out.println(T.solution(4, new int[][]{{0, 1, 10}, {0, 2, 10}, {1, 3, 5}, {2, 3, 3}}, 0, 3, 0));
        System.out.println(T.solution(8, new int[][]{{0, 3, 10}, {1, 5, 10}, {1, 7, 100}, {0, 1, 10}, {0, 2, 10}, {5, 7, 30}, {3, 7, 10}, {1, 3, 5}, {2, 3, 3}}, 1, 7, 2));
        System.out.println(T.solution(10, new int[][]{{1, 8, 50}, {0, 8, 30}, {1, 0, 10}, {2, 8, 10}, {0, 3, 10}, {1, 5, 10}, {1, 7, 100}, {0, 1, 10}, {0, 2, 10}, {5, 7, 30}, {3, 7, 10}, {1, 3, 5}, {2, 3, 3}}, 1, 8, 2));
        System.out.println(T.solution(4, new int[][]{{0, 3, 59},{2, 0, 83}, {3, 1, 16}, {1, 3, 16}}, 3, 0, 3));
    }
}
