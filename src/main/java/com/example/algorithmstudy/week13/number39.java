package com.example.algorithmstudy.week13;

import java.util.*;

// IP 주소
class Solution39 {
    public String[] solution39(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result.toArray(new String[0]);
    }

    // 문자열을 4개로 나누기
    private void backtrack(String s, int start, List<String> segments, List<String> result) {
        // IP 부분들이 4가 됐는지
        if (segments.size() == 4) {
            // 모든 문자 다 사용했는지
            if (start == s.length()) {
                result.add(String.join(".", segments));
            }
            return;
        }

        // 현재 위치에서 최대 3자리 숫자를 선택하여 IP의 한 부분으로 사용
        for (int len = 1; len <= 3; len++) {
            if (start + len > s.length()) break; // 범위를 벗어나면 종료

            String part = s.substring(start, start + len);

            // 유효한 IP 숫자인지 확인
            if (isValid(part)) {
                segments.add(part); // 현재 구간 추가
                backtrack(s, start + len, segments, result); // 다음 구간 탐색
                segments.remove(segments.size() - 1); // 백트래킹
            }
        }
    }

    private boolean isValid(String part) {
        if (part.length() > 1 && part.startsWith("0")) return false; // 01, 00 같은 경우 불가능
        int num = Integer.parseInt(part);
        return num >= 0 && num <= 255;
    }

    public static void main(String[] args){
        Solution39 T = new Solution39();
        System.out.println(Arrays.toString(T.solution39("2025505")));
        System.out.println(Arrays.toString(T.solution39("0000")));
        System.out.println(Arrays.toString(T.solution39("255003")));
        System.out.println(Arrays.toString(T.solution39("155032012")));
        System.out.println(Arrays.toString(T.solution39("02325123")));
        System.out.println(Arrays.toString(T.solution39("121431211")));
    }
}

