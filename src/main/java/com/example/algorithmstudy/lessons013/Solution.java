package com.example.algorithmstudy.lessons013;

import java.util.*;
class Solution {
    public String solution(String[] votes, int k){
        String answer = " ";
        HashMap<String, ArrayList<String>> nominateResult = new HashMap<>();
        HashMap<String, Integer> voteResult = new HashMap<>();

        //후보를 key로, 추천자리스트를 value로
        for(String vote : votes){
            String[] nominateInfo = vote.split(" ");
            ArrayList<String> arrayList = nominateResult.getOrDefault(nominateInfo[1], new ArrayList<>());
            arrayList.add(nominateInfo[0]);
            nominateResult.put(nominateInfo[1], arrayList);
        }

        //최소추천수 이상의 후보를 추천한 투표자를 key로, 받을 선물 수를 value로 + 선물최대갯수 구하기
        int max = 0;
        for(Map.Entry<String, ArrayList<String>> entry : nominateResult.entrySet()){
            if(entry.getValue().size() >= k){
                for(String voter : entry.getValue()){
                    voteResult.merge(voter, 1, Integer::sum);

                    if(voteResult.get(voter) > max){
                        max = voteResult.get(voter);
                    }
                }
            }
        }

        //선물부자 구하기
        ArrayList<String> result = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : voteResult.entrySet()){
            if(entry.getValue() >= max){
                result.add(entry.getKey());
            }
        }

        //알파벳순으로 정렬하기
        result.sort(String::compareTo);

        answer = result.getFirst();

        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new String[]{"john tom", "daniel luis", "john luis", "luis tom", "daniel tom", "luis john"}, 2));
        System.out.println(T.solution(new String[]{"john tom", "park luis", "john luis", "luis tom", "park tom", "luis john", "luis park", "park john", "john park", "tom john", "tom park", "tom luis"}, 2));
        System.out.println(T.solution(new String[]{"cody tom", "john tom", "cody luis", "daniel luis", "john luis", "luis tom", "daniel tom", "luis john"}, 2));
        System.out.println(T.solution(new String[]{"bob tom", "bob park", "park bob", "luis park", "daniel luis", "luis bob", "park luis", "tom bob", "tom luis", "john park", "park john"}, 3));
    }
}
