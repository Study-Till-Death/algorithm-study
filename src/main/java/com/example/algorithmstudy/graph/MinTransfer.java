package com.example.algorithmstudy.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class MinTransfer {
    public int solution(int[][] routes, int s, int e){
        int answer = 0;
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(s, -1)); // 환승횟수가 실제 이용횟수 -1 이기 때문에 그냥 -1 주고 시작함
        // 단 위처럼 -1 주고 시작시 출발 도착목표지가 같을경우 -1 로 되버리지만 문제에서 그런 경우는 없다고 조건에 명시해두었으므로 그냥씀
        
        // 이론상 같은 노선을 두번탈 필요는 없음
        // 고로 transferCount 는 rountes.length 보다 적어야함
        while(!queue.isEmpty()){
            Point point = queue.poll();
            if (point.transferCount >= routes.length){
                return -1;
            }
            if (point.now == e){
                return point.transferCount;
            }
            for (int[] route : routes) {
                if (IntStream.of(route).anyMatch(n -> n== point.now)){ // 현재 위치에서 이용가능한 노선이면
                    for (int end : route) {
                        if (end != point.now){
                            queue.add(new Point(end, point.transferCount + 1));
                        }
                    }
                }
            }
        }

        return answer;
    }

    private record Point(
            int now,
            int transferCount
    ) {
    }

    public static void main(String[] args){
        MinTransfer T = new MinTransfer();
        System.out.println(T.solution(new int[][]{{1, 2, 3, 4, 5, 6, 19}, {2, 7, 8, 13}, {5, 9, 10}, {9, 11, 12, 18}, {13, 14, 15}, {14, 12, 16, 17}}, 1, 12));
        System.out.println(T.solution(new int[][]{{1, 3, 5, 7}, {9, 3, 12}, {6, 5, 8}, {2, 8, 14, 15}, {2, 14, 16}}, 1, 14));
        System.out.println(T.solution(new int[][]{{7, 12},{5, 19},{7, 19},{9, 12, 13},{9, 5, 15}}, 9, 19));
        System.out.println(T.solution(new int[][]{{1, 2, 3, 4, 5},{9, 7, 10},{7, 6, 3, 8}, {5, 11, 8, 12}}, 1, 10));
    }
}
