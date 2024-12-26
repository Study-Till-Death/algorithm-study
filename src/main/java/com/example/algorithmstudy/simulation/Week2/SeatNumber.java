package com.example.algorithmstudy.simulation.Week2;

import java.util.Arrays;

public class SeatNumber {
    public int[] solution(int c, int r, int k) {
        int[] answer = {1,1};
        //앞선 로봇청소기 로직 동일하게 만들면 될듯? 온자리 1로 바꾸고
        int[][] seats = new int[c][r];
        seats[0][0] = 1; // 처음 위치 1
        int direction = 0;
        //  위 오른 아래 왼
        int[] dc = {0, 1, 0, -1};
        int[] dr = {1, 0, -1, 0};
        int[] now = {0, 0}; // 시작 위치 c r
        if(k > c*r){ // 좌석수보다 k가 큰지만 체크하면 된다
            return now; // {0 0} 반환해야하므로 그냥 now 반환
        }
        for (int i = 0; i < k-1; i++) { // 두번째 손님부터 자리이동 이루어지므로 k-1 이동
            int nowC = now[0] + dc[direction];
            int nowR = now[1] + dr[direction];
            if (nowC > c-1 || nowC < 0 || nowR > r-1 || nowR < 0 || seats[nowC][nowR] == 1) {
                // 방향 돌고 새롭게 다음칸 지정
                direction = (direction+1)%4;
                nowC = now[0] + dc[direction];
                nowR = now[1] + dr[direction];
            }
            seats[nowC][nowR] = 1;
            now[0] = nowC;
            now[1] = nowR;
        }
        // 각각의 index 보다 +1 해주고 반환
        answer[0] = now[0]+1;
        answer[1] = now[1]+1;

        return answer;
    }


    public static void main(String[] args) {
        SeatNumber T = new SeatNumber();
        System.out.println(Arrays.toString(T.solution(6, 5, 12)));
        System.out.println(Arrays.toString(T.solution(6, 5, 20)));
        System.out.println(Arrays.toString(T.solution(6, 5, 30)));
        System.out.println(Arrays.toString(T.solution(6, 5, 31)));
    }
}
