package com.algorithm;

import java.util.*;

public class a13_chairmanElection {
    // 뭔가 더 최적화 할 방법이 있을 거 같은데...
    public String solution(String[] votes, int k){
        HashMap<String, Integer> member = new HashMap<>(); //출마멤버(아무나)
        List<String> candidate = new ArrayList<>(); //회장후보
        HashMap<String, List<String>> presentCandidate = new HashMap<>(); //선물받을사람후보(아무나)
        HashMap<String, Integer> presentCount = new HashMap<>(); //실제로 선물받을사람
        TreeSet<String> present = new TreeSet<>(); //그중 가장 많이 받은 사람들

        // 회장후보 받는중(아무나)
        for (int i = 0; i < votes.length; i++) {
            String[] name = votes[i].split(" ");
            member.put(name[1], member.getOrDefault(name[1], 0) + 1); // 투표된 멤버와 각 투표수
            List<String> votedMembers = presentCandidate.get(name[1]) == null ? new ArrayList<>() : presentCandidate.get(name[1]);
            votedMembers.add(name[0]);
            presentCandidate.put(name[1], votedMembers); // 멤버와 투표자들
        }

        // 회장후보 추리는 중(k이상)
        for (Map.Entry<String, Integer> entry : member.entrySet()) {
            if (entry.getValue() >= k) {
                candidate.add(entry.getKey()); // 회장 후보
            }
        }

        // 투표감사선물돌리는중
        for (String c : candidate) {
            // 회장후보 된 사람에게 투표한 사람만 선물. 그에 해당하는 사람과 선물수
            presentCandidate.get(c).forEach(p -> presentCount.put(p, presentCount.getOrDefault(p, 0) + 1));
        }

        int max=0;
        // 모여서 선물누가많이받앗는지 세는중
        for (Map.Entry<String, Integer> entry : presentCount.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                present.clear();
                present.add(entry.getKey());
            } else if (entry.getValue() == max) {
                present.add(entry.getKey());
            }
        }

        //그중이름사전순으로빠른사람
        return present.getFirst().toString();
    }

    public static void main(String[] args){
        a13_chairmanElection T = new a13_chairmanElection();
        System.out.println(T.solution(new String[]{"john tom", "daniel luis", "john luis", "luis tom", "daniel tom", "luis john"}, 2));
        System.out.println(T.solution(new String[]{"john tom", "park luis", "john luis", "luis tom", "park tom", "luis john", "luis park", "park john", "john park", "tom john", "tom park", "tom luis"}, 2));
        System.out.println(T.solution(new String[]{"cody tom", "john tom", "cody luis", "daniel luis", "john luis", "luis tom", "daniel tom", "luis john"}, 2));
        System.out.println(T.solution(new String[]{"bob tom", "bob park", "park bob", "luis park", "daniel luis", "luis bob", "park luis", "tom bob", "tom luis", "john park", "park john"}, 3));
    }
}
