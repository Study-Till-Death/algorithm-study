package com.example.algorithmstudy.week13;

import java.util.*;

// 팰린드롬의 경우 수
class Solution38 {
    public String[] solution38(String s) {
        // 문자마다 빈도수 체크
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        // 팰린드롬 가능 여부 체크
        int oddCount = 0;
        Character oddChar = null;
        StringBuilder halfString = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            char key = entry.getKey();
            int count = entry.getValue();

            if (count % 2 == 1) {  // 홀수인 문자 체크
                oddCount++;
                oddChar = key;
                if (oddCount > 1) return new String[] {}; // 팰린드롬 불가능
            }

            // 반쪽 문자열 만들기
            for (int i = 0; i < count / 2; i++) {
                halfString.append(key);
            }
        }

        // 반쪽 문자열로 순열 생성
        char[] halfArray = halfString.toString().toCharArray();
        Set<String> permutations = new HashSet<>();
        generatePermutations(halfArray, 0, permutations);

        // 팰린드롬 만들기
        List<String> result = new ArrayList<>();
        for (String perm : permutations) {
            // 중앙에 올 문자가 없으면 좌우 대칭 생성
            if (oddChar == null) {
                result.add(perm + new StringBuilder(perm).reverse());
            }
            // 중앙에 올 문자가 있으면 가운데에 삽입
            else {
                result.add(perm + oddChar + new StringBuilder(perm).reverse());
            }
        }

        return result.toArray(new String[0]);
    }

    // 순열 생성
    private void generatePermutations(char[] arr, int index, Set<String> result) {
        if (index == arr.length) {
            result.add(new String(arr));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            if (i != index && arr[i] == arr[index]) continue; // 중복 방지
            swap(arr, i, index);
            generatePermutations(arr, index + 1, result);
            swap(arr, i, index); // 백트래킹
        }
    }

    // 바꾸기
    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args){
        Solution38 T = new Solution38();
        System.out.println(Arrays.toString(T.solution38("aaaabb")));
        System.out.println(Arrays.toString(T.solution38("abbcc")));
        System.out.println(Arrays.toString(T.solution38("abbccee")));
        System.out.println(Arrays.toString(T.solution38("abbcceee")));
        System.out.println(Arrays.toString(T.solution38("ffeffaae")));
    }
}