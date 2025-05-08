package com.example.algorithmstudy.week19;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//백준 1994번 등차수열
public class MyAnswer056 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCount = Integer.parseInt(br.readLine());
        int[] numArr = new int[numCount];

        for (int i = 0; i < numCount; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(numArr);

        int maxCount = 1;

        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();

        for (int i = 0; i < numCount; i++) {
            dp.put(i, new HashMap<>());
            for (int j = 0; j < i; j++) {
                int diff = numArr[i] - numArr[j];
                int len = dp.get(j).getOrDefault(diff, 1) + 1;
                dp.get(i).put(diff, len);
                maxCount = Math.max(maxCount, len);
            }
        }

        System.out.println(maxCount);
    }
}
