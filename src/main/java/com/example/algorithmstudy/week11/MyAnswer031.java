package com.example.algorithmstudy.week11;
import java.util.*;

public class MyAnswer031 {
    public int solution(int n, int[] nums){
        int answer = 0;

        List<int[]> ranges = new ArrayList<>();

        //스프링클러 범위 변환
        for (int i = 0; i <= n; i++) {
            if (nums[i] > 0) {
                int left = Math.max(0, i - nums[i]);
                int right = Math.min(n, i + nums[i]);
                ranges.add(new int[]{left, right});
            }
        }
        ranges.sort(Comparator.comparingInt(a -> a[0]));

        int index = 0;
        int waterReached = 0;

        //끝까지 뿌릴때 까지 반복함
        while (waterReached < n) {
            int maxReach = waterReached;

            while (index < ranges.size() && ranges.get(index)[0] <= waterReached) {
                maxReach = Math.max(maxReach, ranges.get(index)[1]);
                index++;
            }
            answer++;

            if (maxReach == waterReached) return -1;

            waterReached = maxReach;
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer031 T = new MyAnswer031();
        System.out.println(T.solution(8, new int[]{1, 1, 1, 2, 1, 1, 2, 1, 1}));
        System.out.println(T.solution(4, new int[]{1, 2, 2, 0, 0}));
        System.out.println(T.solution(5, new int[]{2, 0, 0, 0, 0, 2}));
        System.out.println(T.solution(11, new int[]{1, 2, 3, 1, 2, 1, 1, 2, 1, 1, 1, 1}));
    }
}
