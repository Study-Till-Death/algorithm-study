package com.example.algorithmstudy.hashing.week5;

import java.util.*;

public class WarningMail {
    public String[] solution(String[] reports, int time){
        HashMap<String, Integer> enterTimeMap = new HashMap<>();
        HashMap<String, Integer> timeCountMap = new HashMap<>();

        // 예시는 이벤트 시간 순서대로 작성되어 있으니 별도로 정렬 안함
        ArrayList<String> warningList = new ArrayList<>();
        for (String report : reports) {
            String[] split = report.split(" ");
            String name = split[0];
            int eventTime = timeToInt(split[1]);
            String eventType = split[2];
            if (eventType.equals("in")) {
                enterTimeMap.put(name, eventTime);
            }
            if (eventType.equals("out")) {
                int gap = eventTime - enterTimeMap.get(name);
                timeCountMap.put(name, timeCountMap.getOrDefault(name, 0) + gap);
                if (timeCountMap.get(name) > time){
                    warningList.add(name);
                }
            }
        }
        // 이름 순 정렬 후 배열화
        List<String> sortedWarningList = warningList.stream().sorted(Comparator.comparing(report -> report.split(" ")[0])).toList();
        
        return sortedWarningList.toArray(new String[0]);
    }

    // hh:mm -> 전부 분으로 변환 01:23 -> 83분
    private int timeToInt(String time){
        return Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
    }

    public static void main(String[] args){
        WarningMail T = new WarningMail();
        System.out.println(Arrays.toString(T.solution(new String[]{"john 09:30 in", "daniel 10:05 in", "john 10:15 out", "luis 11:57 in", "john 12:03 in", "john 12:20 out", "luis 12:35 out", "daniel 15:05 out"}, 60)));
        System.out.println(Arrays.toString(T.solution(new String[]{"bill 09:30 in", "daniel 10:00 in", "bill 11:15 out", "luis 11:57 in", "john 12:03 in", "john 12:20 out", "luis 14:35 out", "daniel 14:55 out"}, 120)));
        System.out.println(Arrays.toString(T.solution(new String[]{"cody 09:14 in", "bill 09:25 in", "luis 09:40 in", "bill 10:30 out", "cody 10:35 out", "luis 10:35 out", "bill 11:15 in", "bill 11:22 out", "luis 15:30 in", "luis 15:33 out"}, 70)));
        System.out.println(Arrays.toString(T.solution(new String[]{"chato 09:15 in", "emilly 10:00 in", "chato 10:15 out", "luis 10:57 in", "daniel 12:00 in", "emilly 12:20 out", "luis 11:20 out", "daniel 15:05 out"}, 60)));
    }
}
