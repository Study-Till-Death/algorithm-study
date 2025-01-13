package com.example.algorithmstudy.datastructure.week6;

public class Unzip {
    public String solution(String s) {
        return unzip(s).s;
    }

    private StringIndex unzip(String s) {
        StringBuilder sb = new StringBuilder();
        int multiplyNum = 0;
        int lastIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 숫자 나오면 기록
            if (c >= '0' && c <= '9') {
                if (multiplyNum > 0) {
                    multiplyNum = 10 * multiplyNum + Character.getNumericValue(c);
                } else {
                    multiplyNum = Character.getNumericValue(c);
                }
            } else if (c == '(') {
                StringIndex si = unzip(s.substring(i+1));
                if (multiplyNum == 0){
                    multiplyNum = 1;
                }
                for (int j = 0; j < multiplyNum; j++){
                    sb.append(si.s);
                }
                multiplyNum = 0;
                i = i+si.i+1;
            }else if(c == ')'){
                lastIndex = i;
                break;
            } else {
                sb.append(c);
                multiplyNum = 0;
            }
        }
        return new StringIndex(sb.toString(), lastIndex);
    }


    private static class StringIndex {
        String s; // text
        int i; // index

        public StringIndex(String s, int i) {
            this.s = s;
            this.i = i;
        }
    }

    public static void main(String[] args) {
        Unzip T = new Unzip();
        System.out.println(T.solution("3(a2(b))ef"));
        System.out.println(T.solution("2(ab)k3(bc)"));
        System.out.println(T.solution("2(ab3((cd)))"));
        System.out.println(T.solution("2(2(ab)3(2(ac)))"));
        System.out.println(T.solution("3(ab2(sg))"));
    }
}
