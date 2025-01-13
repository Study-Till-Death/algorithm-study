package com.example.algorithmstudy.datastructure.week6;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Gate {
    public int[] solution(int[] arrival, int[] state){
        int[] answer = new int[arrival.length];

        // 대충 매 초마다 for 문 돌면서 체크
        // 맨 처음에는 나가는 사람 우선
        // 이후 전 사람 따라서 ex) 나가면 뒤에 줄 선 사람 다 나가고 , 들어오면 다 들어오고 우루루
        // queue 2개 만들고 방향만 값 지정해서 주르륵 넣어주면 될 거 같은데
        Queue<Integer> inQueue = new LinkedList<>(); // 입장대기열
        Queue<Integer> outQueue = new LinkedList<>(); // 퇴장대기열

        int arriveIndex = 0;
        int time =0;
        String direction = "out";
        // 대기열 다 비어있고 모든 사람들이 다 도착한 게 확인 -> 종료
        while (!inQueue.isEmpty() || !outQueue.isEmpty() || arriveIndex < arrival.length){

            // queue 도착자들 집어 넣기
            while(arriveIndex < arrival.length && arrival[arriveIndex] == time){
                if (state[arriveIndex] == 0){
                    inQueue.add(arriveIndex);
                }else{
                    outQueue.add(arriveIndex);
                }
                arriveIndex++;
            }

            // 나갈 사람 없으면 방향 바꿔줄 거임
            if (direction.equals("in") && inQueue.isEmpty()){
                direction = "out";
            } else if (direction.equals("out") && outQueue.isEmpty()){
                direction = "in";
            }

            // 자 나가자 ~
            if (direction.equals("in") && inQueue.peek() != null){
                answer[inQueue.poll()] = time;
            }else if(direction.equals("out") && outQueue.peek() != null){
                answer[outQueue.poll()] = time;
            }else{ // 아무도 안나감
                direction = "out";
            }
            time ++;
        }

        return answer;
    }

    public static void main(String[] args){
        Gate T = new Gate();
        System.out.println(Arrays.toString(T.solution(new int[]{0, 1, 1, 1, 2, 3, 8, 8}, new int[]{1, 0, 0, 1, 0, 0, 1, 0})));
        System.out.println(Arrays.toString(T.solution(new int[]{3, 3, 4, 5, 5, 5}, new int[]{1, 0, 1, 0, 1, 0})));
        System.out.println(Arrays.toString(T.solution(new int[]{2, 2, 2, 3, 4, 8, 8, 9, 10, 10}, new int[]{1, 0, 0, 0, 1, 1, 0, 1, 1, 0})));
    }
}
