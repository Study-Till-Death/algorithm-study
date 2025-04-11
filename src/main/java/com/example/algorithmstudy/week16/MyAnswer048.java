package com.example.algorithmstudy.week16;
import java.util.*;

public class MyAnswer048 {
    public int solution(int[][] routes, int s, int e) {
        if (s == e) return 0;

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int n = routes.length;

        for (int i = 0; i < n; i++) {
            for (int stop : routes[i]) {
                graph.putIfAbsent(stop, new HashSet<>());
                graph.get(stop).add(i);
            }
        }

        Queue<Integer> Q = new LinkedList<>();
        Set<Integer> visitedStops = new HashSet<>();
        boolean[] visitedLines = new boolean[n];

        Q.offer(s);
        visitedStops.add(s);

        int transfer = 0;

        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                int curStop = Q.poll();
                for (int line : graph.getOrDefault(curStop, new HashSet<>())) {
                    if (visitedLines[line]) continue;
                    visitedLines[line] = true;
                    for (int nextStop : routes[line]) {
                        if (nextStop == e) return transfer;
                        if (!visitedStops.contains(nextStop)) {
                            visitedStops.add(nextStop);
                            Q.offer(nextStop);
                        }
                    }
                }
            }
            transfer++;
        }

        return -1;
    }

    public static void main(String[] args){
        MyAnswer048 T = new MyAnswer048();
        System.out.println(T.solution(new int[][]{{1, 2, 3, 4, 5, 6, 19}, {2, 7, 8, 13}, {5, 9, 10}, {9, 11, 12, 18}, {13, 14, 15}, {14, 12, 16, 17}}, 1, 12));
        System.out.println(T.solution(new int[][]{{1, 3, 5, 7}, {9, 3, 12}, {6, 5, 8}, {2, 8, 14, 15}, {2, 14, 16}}, 1, 14));
        System.out.println(T.solution(new int[][]{{7, 12},{5, 19},{7, 19},{9, 12, 13},{9, 5, 15}}, 9, 19));
        System.out.println(T.solution(new int[][]{{1, 2, 3, 4, 5},{9, 7, 10},{7, 6, 3, 8}, {5, 11, 8, 12}}, 1, 10));
    }
}
