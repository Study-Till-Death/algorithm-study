package com.algorithm;

public class a12_subsequence {
    public int solution(int[] nums, int m){
        int answer = 0;
        int temp = 2; // 합할 개수
        int sum;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==m) answer++; // 단일 숫자가 m과 같은 경우
            for (int j = 0; j <= nums.length-temp; j++) { // 1.인덱스 한 칸씩 이동하며
                sum=0;
                for (int k = j; k < j+temp; k++) { // 2.temp만큼 반복해서 합
                    sum+=nums[k];
                }
                if (sum==m) answer++; // 합이 m과 같으면 answer++
            }
            temp++; // 합할 숫자 증가
        }

        return answer;
    }

    public static void main(String[] args){
        a12_subsequence T = new a12_subsequence();
        System.out.println(T.solution(new int[]{2, 2, 3, -1, -1, -1, 3, 1, 1}, 5));
        System.out.println(T.solution(new int[]{1, 2, 3, -3, 1, 2, 2, -3}, 5));
        System.out.println(T.solution(new int[]{1, 2, 3, -3, 1, 2}, 3));
        System.out.println(T.solution(new int[]{-1, 0, 1}, 0));
        System.out.println(T.solution(new int[]{-1, -1, -1, 1}, 0));
    }
}
