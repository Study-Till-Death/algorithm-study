package com.example.algorithmstudy.week4;
import java.util.*;

//서로 다른 빈도수 만들기
public class MyAnswer011 {
    public int solution(String s){
        int answer = 0;

        HashMap<Character, Integer> charCountMap = new HashMap<>();


        for(char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        //문자열 갯수 리스트 만들고 정렬
        List<Integer> countList = new ArrayList<>(charCountMap.values());
        countList.sort(Collections.reverseOrder());

        //체크 리스트에 값 넣고 똑같은 값이 존재하면 1씩 빼면서 정답에 1씩 더함
        Set<Integer> checkCount = new HashSet<>();
        for(int i : countList) {
            while(i > 0 && checkCount.contains(i)) {
                i--;
                answer++;
            }
            checkCount.add(i);
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer011 T = new MyAnswer011();
        System.out.println(T.solution("aaabbbcc"));
        System.out.println(T.solution("aaabbc"));
        System.out.println(T.solution("aebbbbc"));
        System.out.println(T.solution("aaabbbcccde"));
        System.out.println(T.solution("aaabbbcccdddeeeeeff"));
    }
}
