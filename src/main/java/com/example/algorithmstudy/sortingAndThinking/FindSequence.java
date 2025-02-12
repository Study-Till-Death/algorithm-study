package com.example.algorithmstudy.sortingAndThinking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindSequence {
    public int[] solution(int[] nums){
        int[] answer = new int[nums.length / 2];
        // 하나씩 돌면서 x2 포함하고 있으면 오리지널 숫자로 추가
        List<Integer> originList = new ArrayList<>();
        boolean[] counted = new boolean[nums.length];
        for (int i = 0; i < nums.length; i = i + 1) {
            // 이미 카운트 한 애는 스킵
            if (counted[i]) continue;
            for (int j = 0; j < nums.length; j = j + 1) {
                // 두배인 애 찾으면 카운트 처리 후 원래값 배열에 추가
                // 원래값, 두배값 모두 카운트 처리하여 중복 방지
                if (counted[j]) continue;
                if (nums[j] == 2 * nums[i]){
                    originList.add(nums[i]);
                    counted[i] = true;
                    counted[j] = true;
                    break;
                }
            }
        }
        answer = originList.stream().mapToInt(i -> i).sorted().toArray();

        return answer;
    }

    public static void main(String[] args){
        FindSequence T = new FindSequence();
        System.out.println(Arrays.toString(T.solution(new int[]{1, 10, 2, 3, 5, 6})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 1, 6, 2, 2, 7, 3, 14})));
        System.out.println(Arrays.toString(T.solution(new int[]{14, 4, 2, 6, 3, 10, 10, 5, 5, 7, 7, 14})));
    }
}
