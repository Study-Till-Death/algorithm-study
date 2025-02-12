package com.example.algorithmstudy.week8;
import java.util.*;

public class MyAnswer022 {
    public Object[] solution(int[] nums){
        return Arrays.stream(nums)
                .boxed()
                .sorted(Comparator
                        .comparingInt(MyAnswer022::countingNumberOne) //1의 개수 기준으로 정렬
                        .thenComparingInt(n -> n)) //숫자 크기 정렬
                .toArray(); //배열로 반환
    }

    /**
     * 이진법 변환 후 1의 갯수 카운팅 하는 함수
     * @param number 숫자
     * @return 이진법 변환 후 1의 갯수 리턴
     */
    private static int countingNumberOne(Integer number) {
        int count = 0;
        String demical = Integer.toBinaryString(number);
        for (char c : demical.toCharArray()) {
            if (c == '1') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        MyAnswer022 T = new MyAnswer022();
        System.out.println(Arrays.toString(T.solution(new int[]{5, 6, 7, 8, 9})));
        System.out.println(Arrays.toString(T.solution(new int[]{5, 4, 3, 2, 1})));
        System.out.println(Arrays.toString(T.solution(new int[]{12, 5, 7, 23, 45, 21, 17})));
    }
}
