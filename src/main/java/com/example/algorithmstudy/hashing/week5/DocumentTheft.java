package com.example.algorithmstudy.hashing.week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DocumentTheft {
    public String[] solution(String[] reports, String times){
        String[] answer = {};
        int startTime = timeToInt(times.split(" ")[0]);
        int endTime = timeToInt(times.split(" ")[1]);

        // 시간 순으로 정렬
        List<String> sortedReports = Arrays.stream(reports).sorted(Comparator.comparing(report -> timeToInt(report.split(" ")[1]))).toList();
        List<String> suspects = new ArrayList<>();
        for (String report : sortedReports) {
            String name = report.split(" ")[0];
            int time = timeToInt(report.split(" ")[1]);
            if (startTime <= time && time <= endTime) { // 범행 시간 안에 포함 되면
                suspects.add(name);
            }
        }

        // toArray -> 이거 처음 써보는데 좀 특이함 array 갯수 따라가고 타입만 인자로 받은 배열 따라감
        answer = suspects.toArray(new String[0]);

        return answer;
    }

    // hh:mm -> hhmm 4자리 숫자로 변환
    private int timeToInt(String time){
        return Integer.parseInt(time.replace(":", ""));
    }

    public static void main(String[] args){
        DocumentTheft T = new DocumentTheft();
        System.out.println(Arrays.toString(T.solution(new String[]{"john 15:23", "daniel 09:30", "tom 07:23", "park 09:59", "luis 08:57"}, "08:33 09:45")));
        System.out.println(Arrays.toString(T.solution(new String[]{"ami 12:56", "daniel 15:00", "bob 19:59", "luis 08:57", "bill 17:35", "tom 07:23", "john 15:23", "park 09:59"}, "15:01 19:59")));
        System.out.println(Arrays.toString(T.solution(new String[]{"cody 14:20", "luis 10:12", "alice 15:40", "tom 15:20", "daniel 14:50"}, "14:20 15:20")));
    }
}
