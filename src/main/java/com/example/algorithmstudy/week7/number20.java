package com.example.algorithmstudy.week7;

import java.util.*;

class Solution20 {
    public int[] solution20(int[][] tasks) {
        // 큐 모르겠어서 강의 듣고 풀어야되는데 제가 이번주까지 쉬는줄 알고 강의를 못봐서 친구랑 같이 했어요 한 8:2 비율
        List<int[]> taskList = new ArrayList<>();
        for (int i = 0; i < tasks.length; i++) {
            taskList.add(new int[]{tasks[i][0], tasks[i][1], i}); // {호출시간, 실행시간, 작업번호}
        }

        // 호출 시간 기준 정렬
        Collections.sort(taskList, (a, b) -> Integer.compare(a[0], b[0]));

        // 실행시간, 작업번호 기준 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                a[1] == b[1] ? Integer.compare(a[2], b[2]) : Integer.compare(a[1], b[1])
        );

        List<Integer> order = new ArrayList<>();
        int currentTime = 0, index = 0;

        while (index < taskList.size() || !pq.isEmpty()) {
            // 현재 시간까지 실행 가능한 작업들을 대기 큐에 추가
            while (index < taskList.size() && taskList.get(index)[0] <= currentTime) {
                pq.offer(taskList.get(index));
                index++;
            }

            if (pq.isEmpty()) {
                // 실행 가능한 작업이 없으면 다음 작업의 호출 시간으로 점프
                currentTime = taskList.get(index)[0];
            } else {
                int[] task = pq.poll();
                order.add(task[2]); // 실행된 작업 번호 저장
                currentTime += task[1]; // 작업 실행 시간 추가

                // 현재 시간과 다음 호출 시간을 비교하여 5초 대기
                if (index < taskList.size() && currentTime + 5 < taskList.get(index)[0]) {
                    currentTime += 5;
                }
            }
        }

        return order.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Solution20 sol = new Solution20();
        System.out.println(Arrays.toString(sol.solution20(new int[][]{{2, 3}, {1, 2}, {8, 2}, {3, 1}, {10, 2}})));
        System.out.println(Arrays.toString(sol.solution20(new int[][]{{5, 2}, {7, 3}, {1, 3}, {1, 5}, {2, 2}, {1, 1}})));
        System.out.println(Arrays.toString(sol.solution20(new int[][]{{1, 2}, {2, 3}, {1, 3}, {3, 3}, {8, 2}, {1, 5}, {2, 2}, {1, 1}})));
        System.out.println(Arrays.toString(sol.solution20(new int[][]{{999, 1000}, {996, 1000}, {998, 1000}, {999, 7}})));
    }
}
