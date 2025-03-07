package com.example.algorithmstudy.week12;
import java.util.*;

//뒤에서 부터 숫자 가져오면서 현재 숫자보다 앞의 숫자가 작아지는 타이밍이 있으면 둘을 바꾸고 남은 숫자중에 작은숫자부터 나열하면된다.
public class MyAnswer035 {
    public int solution(int n){
        char[] arr = String.valueOf(n).toCharArray();
        int length = arr.length;

        //뒤에서 부터 값이 작아지는 순간 찾기
        int swapIdx = -1;
        for (int i = length - 1; i > 0; i--) {
            if (arr[i - 1] < arr[i]) {
                swapIdx = i - 1;
                break;
            }
        }

        //값이 작아지는 순간이 없으면 idx=-1 이고 return -1
        if (swapIdx == -1) return -1;

        //swapIdx보다 큰 숫자 중 가장 작은 숫자 찾기
        int changeIdx = length - 1;
        while (arr[changeIdx] <= arr[swapIdx]) {
            changeIdx--;
        }

        //두 숫자 교환
        char temp = arr[swapIdx];
        arr[swapIdx] = arr[changeIdx];
        arr[changeIdx] = temp;

        //swapIdx 이후 숫자 정렬(작은 수 부터)
        Arrays.sort(arr, swapIdx + 1, length);

        return Integer.parseInt((new String(arr)));
    }

    public static void main(String[] args){
        MyAnswer035 T = new MyAnswer035();
        System.out.println(T.solution(123));
        System.out.println(T.solution(321));
        System.out.println(T.solution(20573));
        System.out.println(T.solution(27711));
        System.out.println(T.solution(54312));
    }
}
