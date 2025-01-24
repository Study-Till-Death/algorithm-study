package com.example.algorithmstudy.week7;
import java.util.*;

public class MyAnswer021 {
    public int solution(int n, int[][] meetings){
        int answer = 0;
        //각 회의실에 몇명 들어갔는지 셀 변수
        int [] countRoom = new int[n];
        //회의 진행중 인 방과 종료 시간
        PriorityQueue<int[]> checkRoomEndTime = new PriorityQueue<>(
                (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0])
        );
        //공실
        TreeSet<Integer> emptyRoom = new TreeSet<>();
        //공실 갯수 n개로 설정
        for(int i = 0; i < n; i++){
            emptyRoom.add(i);
        }
        //회의 시작 시간 순서로 정렬
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        
        for (int[] i : meetings){
            //현재 미팅에 대해 판단할 때 이미 시간이 지나 있으면 기존 진행중 인 방 비우기
            List<int[]> toRemove = new ArrayList<>();
            for (int[] j : checkRoomEndTime) {
                if (j[0] <= i[0]) {
                    toRemove.add(j);
                    emptyRoom.add(j[1]);
                }
            }
            checkRoomEndTime.removeAll(toRemove);

            //룸 비어 있으면 첫번째부터 채워넣기
            if(!emptyRoom.isEmpty()){
                int room = emptyRoom.pollFirst();
                checkRoomEndTime.add(new int[]{i[1], room});
                countRoom[room]++;
            }else{
                //룸 꽉 차 있으면 가장 빨리 끝나는 방부터 채우기
                int[] endMeeting = checkRoomEndTime.poll();
                int room = endMeeting[1];
                //이때는 기존 시간 + 이번 현재 회의 시간을 추가 해야 로직 오류가 없음
                checkRoomEndTime.add(new int[]{i[1] + endMeeting[0], room});
                countRoom[room]++;
            }
        }

        //최대값 가져와서 회의실 번호 뽑아내기
        int max = 0;
        for(int i = 0; i < n; i++){
            if(countRoom[i] > max){
                max = countRoom[i];
                answer = i;
            }
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer021 T = new MyAnswer021();
        System.out.println(T.solution(2, new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}}));
        System.out.println(T.solution(3, new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
        System.out.println(T.solution(3, new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}}));
        System.out.println(T.solution(4, new int[][]{{3, 20}, {1, 25}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
    }
}
