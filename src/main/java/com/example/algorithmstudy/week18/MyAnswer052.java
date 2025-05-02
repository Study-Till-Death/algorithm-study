package com.example.algorithmstudy.week18;
import java.util.*;

public class MyAnswer052 {
    public String[] solution(String[] subjects, String[] course){
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> order = new HashMap<>();

        for (String subject : subjects) {
            graph.put(subject, new ArrayList<>());
            order.put(subject, 0);
        }

        for (String relation : course) {
            String[] parts = relation.split(" ");
            String target = parts[0];
            String prerequisite = parts[1];

            graph.get(prerequisite).add(target);
            order.put(target, order.get(target) + 1);
        }

        Queue<String> queue = new LinkedList<>();
        for (String subject : order.keySet()) {
            if (order.get(subject) == 0) {
                queue.offer(subject);
            }
        }

        List<String> courseOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            String current = queue.poll();
            courseOrder.add(current);

            for (String next : graph.get(current)) {
                order.put(next, order.get(next) - 1);
                if (order.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }

        return courseOrder.toArray(new String[0]);
    }

    public static void main(String[] args){
        MyAnswer052 T = new MyAnswer052();
        System.out.println(Arrays.toString(T.solution(new String[]{"english", "math", "physics", "art", "music"}, new String[]{"art math", "physics art", "art music", "physics math", "english physics"})));
        System.out.println(Arrays.toString(T.solution(new String[]{"art", "economics", "history", "chemistry"}, new String[]{"chemistry history", "economics history", "art economics"})));
        System.out.println(Arrays.toString(T.solution(new String[]{"math", "science", "music", "biology"}, new String[]{"science music", "math music", "math science", "biology math"})));
    }
}
