package com.example.algorithmstudy.hashing.week4;

import java.util.HashMap;
import java.util.HashSet;

public class Different {
    public int solution(String s) {
        int answer = 0;
        // 같은게 나오면 a b c d e 순서대로 줄인다
        // a 랑 c 랑 같은 숫자면 c 줄이나 a 줄이나 똑같기 때문
        // 0개가 된다면 없기 때문에 괜찮음 (a 0개, b 0개 문제 없음)
        HashMap<Character, Integer> map = new HashMap<>(); // 문자 넣으면 몇갠지
        HashMap<Integer, HashSet<Character>> map2 = new HashMap<>(); // 숫자 넣으면 해당 숫자 갯수 문자 hashset
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 97);
            int count = map.getOrDefault(c, 0);
            HashSet<Character> newHashSet = map2.getOrDefault(count, new HashSet<>());
            newHashSet.add(c);
            map2.put(count, newHashSet);
        }

        for (int i = 0; i < 26; i++) {
            // 문자 넣고 count 알아오고
            while (isDuplicated(map, i, map2)) {
                char c = (char) (i + 97);
                int count = map.getOrDefault(c, 0);
                // 그 숫자가 0이거나 그 카운트에 해당하는 hashSet 의 size 가 1이면 통과
                if (count != 0 && map2.getOrDefault(count, new HashSet<>()).size() != 1) {
                    map.put(c, count - 1);
                    map2.get(count).remove(c);
                    HashSet<Character> newHashSet = map2.getOrDefault(count - 1, new HashSet<>());
                    newHashSet.add(c);
                    map2.put(count - 1, newHashSet);
                    answer++;
                }
            }

        }
        return answer;
    }

    // 너무 복잡하게 푼 거 같아서 답지봄, 아래는 답지 풀이 공부용으로 그냥 가져와서 비교용
    public int solution2(String s) {
        int answer = 0;
        HashMap<Character, Integer> sH = new HashMap<>();
        HashSet<Integer> ch = new HashSet<>();
        for(char x : s.toCharArray()){
            sH.put(x, sH.getOrDefault(x, 0) + 1);
        }
        // 아 그냥 hashSet 하나로 처리하네
        // 해당 문자를 보관하는게 아니라 count 보관하고 겹치는지만 보고 겹치면 answer 증가
        // 문자 -> 카운트 -> 겹치나 체크할 필요 없이 그냥 카운트만 되는 대로 딱딱 박아넣는게 괜찮은듯
        for(char key : sH.keySet()){
            while(ch.contains(sH.get(key))){
                answer++;
                sH.put(key, sH.get(key) - 1);
            }
            if(sH.get(key) == 0) continue;
            ch.add(sH.get(key));
        }
        return answer;
    }

    private static boolean isDuplicated(HashMap<Character, Integer> map, int i, HashMap<Integer, HashSet<Character>> map2) {
        char c = (char)(i+97); // 해당 문자
        int count = map.getOrDefault(c,0); // 해당 문자의 카운트
        // 카운트가 0이 아니고 해당 카운트 hashSet 의 size 도 1이 아닌 경우(중복인 경우)
        return count != 0 && map2.getOrDefault(count, new HashSet<>()).size() != 1;
    }

    public static void main(String[] args) {
        Different T = new Different();
        System.out.println(T.solution("aaabbbcc"));
        System.out.println(T.solution("aaabbc"));
        System.out.println(T.solution("aebbbbc"));
        System.out.println(T.solution("aaabbbcccde"));
        System.out.println(T.solution("aaabbbcccdddeeeeeff"));
    }
}
