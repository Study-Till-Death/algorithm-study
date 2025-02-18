package com.example.algorithmstudy.week10;
import java.util.*;

public class MyAnswer030 {
    public int solution(int[] nums){
        Map<Integer, Integer> countMap = new HashMap<>();

        //2,3,4,5갯수 가져 오기 위해 countMap에 forEach로 때려 박기
        Arrays.stream(nums)
                .forEach(num -> countMap.put(num, countMap.getOrDefault(num, 0) + 1));

        //2의 갯수
        int count2 = countMap.getOrDefault(2, 0);
        //3의 갯수
        int count3 = countMap.getOrDefault(3, 0);

        //2+3은 5니까 갯수 만큼은 정답에 더해짐
        int answer = Math.min(count2, count3);
        //남은 2
        int count2Remain = count2 - answer;

        //남은 3 (남은 2,3 둘중 하나는 0임)
        int count3Remain = count3 - answer;

        //2가 남을 경우에 2개 이상 이면 2개당 1이고 올림 으로 처리 해야함
        if (count2Remain >= 2) {
            count2Remain = (count2Remain + 1) / 2;
        }

        //다 더해
        return answer + count2Remain + count3Remain + countMap.getOrDefault(4, 0) + countMap.getOrDefault(5, 0);
    }

    public static void main(String[] args){
        MyAnswer030 T = new MyAnswer030();
        System.out.println(T.solution(new int[]{2, 5, 3, 4, 2, 3}));
        System.out.println(T.solution(new int[]{2, 3, 4, 5}));
        System.out.println(T.solution(new int[]{3, 3, 3, 3, 3}));
    }
}
