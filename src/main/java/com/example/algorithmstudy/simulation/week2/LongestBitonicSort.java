package com.example.algorithmstudy.simulation.week2;

import java.util.ArrayList;
import java.util.List;

public class LongestBitonicSort {
    public int solution(int[] nums) {
        // 꺾이는 index 만 찾으면 될 듯?
        List<Integer> macCutters = new ArrayList<>(); // 꺾이는 지점 + -> - || + -> + 인 지점의 index 리스트
        List<Integer> sameNumberIndexes = new ArrayList<>(); // 다음 index 와 같은 숫자인 index 리스트
        macCutters.add(0);
        for (int i = 1; i < nums.length - 1; i++) {
            // 처음과 끝은 앞뒤비교 필요 없으니 제외
            if (nums[i-1] == nums[i]){
                sameNumberIndexes.add(i-1);
            }
            if ((nums[i] - nums[i - 1]) * (nums[i + 1] - nums[i]) <= 0) {
                // 앞뒤로 증가 하락 방향이 다르면 -> 음수
                macCutters.add(i);
            }
        }
        macCutters.add(nums.length - 1);
        // 마지막 sameNumber 체크해서 추가해줌
        if (nums[nums.length-2] == nums[nums.length-1]){
            sameNumberIndexes.add(nums.length-2);
        }

        return getMaxLength(macCutters, sameNumberIndexes);
    }

    // 꺾이는 지점, 앞뒤로 같은 숫자인 지점 index 들 정리해서 리스트로 주면 바이오닉수열 최고길이 구해주기
    private static int getMaxLength(List<Integer> macCutters, List<Integer> sameNumberIndexes) {
        int maxLength = 0;
        for (int i = 0; i < macCutters.size() - 2; i++) {
            int endIndex = macCutters.get(i + 2);
            int startIndex = macCutters.get(i);

            // 그 꺾이는 부분들 사이에 같은 숫자가 연속으로 나오는 부분이 있다면 인정협회에서 인정해주지 않음
            boolean containSameNumber = false;
            for(int sameNumberIndex : sameNumberIndexes){
                if(!containSameNumber){
                    containSameNumber = (startIndex <= sameNumberIndex  && sameNumberIndex <= endIndex-1);
                }
            }
            if(!containSameNumber){
                maxLength = Math.max(maxLength, endIndex - startIndex + 1);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestBitonicSort T = new LongestBitonicSort();
        System.out.println(T.solution(new int[]{1, 2, 1, 2, 3, 2, 1}));
        System.out.println(T.solution(new int[]{1, 1, 2, 3, 5, 7, 4, 3, 1, 2}));
        System.out.println(T.solution(new int[]{3, 2, 1, 3, 2, 4, 6, 7, 3, 1}));
        System.out.println(T.solution(new int[]{1, 3, 1, 2, 1, 5, 3, 2, 1, 1}));
        System.out.println(T.solution(new int[]{1, 3, 1, 2, 2, 1, 5, 3, 3, 2, 1, 1})); // 중간에 같은 숫자 연속 예시
    }

}
