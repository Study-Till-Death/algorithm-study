package com.example.algorithmstudy.week5;
import java.util.*;

public class MyAnswer013 {
    public String solution(String[] votes, int k){
        String answer = " ";

        //추천 받은 사람, 받은 횟수
        Map<String, Integer> recommend = new HashMap<>();
        //선물 받는 사람, 받은 갯수
        Map<String, Integer> presentReceive = new HashMap<>();
        //추천인 , 추천받는 사람 HashSet
        Map<String, HashSet<String>> suggestBy = new HashMap<>();

        for (String vote : votes) {
            //추천한 사람
            String suggest = vote.split(" ")[0];
            //추천 받은 사람
            String recommended = vote.split(" ")[1];
            recommend.put(recommended, recommend.getOrDefault(recommended, 0) + 1);
            suggestBy.putIfAbsent(suggest, new HashSet<>());
            suggestBy.get(suggest).add(recommended);

        }

        for (Map.Entry<String, Integer> entry : recommend.entrySet()) {
            //k 횟수 이상 추천 받았는지 체크
            if(k <= entry.getValue()) {
                //기준치 이상으로 추천 받은 사람 key 값
                String entryValue = entry.getKey();
                for(Map.Entry<String, HashSet<String>> data : suggestBy.entrySet()) {
                    //추천 받은 사람 key값이 추천받는 사람 HashSet에 있는지 체크 후 선물 받는 사람 수 ++
                    if(data.getValue().contains(entryValue)){
                        presentReceive.put(data.getKey(), presentReceive.getOrDefault(data.getKey(), 0) + 1);
                    }
                }
            }
        }

        //Key값 문자열 기준 역정렬
        Map<String, Integer> answerMap = new TreeMap<>(Comparator.reverseOrder());
        answerMap.putAll(presentReceive);

        int maxPresents = 0;
        for (Map.Entry<String, Integer> entry : answerMap.entrySet()) {
            //이미 정렬 되어 있으니 조건 만족시 바로 바로 업데이트
            if(entry.getValue() >= maxPresents) {
                maxPresents = entry.getValue();
                answer = entry.getKey();
            }
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer013 T = new MyAnswer013();
        System.out.println(T.solution(new String[]{"john tom", "daniel luis", "john luis", "luis tom", "daniel tom", "luis john"}, 2));
        System.out.println(T.solution(new String[]{"john tom", "park luis", "john luis", "luis tom", "park tom", "luis john", "luis park", "park john", "john park", "tom john", "tom park", "tom luis"}, 2));
        System.out.println(T.solution(new String[]{"cody tom", "john tom", "cody luis", "daniel luis", "john luis", "luis tom", "daniel tom", "luis john"}, 2));
        System.out.println(T.solution(new String[]{"bob tom", "bob park", "park bob", "luis park", "daniel luis", "luis bob", "park luis", "tom bob", "tom luis", "john park", "park john"}, 3));
    }
}
