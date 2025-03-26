package com.algorithm;

import java.util.*;

public class a39 {
    public String[] solution(String s){
        List<String> result = new ArrayList<>();
        int len = s.length();

        // 최소 4자리, 최대 12자리
        if (len < 4 || len > 12) return new String[0];

        for (int i = 1; i < Math.min(4, len - 2); i++) { // 첫번째 란 숫자 갯수: (4-1)개 혹은 문자길이 -2
            for (int j = i + 1; j < Math.min(i + 4, len - 1); j++) { // 두번째 란 숫자 갯수: ((i+4) - (i+1)) = 3 혹은 문자길이 -1
                for (int k = j + 1; k < Math.min(j + 4, len); k++) {
                    String a = s.substring(0, i); // 각 해당하는 인덱스 자름
                    String b = s.substring(i, j);
                    String c = s.substring(j, k);
                    String d = s.substring(k);

                    if (isValid(a) && isValid(b) && isValid(c) && isValid(d)) { //검증
                        result.add(String.join(".", a, b, c, d));
                    }
                }
            }
        }

        return result.toArray(new String[0]);
    }

    private boolean isValid(String part) {
        if (part.isEmpty() || part.length() > 3) return false;
        if (part.startsWith("0") && part.length() > 1) return false;
        int num = Integer.parseInt(part);

        return num >= 0 && num <= 255;
    }

    public static void main(String[] args){
        a39 T = new a39();
        System.out.println(Arrays.toString(T.solution("2025505")));
        System.out.println(Arrays.toString(T.solution("0000")));
        System.out.println(Arrays.toString(T.solution("255003")));
        System.out.println(Arrays.toString(T.solution("155032012")));
        System.out.println(Arrays.toString(T.solution("02325123")));
        System.out.println(Arrays.toString(T.solution("121431211")));
    }
}
