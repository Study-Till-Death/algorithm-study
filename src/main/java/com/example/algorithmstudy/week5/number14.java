package com.example.algorithmstudy.week5;
import java.util.*;

class Solution14 {
    public String[] solution14(String[] reports, String times){
        // 범위 시간 나누기
        String[] timeParsing = times.split(" ");
        int startTime = toMinutes(timeParsing[0]);
        int endTime = toMinutes(timeParsing[1]);

        // 사람 시간 나누기
        List<String[]> newReports = new ArrayList<>();
        for (String report : reports) {
            String[] reportParsing = report.split(" ");
            String name = reportParsing[0];
            int reportTime = toMinutes(reportParsing[1]);

            // 시간에 포함되는지 체크
            if (reportTime >= startTime && reportTime <= endTime) {
                newReports.add(new String[]{name, reportParsing[1]});
            }
        }

        // 코드가 너무 길어져서 제 단짝친구한테 물어보니까 이렇게 쓰래요 예쁜듯
        newReports.sort((a, b) -> toMinutes(a[1]) - toMinutes(b[1]));

        // 시간빼고 이름만 넣기
        List<String> result = new ArrayList<>();
        for (String[] report : newReports) {
            result.add(report[0]);
        }

        return result.toArray(new String[0]);
    }

    // 분으로 변환
    private int toMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    public static void main(String[] args){
        Solution14 T = new Solution14();
        System.out.println(Arrays.toString(T.solution14(new String[]{"john 15:23", "daniel 09:30", "tom 07:23", "park 09:59", "luis 08:57"}, "08:33 09:45"))); // ["daniel", "luis"]
        System.out.println(Arrays.toString(T.solution14(new String[]{"ami 12:56", "daniel 15:00", "bob 19:59", "luis 08:57", "bill 17:35", "tom 07:23", "john 15:23", "park 09:59"}, "15:01 19:59"))); // ["bob", "bill", "john"]
        System.out.println(Arrays.toString(T.solution14(new String[]{"cody 14:20", "luis 10:12", "alice 15:40", "tom 15:20", "daniel 14:50"}, "14:20 15:20"))); // ["cody", "tom", "daniel"]
    }
}