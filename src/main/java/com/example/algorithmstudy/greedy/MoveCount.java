package com.example.algorithmstudy.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class MoveCount {
    public int solution(int[] nums){
        int answer = 0;
        // 타이타닉이잖슴
        // 이번에는 최대가 최소 가져가는 식으로
        // 아 어차피 비슷하네 결국 최대가 최소도 못가져가면 혼자가는 거고
        // 그 다음애도 마찬가지 아 그러네
        // 타이타닉 답지 본 기억으로 따라해보기
        nums = Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) { // 같아야 마지막 1개 남아도 ㄱㅊ 어차피 answer 는 1개씩만 오르니 그 때 가서는 left, righr 이동은 안중요해짐
            if (nums[left] + nums[right] <= 5) { // 2명 가능?
                left++;
                right--;
            }else { // 안되면 예만
                left++;
            }
            answer++; // answer 는 1개씩만
        }

        return answer;
    }

    public static void main(String[] args){
        MoveCount T = new MoveCount();
        System.out.println(T.solution(new int[]{2, 5, 3, 4, 2, 3}));
        System.out.println(T.solution(new int[]{2, 3, 4, 5}));
        System.out.println(T.solution(new int[]{3, 3, 3, 3, 3}));
        System.out.println(T.solution(new int[]{2, 2, 2, 2, 2}));
    }
}
