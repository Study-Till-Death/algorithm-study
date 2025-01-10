package com.algorithm;

import java.util.Arrays;

class a04_sitnumber {
    public int[] solution(int c, int r, int k) {
        int[] answer = {1, 1}; // 시작 x,y
        int[] px = {0, 1, 0, -1}; // 한 바퀴 x증감폭
        int[] py = {1, 0, -1, 0}; // 한 바퀴 y증감폭
        int index = 0; // x,y증감폭의 index
        int cornerHistory = 1; //코너를 돈 횟수
        int cornerNum = r - 1; //코너 돌 때까지 이동해야하는 횟수
        int cnt = 0; // 한 코너를 돌 때까지 이동횟수

        for (int i = 1; i < k; i++) {
            if (cornerNum == 0) return new int[]{0, 0}; // 코너까지의 이동해야하는 횟수가 0인데(자리없음) 사람이 더 존재해서 반복을 돌았을 경우
            answer[0] += px[index]; //이동
            answer[1] += py[index]; //이동
            cnt++; // 한 코너를 돌 때까지 이동 횟수

            if (cnt == cornerNum) { //실제이동횟수와 필요한이동횟수비교
                cornerHistory++; //코너 돈 횟수
                if (px[index] != 0) cornerNum = r - cornerHistory / 2; // 코너 돌기까지 필요한 이동거리 갱신
                else cornerNum = c - cornerHistory / 2;
                index = (index + 1) % 4; // 코너 돌았으므로 x,y 증감 index 갱신
                cnt = 0; // 한 코너를 돌 때까지 이동 횟수 초기화
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        a04_sitnumber T = new a04_sitnumber();
        System.out.println(Arrays.toString(T.solution(6, 5, 12)));
        System.out.println(Arrays.toString(T.solution(6, 5, 20)));
        System.out.println(Arrays.toString(T.solution(6, 5, 30)));
        System.out.println(Arrays.toString(T.solution(6, 5, 31)));
    }
}