package com.example.algorithmstudy.week6;
import java.util.*;

class MyAnswer017 {
    public String solution(String s){
        // 문자열 Stack
        Stack<StringBuilder> stringStack = new Stack<>();
        // 반복 횟수 Stack
        Stack<Integer> repeatStack = new Stack<>();
        // 반복 해야 하는 현재 문자열
        StringBuilder currentString = new StringBuilder();
        int currentNum = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                currentNum = currentNum * 10 + (c - '0');
            } else if (c == '(') {
                stringStack.push(currentString);
                if(currentNum != 0){
                    repeatStack.push(currentNum);
                }else {
                    //괄호 중복 처리
                    repeatStack.push(1);
                }
                currentString = new StringBuilder();
                currentNum = 0;
            } else if (c == ')') {
                // 반복 해야 하는 문자열 temp 로 이동
                StringBuilder temp = currentString;
                // 기존 문자열 Stack에서 pop 해서 가져옴
                currentString = stringStack.pop();
                // 반복 횟수 Stack에서 가져옴
                int repeatCount = repeatStack.pop();
                for (int i = 0; i < repeatCount; i++) {
                    currentString.append(temp);
                }
            } else {
                currentString.append(c);
            }
        }

        return currentString.toString();
    }

    public static void main(String[] args){
        MyAnswer017 T = new MyAnswer017();
        System.out.println(T.solution("3(a2(b))ef"));
        System.out.println(T.solution("2(ab)k3(bc)"));
        System.out.println(T.solution("2(ab3((cd)))"));
        System.out.println(T.solution("2(2(ab)3(2(ac)))"));
        System.out.println(T.solution("3(ab2(sg))"));
    }
}