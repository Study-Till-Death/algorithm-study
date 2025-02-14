package com.example.algorithmstudy.sortingAndThinking;

public class MultiTasking {
    public int solution(int[] tasks, long k) {
        int answer = 0;
        // 일단 그냥 for 문 돌면 되긴 하는데 좀 아쉽다는 거죠
        // 유지보수ti개발매년팀갈엘프재혁님이 안된다 했으니까 그냥 for 문은 패스




        return answer;
    }

    public static void main(String[] args){
        MultiTasking T = new MultiTasking();
        System.out.println(T.solution(new int[]{1, 2, 3}, 5));
        System.out.println(T.solution(new int[]{8, 5, 2, 9, 10, 7}, 30));
        System.out.println(T.solution(new int[]{8, 9, 12, 23, 45, 16, 25, 50}, 100));
    }
}
