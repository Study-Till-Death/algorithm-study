package com.example.algorithmstudy.week13;
import java.util.*;

public class MyAnswer039 {
    List<String> answerArray = new ArrayList<>();
    public String[] solution(String s){
        answerArray.clear();
        isIP(s, 0, new ArrayList<>());
        return answerArray.toArray(new String[0]);
    }

    //IP로 사능한 문자열만 뽑는 함수
    public void isIP(String s, int start, List<String> current) {
        if(current.size() == 4) {
            if(start == s.length()) {
                answerArray.add(String.join(".", current));
            }
            return;
        }

        for(int len = 1; len <= 3; len++) {
            if(start + len > s.length()) break;

            String part = s.substring(start, start + len);
            if(isValid(part)) {
                current.add(part);
                isIP(s, start + len, current);
                current.removeLast();
            }
        }
    }

    //IP로 가능 한지 체크
    public boolean isValid(String part) {
        if(part.length() > 1 && part.startsWith("0")) return false;
        int num = Integer.parseInt(part);
        return num >= 0 && num <= 255;
    }

    public static void main(String[] args){
        MyAnswer039 T = new MyAnswer039();
        System.out.println(Arrays.toString(T.solution("2025505")));
        System.out.println(Arrays.toString(T.solution("0000")));
        System.out.println(Arrays.toString(T.solution("255003")));
        System.out.println(Arrays.toString(T.solution("155032012")));
        System.out.println(Arrays.toString(T.solution("02325123")));
        System.out.println(Arrays.toString(T.solution("121431211")));
    }
}
