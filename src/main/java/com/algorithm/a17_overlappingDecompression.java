package com.algorithm;

import java.util.Stack;

public class a17_overlappingDecompression {
    // 이번 문제는 새벽에 한시간 반 풀다가 우울해서 강의 보고 풀었습니다
    public String solution(String s){
        String answer = "";
        Stack<String> st = new Stack<>();

        for(Character x : s.toCharArray()){
            if(x == ')'){
                String tmp = "";
                while(!st.empty()){ // stack이 비어있지 않으면 계속 반복
                    String c = st.pop();
                    if(c.equals("(")){ // 여는 괄호일 경우
                        String num = "";
                        while(!st.empty() && Character.isDigit(st.peek().charAt(0))){ // 비어있지 않고 숫자인 경우
                            num = st.pop() + num;
                        }
                        String res = "";
                        int cnt = 0;
                        if(num.equals("")) cnt = 1;
                        else cnt = Integer.parseInt(num);
                        for(int i = 0; i < cnt; i++) res += tmp; // res만큼 문자이어붙임
                        st.push(res);
                        break;
                    }
                    tmp = c + tmp;
                }
            }
            else st.push(String.valueOf(x)); // 닫는 괄호가 아니면 stack에 넣는다
        }
        for(String x : st) answer += x;
        return answer;
    }


    public static void main(String[] args){
        a17_overlappingDecompression T = new a17_overlappingDecompression();
        System.out.println(T.solution("3(a2(b))ef"));
        System.out.println(T.solution("2(ab)k3(bc)"));
        System.out.println(T.solution("2(ab3((cd)))"));
        System.out.println(T.solution("2(2(ab)3(2(ac)))"));
        System.out.println(T.solution("3(ab2(sg))"));
    }
}
