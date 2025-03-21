package com.example.algorithmstudy.week13;
import java.util.*;

public class MyAnswer038 {
    boolean[] selected;
    List<String> result = new ArrayList<>();
    public String[] solution(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        selected = new boolean[s.length()];
        result = new ArrayList<>();
        getStringArrayList(chars, new StringBuilder());
        return result.toArray(new String[0]);
    }

    //모든 문자열 조합 생성 함수
    private void getStringArrayList(char[] chars, StringBuilder current) {
        if (current.length() == chars.length) {
            if (isPalindrome(current.toString())) {
                result.add(current.toString());
            }
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (selected[i]) continue;
            if (i > 0 && chars[i] == chars[i - 1] && !selected[i - 1]) continue;

            selected[i] = true;
            current.append(chars[i]);

            getStringArrayList(chars, current);

            selected[i] = false;
            current.deleteCharAt(current.length() - 1);
        }
    }

    //팰린드롬 체크
    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args){
        MyAnswer038 T = new MyAnswer038();
        System.out.println(Arrays.toString(T.solution("aaaabb")));
        System.out.println(Arrays.toString(T.solution("abbcc")));
        System.out.println(Arrays.toString(T.solution("abbccee")));
        System.out.println(Arrays.toString(T.solution("abbcceee")));
        System.out.println(Arrays.toString(T.solution("ffeffaae")));
    }
}
