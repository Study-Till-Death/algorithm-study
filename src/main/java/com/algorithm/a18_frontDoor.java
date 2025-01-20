package com.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class a18_frontDoor {
    public int[] solution(int[] arrival, int[] state){
        Queue<Integer> enter = new LinkedList<>();
        Queue<Integer> exit = new LinkedList<>();
        int n = arrival.length, prev = 1; // pre -> 1초전사용 저장 나감: 1, 들어옴: 0
        int[] answer = new int[n];

        for(int t = 0, i = 0, cnt = 0; ; t++){ // t: 시간, i: 사원번호, cnt: 문 사용횟수
            if(enter.isEmpty() && exit.isEmpty() && i < n) { // 현관문 사용하려는 사람 없고 배열은 남은 경우
                if(t < arrival[i]){ // 다음에 도착할 사원의 도착시간이 현재 시간보다 클 경우
                    t = arrival[i];
                    prev = 1;
                }
            }
            while(i < n && arrival[i] <= t) { // 다음에 도착할 사원의 도착시간이 현재 시간보다 작거나 같을 경우
                if (state[i] == 0) enter.offer(i);
                else exit.offer(i);
                i++;
            }
            if(prev == 1) { // 사용 안했거나 나가는 사원이 1초전 사용
                if(!exit.isEmpty()) { // 나가는 사원 없을 경우
                    answer[exit.poll()] = t;
                    prev = 1;
                }
                else{
                    answer[enter.poll()] = t;
                    prev = 0;
                }
            }else if(prev == 0) {
                if(!enter.isEmpty()) { //들어오는 사원 없을 경우
                    answer[enter.poll()] = t;
                    prev = 0;
                }else{
                    answer[exit.poll()] = t;
                    prev = 1;
                }
            }
            cnt++;
            if(cnt == n) break; // 현관문 사용횟수와 n이 같으면 멈춤
        }
        return answer;
    }

    public static void main(String[] args){
        a18_frontDoor T = new a18_frontDoor();
        System.out.println(Arrays.toString(T.solution(new int[]{0, 1, 1, 1, 2, 3, 8, 8}, new int[]{1, 0, 0, 1, 0, 0, 1, 0})));
        System.out.println(Arrays.toString(T.solution(new int[]{3, 3, 4, 5, 5, 5}, new int[]{1, 0, 1, 0, 1, 0})));
        System.out.println(Arrays.toString(T.solution(new int[]{2, 2, 2, 3, 4, 8, 8, 9, 10, 10}, new int[]{1, 0, 0, 0, 1, 1, 0, 1, 1, 0})));
    }
}
