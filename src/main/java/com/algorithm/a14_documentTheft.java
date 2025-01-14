package com.algorithm;

import java.util.*;

public class a14_documentTheft {
    public String[] solution(String[] reports, String times){
        TreeMap<Integer, String> range = new TreeMap<>();
        String[] theftTime = times.split(" "); //도난시간

        String[] theftStart = theftTime[0].split(":"); //도난시작
        String[] theftEnd = theftTime[1].split(":"); //도난종료

        int theftStartH = Integer.parseInt(theftStart[0]); // 도난시작시
        int theftStartM = Integer.parseInt(theftStart[1]); // 도난시작분
        int theftEndH = Integer.parseInt(theftEnd[0]); // 도난완료시
        int theftEndM = Integer.parseInt(theftEnd[1]); // 도난완료분

        for (int i = 0; i < reports.length; i++) {
            String[] r = reports[i].split(" ");
            String name = r[0]; // 용의자이름
            String[] entryTime = r[1].split(":");
            int entryH = Integer.parseInt(entryTime[0]); // 입장시
            int entryM = Integer.parseInt(entryTime[1]); // 입장분
            int sortTime = Integer.parseInt(entryTime[0]+entryTime[1]); // 도난의심시간에 입장했을 경우 시간+분으로 소트하려고..

            if ((theftStartH<entryH && entryH <theftEndH) // 도난의심시간에 입장했을 경우 treeMap에 sortTime을 key로 이름 삽입
                ||  (theftStartH==entryH && theftStartM<=entryM)
                    || (theftEndH==entryH && entryM<=theftEndM)) {
                range.put(sortTime, name);
            }
        }
        return  range.values().stream().toArray(String[]::new); // range의 values -> string[]
    }

    public static void main(String[] args){
        a14_documentTheft T = new a14_documentTheft();
        System.out.println(Arrays.toString(T.solution(new String[]{"john 15:23", "daniel 09:30", "tom 07:23", "park 09:59", "luis 08:57"}, "08:33 09:45")));
        System.out.println(Arrays.toString(T.solution(new String[]{"ami 12:56", "daniel 15:00", "bob 19:59", "luis 08:57", "bill 17:35", "tom 07:23", "john 15:23", "park 09:59"}, "15:01 19:59")));
        System.out.println(Arrays.toString(T.solution(new String[]{"cody 14:20", "luis 10:12", "alice 15:40", "tom 15:20", "daniel 14:50"}, "14:20 15:20")));
    }
}
