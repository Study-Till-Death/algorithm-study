package com.example.algorithmstudy.lessons014;

import java.time.LocalTime;
import java.util.*;
class Solution {
    public String[] solution(String[] reports, String times){
        String[] answer = {};
        HashMap<String, LocalTime> suspectMap = new HashMap<>();

        //범행추정시간 시작과 끝 구하기
        String[] time = times.split(" ");
        LocalTime from = LocalTime.parse(time[0]);
        LocalTime to = LocalTime.parse(time[1]);

        //용의자별 입장시간 구하기
        for(String report : reports){
            String[] reportInfo = report.split(" ");
            suspectMap.put(reportInfo[0], LocalTime.parse(reportInfo[1]));
        }

        //범행추정시간 중 입장한 최종용의자 구하기
        HashMap<String, LocalTime> finalSuspectMap = new HashMap<>();
        for(Map.Entry<String, LocalTime> entry : suspectMap.entrySet()){
            if((entry.getValue().isAfter(from) || entry.getValue().equals(from)) && (entry.getValue().isBefore(to) || entry.getValue().equals(to))){
                finalSuspectMap.put(entry.getKey(), entry.getValue());
            }
        }

        //최종용의자 시간순으로 정렬하기
        List<Map.Entry<String, LocalTime>> list = new ArrayList<>(finalSuspectMap.entrySet());
        list.sort(Comparator.comparing(Map.Entry::getValue));

        answer = list.stream().map(Map.Entry::getKey).toArray(String[]::new);

        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(Arrays.toString(T.solution(new String[]{"john 15:23", "daniel 09:30", "tom 07:23", "park 09:59", "luis 08:57"}, "08:33 09:45")));
        System.out.println(Arrays.toString(T.solution(new String[]{"ami 12:56", "daniel 15:00", "bob 19:59", "luis 08:57", "bill 17:35", "tom 07:23", "john 15:23", "park 09:59"}, "15:01 19:59")));
        System.out.println(Arrays.toString(T.solution(new String[]{"cody 14:20", "luis 10:12", "alice 15:40", "tom 15:20", "daniel 14:50"}, "14:20 15:20")));
    }
}
