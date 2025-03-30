package com.example.algorithmstudy.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GoHome {
    public int solution(int[] pool, int a, int b, int home){
        int answer = -1;
        // 2연 뒷점 막는거 에반데
        // 전 점프 방향 기록해서 보내줘야할 듯
        Queue<Point> queue = new LinkedList<>();
        int[] result = new int[10000];
        queue.add(new Point(0, false));
        while (!queue.isEmpty()){
            Point current = queue.poll();
            int aIndex = current.index+a;
            int bIndex = current.index-b;
            if (canGo(pool, aIndex)){ // 앞점 가능하면
                if (aIndex == home){
                    return result[current.index] +1;
                }
                result[aIndex] = result[current.index] +1;
                queue.add(new Point(aIndex, false));
            }
            if (current.beforeBackJump){ // 이미 뒷점 했으면 여기서 스탑
                continue;
            }
            if (canBack(pool, bIndex)){ // 뒷점 가능하면
                if (bIndex == home){
                    return result[current.index] +1;
                }
                result[bIndex] = result[current.index] +1;
                queue.add(new Point(bIndex, true));
            }
        }

        return answer;
    }

    private static boolean canBack(int[] pool, int bIndex) {
        return bIndex > 0 && !Arrays.stream(pool).anyMatch(point -> point == bIndex);
    }

    private static boolean canGo(int[] pool, int aIndex) {
        return aIndex < 10000 && !Arrays.stream(pool).anyMatch(point -> point == aIndex) ;
    }

    private class Point{
        int index;
        boolean beforeBackJump;

        public Point(int index, boolean beforeBackJump) {
            this.index = index;
            this.beforeBackJump = beforeBackJump;
        }
    }

    public static void main(String[] args){
        GoHome T = new GoHome();
        System.out.println(T.solution(new int[]{11, 7, 20}, 3, 2, 10));
        System.out.println(T.solution(new int[]{1, 15, 11}, 3, 2, 5));
        System.out.println(T.solution(new int[]{9, 15, 35, 30, 20}, 2, 1, 25));
        System.out.println(T.solution(new int[]{5, 12, 7, 19, 23}, 3, 5, 18));
        System.out.println(T.solution(new int[]{10, 15, 20}, 3, 2, 2));
    }
}
