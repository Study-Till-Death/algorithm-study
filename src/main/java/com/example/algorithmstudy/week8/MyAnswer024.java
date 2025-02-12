package com.example.algorithmstudy.week8;
import java.util.*;

public class MyAnswer024 {
    public int solution(int[] nums, int k) {
        int answer = 0;

        //실제 시행횟수
        int round = nums.length/2;

        //넣으면 오름차순 정렬
        PriorityQueue<Integer> diffValue = new PriorityQueue<>(Collections.reverseOrder());

        //내림차순 정렬
        Arrays.sort(nums);

        //우선 철수는 작은숫자부터 정렬시 0,2,4..번째 값을 다 더한 값을 가지게 됨
        for(int i = 0; i< round; i++) {
            int idx = i*2;
            answer = answer + nums[idx];
        }

        //값의 차이를 구하고 오름차순 정렬되는 PQ에 넣음
        for (int i = 0; i < round; i++) {
            int idx = nums.length - 1 - (2 * i);
            diffValue.add(nums[idx] - nums[idx-1]);
        }

        //k횟수 많큼 값의 차이를 answer에 추가적으로 더해줌
        //k에 대한 제한사항이 존재하므로 따로 추가적인 k횟수에 대한 검증 수행은 필요 없음
        for (int i = 0; i < k; i++){
            answer += diffValue.poll();
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer024 T = new MyAnswer024();
        System.out.println(T.solution(new int[]{7, 8, 5, 12, 3, 1, 3, 1, 1, 12}, 2));
        System.out.println(T.solution(new int[]{8, 2, 12, 12, 12, 12, 2, 2}, 2));
        System.out.println(T.solution(new int[]{3, 7, 12, 3, 3, 5, 7, 8, 9, 11, 23, 4, 6, 7}, 3));
        System.out.println(T.solution(new int[]{12, 34, 56, 23, 22, 34, 55, 45, 24, 23, 45, 55, 55, 23, 11, 12, 23, 12}, 3));
        System.out.println(T.solution(new int[]{14, 15, 20, 11, 10, 20, 20, 12, 9, 22, 27, 25, 30, 19}, 3));
    }
}
