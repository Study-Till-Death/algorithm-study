package com.example.algorithmstudy.week8;
import java.util.*;

public class MyAnswer023 {
    public int[] solution(int[] nums){
        int[] answer = new int[nums.length / 2];

        //정답 리스트
        ArrayList<Integer> answerList = new ArrayList<>();
        //짝수는 한번더 걸러야함
        ArrayList<Integer> evenList = new ArrayList<>();

        for(int i = 0; i < nums.length; i++){
            //홀수는 그냥 무조건 정답
            if(nums[i]%2 == 1) answerList.add(nums[i]);
            //짝수 처리
            if(nums[i]%2 == 0) evenList.add(nums[i]);
        }

        for(int j = 0; j < evenList.size(); j++){
            //짝수중에 나눴을때 해당값이 짝수 리스트에 존재하는 값만 /2해서 더함
            if(evenList.contains(evenList.get(j)/2)){
                answerList.add(evenList.get(j)/2);
            }
        }

        //정렬 후 변환해서 return
        return answerList.stream().sorted().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args){
        MyAnswer023 T = new MyAnswer023();
        System.out.println(Arrays.toString(T.solution(new int[]{1, 10, 2, 3, 5, 6})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 1, 6, 2, 2, 7, 3, 14})));
        System.out.println(Arrays.toString(T.solution(new int[]{14, 4, 2, 6, 3, 10, 10, 5, 5, 7, 7, 14})));
    }
}
