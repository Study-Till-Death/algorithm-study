package com.example.algorithmstudy.week5;
import java.util.*;

import static java.lang.Integer.parseInt;

public class MyAnswer014 {
    public String[] solution(String[] reports, String times){
        List<String> answerList = new ArrayList<>();
        //문서 사라진 시간 시작
        String startTime = times.split(" ")[0].replace(":","");
        //문서 사라진 시간 끝
        String endTime = times.split(" ")[1].replace(":","");

        //입장한 엘리트들 시간 순으로 먼저 정렬 해버림
        Arrays.sort(reports, (o1, o2) -> {
            String time1 = o1.split(" ")[1].replace(":", "");
            String time2 = o2.split(" ")[1].replace(":", "");
            return Integer.compare(Integer.parseInt(time1), Integer.parseInt(time2));
        });

        //사라진 시간 시작, 끝 이용해서 그 사이에 해당 하는 엘리트만 arrayList에 add
        for (String report : reports ) {
            String elite = report.split(" ")[0];
            String time = report.split(" ")[1].replace(":","");
            if(parseInt(time) <= parseInt(endTime) && parseInt(time) >= parseInt(startTime)){
                answerList.add(elite);
            }
        }

        //배열로 변환 후 반환
        return answerList.toArray(new String[0]);
    }

    public static void main(String[] args){
        MyAnswer014 T = new MyAnswer014();
        System.out.println(Arrays.toString(T.solution(new String[]{"john 15:23", "daniel 09:30", "tom 07:23", "park 09:59", "luis 08:57"}, "08:33 09:45")));
        System.out.println(Arrays.toString(T.solution(new String[]{"ami 12:56", "daniel 15:00", "bob 19:59", "luis 08:57", "bill 17:35", "tom 07:23", "john 15:23", "park 09:59"}, "15:01 19:59")));
        System.out.println(Arrays.toString(T.solution(new String[]{"cody 14:20", "luis 10:12", "alice 15:40", "tom 15:20", "daniel 14:50"}, "14:20 15:20")));
    }
}
