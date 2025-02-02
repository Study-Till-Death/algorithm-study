package com.example.algorithmstudy.lessons020;

import java.util.*;
class Solution {
    public int[] solution(int[][] tasks){
        int[] answer = {};

        Queue<Task> taskQueue = new LinkedList<>();
        PriorityQueue<Task> readyQueue = new PriorityQueue<>();
        List<Integer> history = new ArrayList<>();

        //task 정보 큐에 넣기
        for(int i = 0; i < tasks.length; i++){
            taskQueue.add(new Task(i, tasks[i][0], tasks[i][1]));
        }

        //callTime 기준으로 정렬하기
        List<Task> list = new ArrayList<>(taskQueue);
        list.sort(Comparator.comparingInt(task -> task.callTime));
        taskQueue = new LinkedList<>(list);

        int t = 0;
        Task cpu = null;
        while(!taskQueue.isEmpty() || !readyQueue.isEmpty()){
            //callTime 충족하면 readyQueue에 넣기
            while(!taskQueue.isEmpty() && taskQueue.peek().callTime <= t){
                readyQueue.add(taskQueue.poll());
            }

            if(!readyQueue.isEmpty() && (cpu == null || cpu.endTime <= t)){
                Task task = readyQueue.poll();
                task.endTime = t + task.runTime;
                cpu = task;
                history.add(cpu.taskId);
            }

            t++;
        }

        answer = history.stream().mapToInt(i -> i).toArray();

        return answer;
    }

    static class Task implements Comparable<Task>{
        int taskId;
        int callTime;
        int runTime;
        int endTime;

        public Task(int taskId, int callTime, int runTime) {
            this.taskId = taskId;
            this.callTime = callTime;
            this.runTime = runTime;
        }

        @Override
        public int compareTo(Task o) {
            // 우선순위 runtime 비교
            int result = Integer.compare(this.runTime, o.runTime);
            if (result == 0) {
                // runTime이 같으면 taskId 비교
                result = Integer.compare(this.taskId, o.taskId);
            }
            
            return result;
        }
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(Arrays.toString(T.solution(new int[][]{{2, 3}, {1, 2}, {8, 2}, {3, 1}, {10, 2}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{5, 2}, {7, 3}, {1, 3}, {1, 5}, {2, 2}, {1, 1}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{1, 2}, {2, 3}, {1, 3}, {3, 3}, {8, 2}, {1, 5}, {2, 2}, {1, 1}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{999, 1000}, {996, 1000}, {998, 1000}, {999, 7}})));
    }
}
