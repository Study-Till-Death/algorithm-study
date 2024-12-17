package com.example.algorithmstudy.week2;

public class MyAnswer005 {
    public int solution(int[] nums){
        int answer = 1;
        int currentLength = 1;

        boolean decrease = false;

        for (int i = 0; i < nums.length-1; i++) {
            if(nums[i] < nums[i+1]){
                if(decrease){
                    answer = Math.max(answer, currentLength);
                    currentLength = 1;
                    decrease = false;
                }
                currentLength++;
                answer = Math.max(answer, currentLength);
            }else if(nums[i] > nums[i+1]){
                currentLength++;
                answer = Math.max(answer, currentLength);
                decrease = true;
            }else if(nums[i] == nums[i+1]){
                answer = Math.max(answer, currentLength);
                currentLength = 1;
                decrease = false;
            }
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer005 T = new MyAnswer005();
        System.out.println(T.solution(new int[]{1, 2, 1, 2, 3, 2, 1}));
        System.out.println(T.solution(new int[]{1, 1, 2, 3, 5, 7, 4, 3, 1, 2}));
        System.out.println(T.solution(new int[]{3, 2, 1, 3, 2, 4, 6, 7, 3, 1}));
        System.out.println(T.solution(new int[]{1, 3, 1, 2, 1, 5, 3, 2, 1, 1}));
    }
}
