package com.example.algorithmstudy.week11;

import java.util.*;

// 전투 게임
class Solution33 {
    public int[] solution33(String[] students) {
        int n = students.length;
        int[] answer = new int[n];

        Map<String, Integer> teamPower = new HashMap<>(); // 각 팀의 총 공격력 저장
        List<int[]> studentData = new ArrayList<>(); // (공격력, 팀, 인덱스)

        Map<String, Integer> teamIdMap = new HashMap<>();
        int teamId = 0;

        // 학생 정보 파싱
        for (int i = 0; i < n; i++) {
            String[] parts = students[i].split(" ");
            String team = parts[0];
            int power = Integer.parseInt(parts[1]);

            if (!teamIdMap.containsKey(team)) {
                teamIdMap.put(team, teamId++);
            }

            studentData.add(new int[]{power, teamIdMap.get(team), i});
            teamPower.put(team, teamPower.getOrDefault(team, 0) + power);
        }

        // 공격력 기준으로 정렬
        studentData.sort(Comparator.comparingInt(a -> a[0]));

        // 누적 공격력
        int totalPower = 0;
        Map<Integer, Integer> teamWisePower = new HashMap<>(); // 팀별 누적 공격력

        int i = 0;
        while (i < n) {
            int power = studentData.get(i)[0];
            List<int[]> samePowerStudents = new ArrayList<>();

            // 같은 공격력을 가진 학생들을 한 번에 처리
            while (i < n && studentData.get(i)[0] == power) {
                samePowerStudents.add(studentData.get(i));
                i++;
            }

            // 현재까지의 총 공격력에서 팀별 공격력을 뺀 점수를 저장
            for (int[] student : samePowerStudents) {
                int team = student[1];
                int index = student[2];
                int myTeamPower = teamWisePower.getOrDefault(team, 0);
                answer[index] = totalPower - myTeamPower;
            }

            // 같은 공격력을 가진 학생들을 누적 업데이트
            for (int[] student : samePowerStudents) {
                int team = student[1];
                int powerValue = student[0];
                totalPower += powerValue;
                teamWisePower.put(team, teamWisePower.getOrDefault(team, 0) + powerValue);
            }
        }

        return answer;
    }

    public static void main(String[] args){
        Solution33 T = new Solution33();
        System.out.println(Arrays.toString(T.solution33(new String[]{"a 20", "b 12", "a 10", "c 11", "e 12"})));
        System.out.println(Arrays.toString(T.solution33(new String[]{"a 17", "b 12", "a 10", "c 11", "b 24", "a 25", "b 12"})));
        System.out.println(Arrays.toString(T.solution33(new String[]{"b 20", "c 15", "a 200", "b 11", "b 24", "a 25", "b 12"})));
        System.out.println(Arrays.toString(T.solution33(new String[]{"a 30", "a 25", "a 25", "b 20", "b 25", "a 25", "b 30"})));
    }
}
