package com.example.algorithmstudy.datastructure.week7;

import java.util.*;
import java.util.stream.Collectors;

public class Skin {
    public int solution(int[] laser, String[] enter) {
        int answer = 0;
        int nowEndTime = 0;

        // 이하 시간은 전부 분단위로 변환하여 사용
        // hh:mm -> hhmm 으로 바꾸어 큐로 저장
        Queue<Customer> enterQueue = Arrays.stream(enter).map(s -> {
            String[] split = s.split(" ");
            String[] hhmm = split[0].split(":");
            int time = Integer.parseInt(hhmm[0]) * 60 + Integer.parseInt(hhmm[1]);
            return new Customer(time, Integer.parseInt(split[1]));
        }).collect(Collectors.toCollection(LinkedList::new));

        Queue<Customer> waitingQueue = new LinkedList<>();

        while (!enterQueue.isEmpty() || !waitingQueue.isEmpty()) {
            if (!enterQueue.isEmpty() && enterQueue.peek().enterTime < nowEndTime) { // 바로 수술 불가시 대기열 추가
                waitingQueue.add(enterQueue.poll());
            } else { // 수술 가능 (즉 전 수술이 끝난 다음에 손님이 왔다면) 대기열 빼거나 바로 수술 진행
                if (!waitingQueue.isEmpty()) { // 대기열 있으면 대기열 우선
                    Customer customer = waitingQueue.poll();
                    nowEndTime += laser[customer.type]; // 새 수술 종료시간 설정 (전 수술 시간 + 진행)
                } else { // 대기열 없으면 바로 수술
                    Customer customer = enterQueue.poll();
                    nowEndTime = customer.enterTime + laser[customer.type]; // 새 수술 종료시간 설정 (전 수술 시간 + 진행)
                }
            }
            answer = Math.max(answer, waitingQueue.size());
        }
        return answer;
    }

    private record Customer(
            int enterTime,
            int type
    ) {
    }

    public static void main(String[] args) {
        Skin T = new Skin();
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "11:10 2"}));
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "15:10 0", "15:20 3", "15:22 1", "15:23 0", "15:25 0"}));
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:20 1", "10:40 1", "11:00 1", "11:20 1", "11:40 1"}));
    }
}
