package com.algorithm;

public class a19_dermatology {
    public int solution(int[] laser, String[] enter){
        int answer = 0; // 동시대기자수
        int waitInfo[][] = new int[21][60]; // 20시 59분까지 각 시,분당 병원에 있는 사람 수
        int end = 900; // 레이저 끝나는 시간

        for (int i = 0; i < enter.length; i++) {
            String[] enterInfo = enter[i].split(" ");
            String[] timeInfo = enterInfo[0].split(":");
            int hour= Integer.parseInt(timeInfo[0]); // 입장시
            int minutes= Integer.parseInt(timeInfo[1]); //입장분
            int duration= laser[Integer.parseInt(enterInfo[1])]; //레이저 받는 시간
            int time = hour*100+minutes; //end랑 비교할 입장시간

            if (time>=end) { //현재 대기자 없는 경우
                end = (hour+duration/60)*100+(minutes+duration%60); //입장자 입장시간+레이저 받는 시간
            } else { // 대기자 있는 경우
                for (int j = 0; j < duration; j++) { //레이저 받는 시간동안 병원에 있는 사람 갱신
                    if ((minutes+j)%60==0 && minutes!=0) {
                        hour++;
                        minutes-=60;
                    }
                    if (hour*100+minutes==end) break; //대기 끝
                    waitInfo[hour][minutes+j]+=1; //대기자 갱신
                    answer=(answer<waitInfo[hour][minutes+j]) ? waitInfo[hour][minutes+j]:answer; //최대대기자수 비교
                }
            }
        }

        return answer;
    }

    public static void main(String[] args){
        a19_dermatology T = new a19_dermatology();
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "11:10 2"}));
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "15:10 0", "15:20 3", "15:22 1", "15:23 0", "15:25 0"}));
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:20 1", "10:40 1", "11:00 1", "11:20 1", "11:40 1"}));
    }
}
