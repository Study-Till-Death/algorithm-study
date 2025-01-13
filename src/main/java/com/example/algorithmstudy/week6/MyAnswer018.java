package com.example.algorithmstudy.week6;
import java.util.*;

class MyAnswer018 {
    public int[] solution(int[] arrival, int[] state){
        int n = arrival.length;
        int[] answer = new int[n];

        //두 개의 큐로 문제를 풀어야 한다.
        Queue<Integer> enter = new LinkedList<>(); //입장 리스트
        Queue<Integer> exit = new LinkedList<>(); //퇴장 리스트

        boolean moveIn = false; //기본 값은 나가는 게 우선 / true : 입장 / false : 퇴장
        int time = 0; //시간
        int managerCount = 0; //처리된 사원 카운트
        int index = 0; //처리 중인 인덱스

        while (managerCount < n) {
            //시간 이전의 값을 가지는 모든 사원 입장, 퇴장 사원 Queue에 추가
            while (index < n && arrival[index] <= time) {
                if (state[index] == 0) {
                    enter.add(index);
                } else {
                    exit.add(index);
                }
                index++;
            }

            //입장, 퇴장 사원이 없는 상태일 경우 시간 점프
            if (enter.isEmpty() && exit.isEmpty() && index < n) {
                //시간 Skip
                time = arrival[index];
                moveIn = false; //기본 값으로 초기화
                continue;
            }

            //이전 작업이 퇴장일 경우 처리
            if (!moveIn) {
                if (!exit.isEmpty()) {
                    answer[exit.poll()] = time;
                } else {
                    answer[enter.poll()] = time;
                    moveIn = true;
                }
            } else {
                //이전 작업이 입장일 경우 처리
                if (!enter.isEmpty()) {
                    answer[enter.poll()] = time;
                } else {
                    answer[exit.poll()] = time;
                    moveIn = false;
                }
            }

            managerCount++;
            time++;
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer018 T = new MyAnswer018();
        System.out.println(Arrays.toString(T.solution(new int[]{0, 1, 1, 1, 2, 3, 8, 8}, new int[]{1, 0, 0, 1, 0, 0, 1, 0})));
        System.out.println(Arrays.toString(T.solution(new int[]{3, 3, 4, 5, 5, 5}, new int[]{1, 0, 1, 0, 1, 0})));
        System.out.println(Arrays.toString(T.solution(new int[]{2, 2, 2, 3, 4, 8, 8, 9, 10, 10}, new int[]{1, 0, 0, 0, 1, 1, 0, 1, 1, 0})));
    }
}