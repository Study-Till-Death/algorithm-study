package com.example.algorithmstudy.sortingAndThinking;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class MultiTasking {
    public int solution(int[] tasks, long k) {
        int answer = 0;
        // 일단 그냥 for 문 돌면 되긴 하는데 좀 아쉽다는 거죠
        // 유지보수ti개발매년팀갈엘프재혁님이 안된다 했으니까 그냥 for 문은 패스

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.stream(tasks).forEach(pq::add);

        //다풀고나니까 k번째 작업 숫자 구하게 해놔서 귀찮으니 1 더함
        k++;

        if (k > Arrays.stream(tasks).sum()){ // 대춛 너무 크면 -1
            return -1;
        }

        int count = 0;
        while(k > pq.size() * (pq.peek()-count)){ // 가장 작은 숫자의 작업을 끝낼만큼 충분한지 체크
            // 작업 중
            int peek = pq.peek();
            k -= pq.size()* (peek-count);
            count = pq.poll();
            tasks = Arrays.stream(tasks).map(i ->  (i == peek) ? -1 : i).toArray();

            for (int i=0; i<tasks.length-1; i++){ // 마지막 작업 종료지점 체크
                if (tasks[tasks.length-1 - i] != -1){
                    answer = tasks.length - i;
                    break;
                }
            }

        }

        // pq 사이즈로 나눠서 몇번째 일인지 체크
        if (k == pq.size()){
            return answer;
        }
        int findOrder = (int) (k%pq.size()); // 몇번 째 순서인지
        count = 0;
        for (int i=0; i<tasks.length; i++){
            if (tasks[i] != -1){
                count++;
                if (count == findOrder){
                    answer = i+1;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args){
        MultiTasking T = new MultiTasking();
        System.out.println(T.solution(new int[]{1, 2, 3}, 5));
        System.out.println(T.solution(new int[]{8, 5, 2, 9, 10, 7}, 30));
        System.out.println(T.solution(new int[]{8, 9, 12, 23, 45, 16, 25, 50}, 100));
    }
}
