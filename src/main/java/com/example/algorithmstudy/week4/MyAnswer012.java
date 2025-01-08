package com.example.algorithmstudy.week4;
import java.util.*;

//음수가 있는 부분수열
public class MyAnswer012 {
    public int solution(int[] nums, int m){
        int answer = 0;

        //다 더해가면서 m 나오면 answer를 1 더한다. 뒤에 숫자를 더 더 했을 때 또 m이 나올 수 있으니 정지는 없다.
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for (int j = i; j < nums.length; j++){
                sum += nums[j];
                if (sum == m) {
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer012 T = new MyAnswer012();
        System.out.println(T.solution(new int[]{2, 2, 3, -1, -1, -1, 3, 1, 1}, 5));
        System.out.println(T.solution(new int[]{1, 2, 3, -3, 1, 2, 2, -3}, 5));
        System.out.println(T.solution(new int[]{1, 2, 3, -3, 1, 2}, 3));
        System.out.println(T.solution(new int[]{-1, 0, 1}, 0));
        System.out.println(T.solution(new int[]{-1, -1, -1, 1}, 0));
    }
}
