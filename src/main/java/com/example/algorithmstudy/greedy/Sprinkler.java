package com.example.algorithmstudy.greedy;

public class Sprinkler {
    public int solution(int n, int[] nums) {
        int answer = 0;
        // 일단 물 뿌리는데 앞부터 뿌리기
        // 근데 이 칸을 뿌릴 수 있는 칸이 뒤에 있다면 걔 우선 (자연적으로 고를 수 있는 애중 제일 큰 수의 스프링 쿨러가 선택될 거니까)
        // 그럼 뒤부터 세는 것이 더 빠를 듯?
        // 뿌린 뒤 현재 칸 전진시키고 다 칠할 때 까지 반복
        int now = 0;
        while (now < n) {
            boolean findSprinkler = false;
            for (int i = n; i >= now; i--) {
                if ( nums[i]!= 0 && i - nums[i] <= now) { // 0은 아예 안뿌리는 거임
                    answer ++;
                    now = i + nums[i];
                    findSprinkler = true;
                    break;
                }
            }
            if(!findSprinkler) {
                return -1;
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        Sprinkler T = new Sprinkler();
        System.out.println(T.solution(8, new int[]{1, 1, 1, 2, 1, 1, 2, 1, 1}));
        System.out.println(T.solution(4, new int[]{1, 2, 2, 0, 0}));
        System.out.println(T.solution(5, new int[]{2, 0, 0, 0, 0, 2}));
        System.out.println(T.solution(11, new int[]{1, 2, 3, 1, 2, 1, 1, 2, 1, 1, 1, 1}));
    }
}
