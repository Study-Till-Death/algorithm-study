package com.example.algorithmstudy.lessons015;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
class Solution {
    public String[] solution(String[] reports, int time){
        String[] answer = {};
        HashMap<String, LocalTime> inMap = new HashMap<>();
        HashMap<String, Long> countMap = new HashMap<>();

        for(String report : reports){
            String[] splitReport = report.split(" ");
            String name = splitReport[0];
            String eventTime = splitReport[1];
            String eventType = splitReport[2];

            if(eventType.equals("in")) {
                //in이면 map에 이름과 시간 저장
                inMap.put(name, LocalTime.parse(eventTime));
            } else {
                //out이면 시간차를 분으로 바꿔 coountMap에 더한다.
                long count = countMap.getOrDefault(name, 0L);
                long minutes = Duration.between(inMap.get(name), LocalTime.parse(eventTime)).toMinutes();
                countMap.put(name, count+minutes);
            }
        }

        //초과자 찾기
        ArrayList<String> list = new ArrayList<>();
        for(Map.Entry<String, Long> entry : countMap.entrySet()) {
            if(entry.getValue() > time){
                list.add(entry.getKey());
            }
        }

        //알파벳순으로 정렬
        list.sort(String::compareTo);
        answer = list.toArray(new String[0]);

        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(Arrays.toString(T.solution(new String[]{"john 09:30 in", "daniel 10:05 in", "john 10:15 out", "luis 11:57 in", "john 12:03 in", "john 12:20 out", "luis 12:35 out", "daniel 15:05 out"}, 60)));
        System.out.println(Arrays.toString(T.solution(new String[]{"bill 09:30 in", "daniel 10:00 in", "bill 11:15 out", "luis 11:57 in", "john 12:03 in", "john 12:20 out", "luis 14:35 out", "daniel 14:55 out"}, 120)));
        System.out.println(Arrays.toString(T.solution(new String[]{"cody 09:14 in", "bill 09:25 in", "luis 09:40 in", "bill 10:30 out", "cody 10:35 out", "luis 10:35 out", "bill 11:15 in", "bill 11:22 out", "luis 15:30 in", "luis 15:33 out"}, 70)));
        System.out.println(Arrays.toString(T.solution(new String[]{"chato 09:15 in", "emilly 10:00 in", "chato 10:15 out", "luis 10:57 in", "daniel 12:00 in", "emilly 12:20 out", "luis 11:20 out", "daniel 15:05 out"}, 60)));
    }
}
