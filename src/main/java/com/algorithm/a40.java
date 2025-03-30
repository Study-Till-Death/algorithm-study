package com.algorithm;

public class a40 {
    int answer = 0;
    String s;
    int len;

    public int solution(String s){
        this.s = s;
        this.len = s.length();
        answer = 0;
        DFS(0);
        return answer;
    }

    public void DFS(int L) {
        if (L == len) { // 문자열 최종길이가 되었으면 방법수 증가 후 리턴
            answer++;
            return;
        }

        // 한 자리 수인 경우. 0은 해석 불가
        if (s.charAt(L) != '0') {
            DFS(L + 1);
        }

        // 두 자리 수인 경우.
        if (L + 1 < len) {
            String temp = s.substring(L, L + 2);
            int num = Integer.parseInt(temp);
            if (num >= 10 && num <= 26) {
                DFS(L + 2);
            }
        }
    }
    public static void main(String[] args){
        a40 T = new a40();
        System.out.println(T.solution("25114"));
        System.out.println(T.solution("23251232"));
        System.out.println(T.solution("21020132"));
        System.out.println(T.solution("21350"));
        System.out.println(T.solution("120225"));
        System.out.println(T.solution("232012521"));
    }
}
