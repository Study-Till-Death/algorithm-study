package com.example.algorithmstudy.greedy;

import java.util.Arrays;

public class BattleGame {
    public int[] solution(String[] students){
        int n = students.length;
        int[] answer = new int[n];
        // 자기보다 낮은 공격력 + 다른 팀 사람들 다 잡으면
        // 그냥 for문 돌면 되긴 하는데
        // 공격력 순 정렬 해야되나
        // 그냥 더 큰 쪽 점수추가하고 중복전투 발생 안하도록 구성

        for (int i = 0; i < n; i++) {
            String[] studuent1 = students[i].split(" ");
            for (int j = i + 1; j < n; j++) {
                String[] studuent2 = students[j].split(" ");
                if (!studuent1[0].equals(studuent2[0])) { // 둘이 팀 다르면 배틀
                    // 공격력 더 높은 사람에게 점수 추가
                    if (Integer.parseInt(studuent1[1]) > Integer.parseInt(studuent2[1])) {
                        answer[i] += Integer.parseInt(studuent2[1]);
                    }else if (Integer.parseInt(studuent1[1]) < Integer.parseInt(studuent2[1])) {
                        answer[j] += Integer.parseInt(studuent1[1]);
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args){
        BattleGame T = new BattleGame();
        System.out.println(Arrays.toString(T.solution(new String[]{"a 20", "b 12", "a 10", "c 11", "e 12"})));
        System.out.println(Arrays.toString(T.solution(new String[]{"a 17", "b 12", "a 10", "c 11", "b 24", "a 25", "b 12"})));
        System.out.println(Arrays.toString(T.solution(new String[]{"b 20", "c 15", "a 200", "b 11", "b 24", "a 25", "b 12"})));
        System.out.println(Arrays.toString(T.solution(new String[]{"a 30", "a 25", "a 25", "b 20", "b 25", "a 25", "b 30"})));
    }
}
