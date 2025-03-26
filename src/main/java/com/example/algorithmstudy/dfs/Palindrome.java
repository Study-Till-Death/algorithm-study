package com.example.algorithmstudy.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Palindrome {
    int N;
    char[] charArray;
    List<String> answer;
    public String[] solution(String s){
        // 이래도 되나
        // s 가 영어 소문자만 온다는 보장 X
        // 고로 그냥 일단 다 dfs 박는 식으로 해결
        N = s.length();
        charArray = s.toCharArray();
        boolean[] isUsed = new boolean[N];
        answer = new ArrayList<String>();

        dfs("",isUsed);

        return answer.toArray(new String[answer.size()]);
    }

    private void dfs(String now, boolean[] isUsed) {
        if (now.length() == N) {
            checkPalindrome(now);
            return;
        }
        for (int i = 0; i < N; i++) {
            if(isUsed[i]){
                continue;
            }
            isUsed[i] = true;
            dfs(now + charArray[i], isUsed);
            isUsed[i] = false;
        }
    }

    private void checkPalindrome(String now) {
        boolean isPalindrome = true;
        for (int i = 0; i < N/2; i++) {
            if (now.charAt(i) != now.charAt(N-1-i)) {
                isPalindrome = false;
                break;
            }
        }
        if (isPalindrome && !answer.contains(now)) { // 중복방지
            answer.add(now);
        }
    }

    public static void main(String[] args){
        Palindrome T = new Palindrome();
        System.out.println(Arrays.toString(T.solution("aaaabb")));
        System.out.println(Arrays.toString(T.solution("abbcc")));
        System.out.println(Arrays.toString(T.solution("abbccee")));
        System.out.println(Arrays.toString(T.solution("abbcceee")));
        System.out.println(Arrays.toString(T.solution("ffeffaae")));
    }
}
