package com.example.algorithmstudy.week6;

class Solution17 {
    public String solution17(String s) {
        return remove(solve(s));
    }

    private String solve(String s) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);

            // 현재 문자가 숫자면
            if (Character.isDigit(c)) {
                int repeat = 0;
                while (Character.isDigit(s.charAt(i))) {
                    // 반복 횟수 구하기
                    repeat = repeat * 10 + (s.charAt(i) - '0');
                    i++;
                }

                i++;
                int openBracket = 1;
                int start = i;

                // 닫는 괄호 찾기
                while (openBracket > 0) {
                    if (s.charAt(i) == '(') openBracket++;
                    if (s.charAt(i) == ')') openBracket--;
                    i++;
                }

                // 괄호 안의 문자열 뽑기
                String inner = solve(s.substring(start, i - 1));
                // 문자열 반복 횟수만큼 반복하여 결과 추가
                result.append(inner.repeat(repeat));
            } else {
                // 일반 문자는 그대로 추가
                result.append(c);
                i++;
            }
        }

        return result.toString();
    }

    private String remove(String s) {
        // 세번째가 3((cd))) 이따구로 되어있는데 솔직히 3(cd)여야 되는거 아니에요?
        // 얘만 괄호가 2개라서 이걸 안쓰면 ab(cd)(cd)(cd)ab(cd)(cd)(cd) 이렇게 나와요
        return s.replace("(", "").replace(")", "");
    }

    public static void main(String[] args) {
        Solution17 T = new Solution17();
        System.out.println(T.solution17("3(a2(b))ef"));
        System.out.println(T.solution17("2(ab)k3(bc)"));
        System.out.println(T.solution17("2(ab3((cd)))"));
        System.out.println(T.solution17("2(2(ab)3(2(ac)))"));
        System.out.println(T.solution17("3(ab2(sg))"));
    }
}
