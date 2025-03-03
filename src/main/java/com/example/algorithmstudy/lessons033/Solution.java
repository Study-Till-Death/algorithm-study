package com.example.algorithmstudy.lessons033;

import java.util.*;
class Solution {
    public int[] solution(String[] students){
        int n = students.length;
        int[] answer = new int[n];

        ArrayList<Status> list = new ArrayList<Status>();

        //팀과 공격력 Status로 정리
        for(int i = 0; i < students.length; i++) {
            String[] student = students[i].split(" ");
            list.add(new Status(student[0], Integer.parseInt(student[1])));
        }

        for(int i = 0; i < list.size(); i++) {
            Status attacker = list.get(i);
            for(int j = 0; j < list.size(); j++) {
                Status defender = list.get(j);

                if(!attacker.team.equals(defender.team) && attacker.attackPoint > defender.attackPoint) {
                    attacker.point+=defender.attackPoint;
                }
            }
            answer[i] = attacker.point;
        }

        return answer;
    }

    static class Status {
        String team;
        int attackPoint;
        int point;

        public Status(String team, int attackPoint) {
            this.team = team;
            this.attackPoint = attackPoint;
        }
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(Arrays.toString(T.solution(new String[]{"a 20", "b 12", "a 10", "c 11", "e 12"})));
        System.out.println(Arrays.toString(T.solution(new String[]{"a 17", "b 12", "a 10", "c 11", "b 24", "a 25", "b 12"})));
        System.out.println(Arrays.toString(T.solution(new String[]{"b 20", "c 15", "a 200", "b 11", "b 24", "a 25", "b 12"})));
        System.out.println(Arrays.toString(T.solution(new String[]{"a 30", "a 25", "a 25", "b 20", "b 25", "a 25", "b 30"})));
    }
}