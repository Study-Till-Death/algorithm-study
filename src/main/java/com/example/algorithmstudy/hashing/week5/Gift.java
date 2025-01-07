package com.example.algorithmstudy.hashing.week5;

import java.util.*;

public class Gift {
    // 그냥 가장 많이 추천한 사람 고르면 안되나
    // 아 투표수 충족 해야 되는 구나
    public String solution(String[] votes, int k) {
        String answer = "";

        // 무효표를 죽이고 나머지중 젤 많은 사람

        HashSet<String> qualifiers = new HashSet<>();
        HashMap<String, Integer> giverCountMap = new HashMap<>();
        HashMap<String, Integer> takerCountMap = new HashMap<>();

        for (String vote : votes) {
            String b = vote.split(" ")[1];
            if (takerCountMap.getOrDefault(b, 0) + 1 >= k) {
                // 후보자 기준 충족시 바로 qualifiers 넣어줌
                qualifiers.add(b);
                continue;
            }
            takerCountMap.put(b, takerCountMap.getOrDefault(b, 0) + 1);
        }

        int max = 0;
        
        List<String> sortedVotes = Arrays.stream(votes).sorted(Comparator.comparing(vote -> vote.split(" ")[0])).toList();
        // 투표자 이름 명 순서로 정렬
        for (String vote : sortedVotes){
            String a = vote.split(" ")[0];
            String b = vote.split(" ")[1];
            if(!qualifiers.contains(b)) { // 유효표 아니면 스킵
                continue;
            }
            // 후보자로 등록된 사람을 투표한 유효표일 때만 count 체크
            giverCountMap.put(a, giverCountMap.getOrDefault(a, 0) + 1);
            if (giverCountMap.get(a) > max) {
                // 투표자 이름 순으로 정렬했으므로 이름 체크 없이 전 사람보다 크기만 하면 된다
                max = giverCountMap.get(a);
                answer = a;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Gift T = new Gift();
        System.out.println(T.solution(new String[]{"john tom", "daniel luis", "john luis", "luis tom", "daniel tom", "luis john"}, 2));
        System.out.println(T.solution(new String[]{"john tom", "park luis", "john luis", "luis tom", "park tom", "luis john", "luis park", "park john", "john park", "tom john", "tom park", "tom luis"}, 2));
        System.out.println(T.solution(new String[]{"cody tom", "john tom", "cody luis", "daniel luis", "john luis", "luis tom", "daniel tom", "luis john"}, 2));
        System.out.println(T.solution(new String[]{"bob tom", "bob park", "park bob", "luis park", "daniel luis", "luis bob", "park luis", "tom bob", "tom luis", "john park", "park john"}, 3));
    }

}
