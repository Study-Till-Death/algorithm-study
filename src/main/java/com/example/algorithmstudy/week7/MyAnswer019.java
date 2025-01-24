package com.example.algorithmstudy.week7;
import java.util.*;

//모든 줄에 주석을 적어봄 ParamDoc까지 써드림
public class MyAnswer019 {
    public int solution(int[] laser, String[] enter){
        int answer = 0;

        //입장 시간
        Stack<Integer> enterTime = new Stack<>();
        //나가는 시간
        Stack<Integer> exitTime = new Stack<>();

        //입장 순서대로 for 문
        for (String enterCase : enter){
            //입장한 시간 String
            String time = enterCase.split(" ")[0];
            //레이저 종류
            String laserCase = enterCase.split(" ")[1];

            //현재 사람 입장 시간
            int currentEnterTime = timeStringToMinute(time);
            //현재 사람 레이저 시간
            int laserDuration = laser[Integer.parseInt(laserCase)];

            //입장 시간 Stack에 변환한 입장 시간 push
            enterTime.push(currentEnterTime);

            //퇴장 시간 존재 여부 확인 및 현재 시간 보다 작은지 확인
            if (exitTime.isEmpty() || exitTime.peek() <= currentEnterTime) {
                //대기 없이 바로 수술 시작
                exitTime.push(currentEnterTime + laserDuration);
            } else {
                //대기 필요
                int startTime = exitTime.peek(); //수술 시작 시간
                exitTime.push(startTime + laserDuration);
            }

            //대기 사람 숫자
            int waitCnt = 0;
            for (int i = 0; i < enterTime.size(); i++) {
                //입장 시간이 현재 시간 보다 작고 퇴장 시간이 현재 시간 보다 높은 경우 대기 사람 숫자 증가
                if (enterTime.get(i) < currentEnterTime && exitTime.get(i) > currentEnterTime) {
                    waitCnt++;
                }
            }
            //대기 사람 숫자 최대값 계산용
            answer = Math.max(answer, waitCnt);
        }

        //PriorityQueue 로 돌리면 더 간단 했을듯
        return answer;
    }

    /**
     * 시간:분 문자열 을 받아서 분으로 return 해주는 함수
     * @param timeString 시간:분 으로 되어 있는 String
     * @return 시간*60 + 분을 한 분 값
     */
    public int timeStringToMinute(String timeString){
        int hour = Integer.parseInt(timeString.split(":")[0]);
        int minute = Integer.parseInt(timeString.split(":")[1]);
        return hour*60 + minute;
    }

    public static void main(String[] args){
        MyAnswer019 T = new MyAnswer019();
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "11:10 2"}));
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "15:10 0", "15:20 3", "15:22 1", "15:23 0", "15:25 0"}));
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:20 1", "10:40 1", "11:00 1", "11:20 1", "11:40 1"}));
    }
}
