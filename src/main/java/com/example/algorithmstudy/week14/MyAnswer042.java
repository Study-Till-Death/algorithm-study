package com.example.algorithmstudy.week14;
import java.util.*;

//먼저 xa를 하는데 중간에 pool에 일치하는 숫자가 나오면 멈추고 y를 한번 뺌 (연속으로 두번은 못뺌)
//y를 뺀 값이 또 pool에 일치하면 return -1을하고 아니면 다시 또 진행하면서 home에 도착하면됨
public class MyAnswer042 {
    public int solution(int[] pool, int a, int b, int home){
        int answer = 0;
        int sum = 0;
        boolean twiceCheck = false;
        Set<Integer> poolCheck = new HashSet<>();
        for (int p : pool) {
            poolCheck.add(p);
        }

        while (sum != home){
            int finalSum = sum;
            if(poolCheck.contains(finalSum) || finalSum > home){
                if (!twiceCheck) {
                    sum -= b;
                    twiceCheck = true;
                } else {
                    //두번 뒤로 점프시
                    return -1;
                }
            } else {
                sum += a;
                twiceCheck = false;
            }
            answer++;
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer042 T = new MyAnswer042();
        System.out.println(T.solution(new int[]{11, 7, 20}, 3, 2, 10));
        System.out.println(T.solution(new int[]{1, 15, 11}, 3, 2, 5));
        System.out.println(T.solution(new int[]{9, 15, 35, 30, 20}, 2, 1, 25));
        System.out.println(T.solution(new int[]{5, 12, 7, 19, 23}, 3, 5, 18));
        System.out.println(T.solution(new int[]{10, 15, 20}, 3, 2, 2));
    }
}
