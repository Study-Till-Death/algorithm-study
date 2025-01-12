package com.example.algorithmstudy.week5;

import java.util.*;

class Solution13 {
    public String solution13(String[] votes, int k){
        HashMap<String, HashSet<String>> voterMap = new HashMap<>();
        HashMap<String, Integer> nomineeMap = new HashMap<>();
        HashMap<String, Integer> voteMap = new HashMap<>();

        for(String vote : votes){
            // 투표자 후보자 사람 나누기
            String[] parsing = vote.split(" ");
            // 투표자
            String voter = parsing[0];
            // 후보자
            String nominee = parsing[1];

            if (!voterMap.containsKey(voter)) {
                voterMap.put(voter, new HashSet<>());
            }
            if (voterMap.get(voter).add(nominee)) {
                // 후보자 투표수 증가, 투표자가 동일 후보 투표 시 막기
                nomineeMap.put(nominee, nomineeMap.getOrDefault(nominee, 0) + 1);
            }
        }

        int maxVotes = 0;
        // 후보자 투표수 k 이상인 애들 찾기
        for (String voter : voterMap.keySet()) {
            int count = 0;
            for (String nominee : voterMap.get(voter)) {
                if (nomineeMap.get(nominee) >= k) {
                    count++;
                }
            }
            voteMap.put(voter, count);
            // 가장 높은 투표수 저장
            maxVotes = Math.max(maxVotes, count);
        }

        // 가장 높은 투표수인 후보자들 찾기
        List<String> topVoters = new ArrayList<>();
        for (String voter : voteMap.keySet()) {
            if (voteMap.get(voter) == maxVotes) {
                topVoters.add(voter);
            }
        }

        // 이렇게 쓰면 사전 순으로 가장 빠른거 예쁘게 찾는대요
        Collections.sort(topVoters);
        return topVoters.get(0);
    }

    public static void main(String[] args){
        Solution13 T = new Solution13();
        System.out.println(T.solution13(new String[]{"john tom", "daniel luis", "john luis", "luis tom", "daniel tom", "luis john"}, 2));
        System.out.println(T.solution13(new String[]{"john tom", "park luis", "john luis", "luis tom", "park tom", "luis john", "luis park", "park john", "john park", "tom john", "tom park", "tom luis"}, 2));
        System.out.println(T.solution13(new String[]{"cody tom", "john tom", "cody luis", "daniel luis", "john luis", "luis tom", "daniel tom", "luis john"}, 2));
        System.out.println(T.solution13(new String[]{"bob tom", "bob park", "park bob", "luis park", "daniel luis", "luis bob", "park luis", "tom bob", "tom luis", "john park", "park john"}, 3));
    }
}

