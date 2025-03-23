package com.algorithm;
import java.util.*;

class a38 {
    public String[] solution(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1); // 문자열을 배열화 시켜 개수와 함께 map에 넣음
        }

        int oddCount = 0;
        char middle = 0;
        StringBuilder half = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            char ch = entry.getKey();
            int count = entry.getValue();

            if (count % 2 == 1) { // 홀수 개인 문자 찾아서 middle에 배치
                oddCount++;
                middle = ch;
            }
            for (int i = 0; i < count / 2; i++) {
                half.append(ch);
            }
        }

        if (oddCount > 1) return new String[0]; // 홀수 문자가 2개 이상이면 팰린드롬 불가능

        Set<String> halfPermutations = new HashSet<>();
        boolean[] visited = new boolean[half.length()];
        backtrack(half.toString().toCharArray(), visited, new StringBuilder(), halfPermutations);

        List<String> result = new ArrayList<>();
        for (String halfStr : halfPermutations) {
            String reversed = new StringBuilder(halfStr).reverse().toString();
            String palindrome = (oddCount == 1) ? halfStr + middle + reversed : halfStr + reversed;
            result.add(palindrome);
        }

        return result.toArray(new String[0]);
    }

    // 중복 방지
    private void backtrack(char[] chars, boolean[] visited, StringBuilder path, Set<String> result) {
        if (path.length() == chars.length) {
            result.add(path.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) continue;
            if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) continue; // 중복 방지

            visited[i] = true;
            path.append(chars[i]);
            backtrack(chars, visited, path, result);
            path.deleteCharAt(path.length() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        a38 T = new a38();
        System.out.println(Arrays.toString(T.solution("aaaabb")));
        System.out.println(Arrays.toString(T.solution("abbcc")));
        System.out.println(Arrays.toString(T.solution("abbccee")));
        System.out.println(Arrays.toString(T.solution("abbcceee")));
        System.out.println(Arrays.toString(T.solution("ffeffaae")));
    }
}
