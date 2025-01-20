package com.algorithm;

import java.util.Stack;

public class a17_overlapping_junginver {
    //쓰레기도 함께 동봉해올립니다.
    public String solution(String s) {
        Stack<Integer> counts = new Stack<>();  // 반복 횟수를 저장하는 스택
        Stack<StringBuilder> strings = new Stack<>();  // 문자열을 저장하는 스택
        StringBuilder currentString = new StringBuilder();  // 현재 문자열

        int repeatCount = 0;  // 반복 횟수

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // 숫자일 경우, 반복 횟수를 계산 (여러 자리 숫자도 처리)
                repeatCount = repeatCount * 10 + (c - '0');
            } else if (c == '(') {
                // (가 나오면 현재 문자열을 스택에 저장하고 반복 횟수 저장
                counts.push(repeatCount);
                strings.push(currentString);
                repeatCount = 0;  // 반복 횟수 초기화
                currentString = new StringBuilder();  // 괄호 내 문자열 초기화
            } else if (c == ')') {
                // )가 나오면 스택에서 반복 횟수와 이전 문자열을 꺼내어 처리
                StringBuilder temp = currentString;
                currentString = strings.pop();  // 이전 문자열
                int count = counts.pop();  // 반복 횟수

                // 괄호 내 문자열을 반복 횟수만큼 반복하여 붙임
                for (int j = 0; j < count; j++) {
                    currentString.append(temp);
                }
            } else {
                // 일반 문자는 그대로 currentString에 추가
                currentString.append(c);
            }
        }

        return currentString.toString();
    }

    public static void main(String[] args) {
        a17_overlapping_junginver T = new a17_overlapping_junginver();
        System.out.println(T.solution("3(a2(b))ef"));
        System.out.println(T.solution("2(ab)k3(bc)"));
        System.out.println(T.solution("2(ab3((cd)))"));
        System.out.println(T.solution("2(2(ab)3(2(ac)))"));
        System.out.println(T.solution("3(ab2(sg))"));
    }
}
