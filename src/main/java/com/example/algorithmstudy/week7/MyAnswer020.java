package com.example.algorithmstudy.week7;
import java.util.*;

public class MyAnswer020 {
    public int[] solution(int[][] tasks){
        //우선 순위 queue를 써야한다. (PriorityQueue)
        //작업 번호까지 추가를 해서 호출 시간으로 정렬해버린다.
        //PriorityQueue 에 시간이 됬을 때 값을 넣으면서 진행한다.
        //작업 갯수
        int n = tasks.length;
        int[] answer = new int[n];
        //cpu program (작업 번호 까지 추가)
        int[][] cpuProgram = new int[n][3];

        for (int i = 0; i < n; i++) {
            //호출 시간
            cpuProgram[i][0] = tasks[i][0];
            //작업 시간
            cpuProgram[i][1] = tasks[i][1];
            //작업 번호
            cpuProgram[i][2] = i;
        }
        //호출 시간 으로 정렬
        Arrays.sort(cpuProgram,Comparator.comparingInt(a -> a[0]));

        //PriorityQueue 에 값을 넣으면 정의된 순서로 자동 정렬을 진행 해준다.
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                //작업 시간 으로 정렬 후 같을 시 작업 번호 순으로 정렬
                (a, b) -> a[1] == b[1] ? Integer.compare(a[2], b[2]) : Integer.compare(a[1], b[1])
        );

        //현재 시간
        int currentTime = 0;
        //작업 인덱스
        int index = 0;
        //프로그램 순서
        int programIndex = 0;

        while (programIndex < n) {
            //현재 시간 이전 작업 모두 PriorityQueue 에 입력 (자동 정렬)
            while(index < n && cpuProgram[index][0] <= currentTime){
                pq.add(cpuProgram[index]);
                index++;
            }

            if(!pq.isEmpty()){
                //pq의 첫번째 값 빼내고 해당 값에 대한 시간 진행
                int[] currentCPU = pq.poll();
                //위에서 설장한 cpu program 작업 번호를 프로그램 순서 기준 으로 정답에 입력
                answer[programIndex] = currentCPU[2];
                //작업 진행 시키고 시간은 작업이 진행된 만큼의 시간 더하기
                currentTime += currentCPU[1];
                programIndex++;
            }else{
                //pq에 아무것도 값이 없으면 현재 index 기준 시간으로 시간 변경
                currentTime = cpuProgram[index][0];
            }
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer020 T = new MyAnswer020();
        System.out.println(Arrays.toString(T.solution(new int[][]{{2, 3}, {1, 2}, {8, 2}, {3, 1}, {10, 2}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{5, 2}, {7, 3}, {1, 3}, {1, 5}, {2, 2}, {1, 1}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{1, 2}, {2, 3}, {1, 3}, {3, 3}, {8, 2}, {1, 5}, {2, 2}, {1, 1}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{999, 1000}, {996, 1000}, {998, 1000}, {999, 7}})));
    }
}
