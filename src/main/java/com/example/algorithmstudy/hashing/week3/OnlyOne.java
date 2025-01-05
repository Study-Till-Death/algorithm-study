package com.example.algorithmstudy.hashing.week3;

import java.util.HashMap;

public class OnlyOne {
    public int solution(String s) {
        int answer = -1;
        // 알파벳 중복 체크
        boolean[] isDuplicate = new boolean[27];
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            boolean isUnique = true;
            // 이미 체크된 전적이 있다면 스킵
            if (isDuplicate[s.charAt(i) - 96]) {
                continue;
            }
            for (int j = i + 1; j < s.length(); j++) {
                // 해당 index 뒤로 쭉 자신과 다른지 체크
                if (current == s.charAt(j)) {
                    isUnique = false;
                    isDuplicate[s.charAt(i) - 96] = true;
                    break;
                }
            }
            if (isUnique) {
                answer = i + 1;
                break;
            }
        }
        return answer;
    }

    public int solution2(String s) {
        // solution 은 for 문 중첩되서 좀 느림 O(n²) => 답지 풀이 이렇게 안풀었을 거 같아서 구경감
        // 답지 풀이
        // 앞으로는  for문 중첩되면 다른 방법 생각좀 해보자
        int answer = -1;
        // 한 번만 쭉 돌아서 갯수 체크하고 1개이면서 가장 먼저 나온 것만 가져가기
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            countMap.put(s.charAt(i), countMap.getOrDefault(s.charAt(i) + 1, 0));
        }
        for (int i = 0; i < s.length(); i++) {
            if (countMap.getOrDefault(s.charAt(i), 0) == 1) {
                answer = i + 1;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        OnlyOne T = new OnlyOne();

        String testString = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";

        long start = System.nanoTime();
        System.out.println(T.solution(testString));
        long end = System.nanoTime();
        System.out.println("solution time: " + (end - start) + " ns");

        start = System.nanoTime();
        System.out.println(T.solution2(testString));
        end = System.nanoTime();
        System.out.println("solution2 time: " + (end - start) + " ns");
    }
}
