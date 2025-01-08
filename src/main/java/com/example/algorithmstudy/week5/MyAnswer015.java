package com.example.algorithmstudy.week5;
import java.util.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class MyAnswer015 {
    public String[] solution(String[] reports, int time){
        List<String> answerList = new ArrayList<>();

        Map<String, Integer> enterPerson = new HashMap<>();

        for (int i = 0; i < reports.length; i++ ) {
            //in 인지 체크
            if(Objects.equals(reports[i].split(" ")[2], "in")){
                for (int j = i+1; j < reports.length; j++ ) {
                    //in 이후 다음 동일 이름이 나오면 무조건 out
                    if((reports[i].split(" ")[0]).equals(reports[j].split(" ")[0])) {
                        //in 일때 시간
                        LocalTime start = LocalTime.parse(reports[i].split(" ")[1]);
                        //out 일때 시간
                        LocalTime end = LocalTime.parse(reports[j].split(" ")[1]);
                        //입장 후 퇴장 시간 간격 계산
                        int minute = (int) ChronoUnit.MINUTES.between(start, end);
                        //해당 사람 key로 시간 간격 추가하면서 입력
                        enterPerson.put(reports[i].split(" ")[0], enterPerson.getOrDefault(reports[i].split(" ")[0], 0) + minute);
                        //한번 in 만나서 여기로 들어왔으면 out 만났으니 바로 반복 종료
                        break;
                    }
                }
            }
        }

        //입력된 사람, 시간 으로 time 초과 했는지 확인 후 초과시 리스트에 추가
        for (Map.Entry<String, Integer> entry : enterPerson.entrySet()) {
            if(entry.getValue() > time) {
                answerList.add(entry.getKey());
            }
        }

        return answerList.toArray(new String[0]);
    }

    public static void main(String[] args){
        MyAnswer015 T = new MyAnswer015();
        System.out.println(Arrays.toString(T.solution(new String[]{"john 09:30 in", "daniel 10:05 in", "john 10:15 out", "luis 11:57 in", "john 12:03 in", "john 12:20 out", "luis 12:35 out", "daniel 15:05 out"}, 60)));
        System.out.println(Arrays.toString(T.solution(new String[]{"bill 09:30 in", "daniel 10:00 in", "bill 11:15 out", "luis 11:57 in", "john 12:03 in", "john 12:20 out", "luis 14:35 out", "daniel 14:55 out"}, 120)));
        System.out.println(Arrays.toString(T.solution(new String[]{"cody 09:14 in", "bill 09:25 in", "luis 09:40 in", "bill 10:30 out", "cody 10:35 out", "luis 10:35 out", "bill 11:15 in", "bill 11:22 out", "luis 15:30 in", "luis 15:33 out"}, 70)));
        System.out.println(Arrays.toString(T.solution(new String[]{"chato 09:15 in", "emilly 10:00 in", "chato 10:15 out", "luis 10:57 in", "daniel 12:00 in", "emilly 12:20 out", "luis 11:20 out", "daniel 15:05 out"}, 60)));
    }
}
