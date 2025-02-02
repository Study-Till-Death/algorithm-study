package com.example.algorithmstudy.datastructure.week7;

import java.util.*;
import java.util.stream.Collectors;

public class Cpu {
    public int[] solution(int[][] tasks) {
        int[] answer = new int[tasks.length];
        //우선순위큐로 만든 대기열 workTime 순으로 정렬 같아면 작업번호순
        PriorityQueue<Task> waitingQueue = new PriorityQueue<>(
                Comparator.comparingInt((Task a) -> a.workTime)
                        .thenComparingInt(a -> a.no)
        );
        // 시작시간, workTime 순으로 정렬한 taskQueue
        PriorityQueue<Task> taskQueue = new PriorityQueue<>(
                Comparator.comparingInt((Task a) -> a.startTime)
                        .thenComparingInt(a -> a.workTime)
        );

        for (int i = 0; i < tasks.length; i++) {
            taskQueue.add(new Task(i, tasks[i][0], tasks[i][1]));
        }
        int nowEndTime = 0;
        int nowIndex = 0;
        while (!taskQueue.isEmpty() || !waitingQueue.isEmpty()) {
            // 현재 작업중인 작업이 완료되기 전 혹은 완료되는 시점의 일은 전부 waitingQueue 에 추가 (자동으로 우선순위 설정해주기 위함)
            if (!taskQueue.isEmpty() && taskQueue.peek().startTime <= nowEndTime) {
                waitingQueue.add(taskQueue.poll());
            } else { // 완료시간 이후 일이 왔을 경우
                if (!waitingQueue.isEmpty()) { // 대기열 에서 제일 우선순위로 제거
                    Task task = waitingQueue.poll();
                    nowEndTime += task.workTime;
                    answer[nowIndex++] = task.no;
                } else { // 대기열 비었으면 바로 처리
                    Task task = taskQueue.poll();
                    nowEndTime = task.startTime + task.workTime;
                    answer[nowIndex++] = task.no;
                }
            }
        }

        return answer;
    }

    private record Task(int no, int startTime, int workTime) {
    }

    public static void main(String[] args) {
        Cpu T = new Cpu();
        System.out.println(Arrays.toString(T.solution(new int[][]{{2, 3}, {1, 2}, {8, 2}, {3, 1}, {10, 2}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{5, 2}, {7, 3}, {1, 3}, {1, 5}, {2, 2}, {1, 1}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{1, 2}, {2, 3}, {1, 3}, {3, 3}, {8, 2}, {1, 5}, {2, 2}, {1, 1}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{999, 1000}, {996, 1000}, {998, 1000}, {999, 7}})));
    }
}
