package com.example.algorithmstudy.week5;

import java.util.*;

class Solution15 {
    public String[] solution15(String[] reports, int time){
        Map<String, Integer> newReports = new HashMap<>();
        Map<String, Integer> times = new HashMap<>();

        for (String report : reports) {
            // 이름 시간 입장/퇴장 나누기
            String[] parsing = report.split(" ");
            String name = parsing[0];
            int inOutTime = toMinutes(parsing[1]);
            String inOut = parsing[2];

            if (inOut.equals("in")) {
                // 입장
                times.put(name, inOutTime);
            } else if (inOut.equals("out") && times.containsKey(name)) {
                // 퇴장, 이용 시간 계산
                int usingTime = inOutTime - times.remove(name);
                newReports.put(name, newReports.getOrDefault(name, 0) + usingTime);
            }
        }

        // 특정 시간 넘긴 사람 찾기
        List<String> warnings = new ArrayList<>();
        for (String name : newReports.keySet()) {
            if (newReports.get(name) > time) {
                warnings.add(name);
            }
        }

        // 알파벳 순 정렬
        Collections.sort(warnings);

        return warnings.toArray(new String[0]);
    }

    // 분으로 변환
    private int toMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    public static void main(String[] args){
        Solution15 T = new Solution15();
        System.out.println(Arrays.toString(T.solution15(new String[]{"john 09:30 in", "daniel 10:05 in", "john 10:15 out", "luis 11:57 in", "john 12:03 in", "john 12:20 out", "luis 12:35 out", "daniel 15:05 out"}, 60))); // ["daniel", "john"]
        System.out.println(Arrays.toString(T.solution15(new String[]{"bill 09:30 in", "daniel 10:00 in", "bill 11:15 out", "luis 11:57 in", "john 12:03 in", "john 12:20 out", "luis 14:35 out", "daniel 14:55 out"}, 120))); // ["daniel", "luis"]
        System.out.println(Arrays.toString(T.solution15(new String[]{"cody 09:14 in", "bill 09:25 in", "luis 09:40 in", "bill 10:30 out", "cody 10:35 out", "luis 10:35 out", "bill 11:15 in", "bill 11:22 out", "luis 15:30 in", "luis 15:33 out"}, 70))); // ["bill", "cody"]
        System.out.println(Arrays.toString(T.solution15(new String[]{"chato 09:15 in", "emilly 10:00 in", "chato 10:15 out", "luis 10:57 in", "daniel 12:00 in", "emilly 12:20 out", "luis 11:20 out", "daniel 15:05 out"}, 60))); // ["daniel", "emilly"]
    }
}