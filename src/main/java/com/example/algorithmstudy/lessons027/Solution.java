package com.example.algorithmstudy.lessons027;

import java.util.*;
class Solution {
    public int solution(int[] tasks, long k) {
        int answer = 0;
        int len = tasks.length;
        int t = 0;

        //k초+1에 동작했어야할 친구를 찾는 중이니까 k+1까지
        for(int i = 0; t < k+1; i++) {
            //남은 작업이 있다면
            if(tasks[i%len] > 0) {
                tasks[i%len]--;
                t++;
                
                //index는 0부터 시작하니까 +1
                answer = i%len+1;
            }
        }

        //k=1이면, 0,2,3되고 1이 답이 되어야 한다.
        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{1, 2, 3}, 5));
        System.out.println(T.solution(new int[]{8, 5, 2, 9, 10, 7}, 30));
        System.out.println(T.solution(new int[]{8, 9, 12, 23, 45, 16, 25, 50}, 100));
    }
}
