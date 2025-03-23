package com.example.algorithmstudy.lessons038;

import java.util.*;
class Solution {
    boolean[] selected;
    char[] chars;
    int length;
    Set<String> set;

    public String[] solution(String s){

        set = new HashSet<>();
        selected = new boolean[s.length()];
        length = s.length();
        chars = s.toCharArray();

        dfs("");

        return set.toArray(new String[0]);
    }

    private void dfs(String string) {
        //string 조합이 끝났다면, 조건에 만족하는지 체크
        if(string.length() == length) {
            check(string);
            return;
        }

        //순회
        for(int i = 0; i < length; i++) {
            //이미 사용했으면 패스
            if(selected[i]) {
                continue;
            }
            selected[i] = true;
            dfs(string + chars[i]);
            selected[i] = false;
        }
    }

    private void check(String string) {
        String reverseString = new StringBuilder(string).reverse().toString();
        //뒤집은 string과 같으면 set에 추가
        if(string.equals(reverseString)) {
            set.add(string);
        }
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(Arrays.toString(T.solution("aaaabb")));
        System.out.println(Arrays.toString(T.solution("abbcc")));
        System.out.println(Arrays.toString(T.solution("abbccee")));
        System.out.println(Arrays.toString(T.solution("abbcceee")));
        System.out.println(Arrays.toString(T.solution("ffeffaae")));
    }
}