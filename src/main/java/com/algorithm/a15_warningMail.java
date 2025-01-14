package com.algorithm;

import java.util.*;
import java.lang.*;
import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class a15_warningMail {
    public String[] solution(String[] reports, int time){
        ArrayList<String> answer = new ArrayList(); // 경고메일 받는 사람 리스트
        HashMap<String, String> inList = new HashMap<>(); // 입장한 사람, 입장 시간 map
        HashMap<String, Integer> timeList = new HashMap<>(); // 입장한 사람, 머무른 시간 map

        for (int i = 0; i < reports.length; i++) {
            String[] report = reports[i].split(" ");
            if (inList.containsKey(report[0])){ // 현재 퇴장하는 경우
                String[] inTime = inList.get(report[0]).split(":");
                int inMinutes = Integer.parseInt(inTime[0])*60+Integer.parseInt(inTime[1]); // 입장시간 분으로 변환
                String[] outTime = report[1].split(":");
                int outMinutes = Integer.parseInt(outTime[0])*60+Integer.parseInt(outTime[1]); // 퇴장시간 분으로 변환
                int timeSum = outMinutes - inMinutes; // 머무른 시간
                
                if (timeList.containsKey(report[0])) { // 입장전적 있는 경우
                    timeSum=timeList.get(report[0])+timeSum; // 과거 머무른 시간 + 이번 회차 머무른 시간
                }
                timeList.put(report[0],timeSum); // 타임리스트에 이름과 머문 시간 put
                if (timeList.get(report[0])>time) answer.add(report[0]); // 머문 시간이 time 이상인 경우 경고메일 리스트에 add
                inList.remove(report[0]); // 퇴장 시 inList에서 삭제
            } else {
                inList.put(report[0],report[1]); // 입장일 경우 inList에 추가
            };
        }
        Collections.sort(answer); // 소트
        return answer.toArray(new String[0]);
    }
    public static void main(String[] args){
        a15_warningMail T = new a15_warningMail();
        System.out.println(Arrays.toString(T.solution(new String[]{"john 09:30 in", "daniel 10:05 in", "john 10:15 out", "luis 11:57 in", "john 12:03 in", "john 12:20 out", "luis 12:35 out", "daniel 15:05 out"}, 60)));
        System.out.println(Arrays.toString(T.solution(new String[]{"bill 09:30 in", "daniel 10:00 in", "bill 11:15 out", "luis 11:57 in", "john 12:03 in", "john 12:20 out", "luis 14:35 out", "daniel 14:55 out"}, 120)));
        System.out.println(Arrays.toString(T.solution(new String[]{"cody 09:14 in", "bill 09:25 in", "luis 09:40 in", "bill 10:30 out", "cody 10:35 out", "luis 10:35 out", "bill 11:15 in", "bill 11:22 out", "luis 15:30 in", "luis 15:33 out"}, 70)));
        System.out.println(Arrays.toString(T.solution(new String[]{"chato 09:15 in", "emilly 10:00 in", "chato 10:15 out", "luis 10:57 in", "daniel 12:00 in", "emilly 12:20 out", "luis 11:20 out", "daniel 15:05 out"}, 60)));
    }
}
