package com.algorithm;

import java.util.*;

public class a20_cpu {
    // 대기 상태에 있는 애들을 1. 소요시간이 짧은 순 2. 인덱스가 적은 순 으로 인덱스를 출력한다.
    public int[] solution(int[][] tasks){
        int[] answer = new int[tasks.length];
        List<int[]> input = new ArrayList<>();
        int end = 0; // 시간소요 후의 현재시간 값을 넣을 변수
        int cnt = 0;

        for (int i = 0; i < tasks.length; i++) {
            int[] temp = new int[]{tasks[i][0],tasks[i][1],i}; // 정렬 후엔 기존 인덱스 알 수 없으므로 추가 해준다.
            input.add(temp); // List로 변경
        }

        Collections.sort(input, Comparator.comparingInt((int[] o) -> o[1]).thenComparingInt(o -> o[2])); // 1차 소요시간 순으로 오름차순 정렬, 2차 인덱스 순으로 오름차순 정렬

        while (true) {
            for (int i = 0; i < input.size(); i++) { // input을 전부 대기상태라고 생각하고 input 중 현재시간보다 시작시간이 짧은 아이들을 찾음
                if (input.get(i)[0]<=end) {
                    answer[cnt]=input.get(i)[2]; // answer 최신 index에 input의 task시절 인덱스 넣음
                    cnt++; // answer index 갱신
                    end+=input.get(i)[1]; // 현재시간 갱신(현재시간+찾은 task의 소요시간)
                    input.remove(i); // 처리한 task 대기리스트에서 삭제
                    break;
                }
                if (i==input.size()-1) end++; // input중 task시작시간이 end보다 작은 아이들이 없을 경우 end시간을 증가해준다.
            }
            if (cnt==tasks.length) break; // 인덱스를 모두 집어 넣었으면 break
        }

        return answer;
    }

    public static void main(String[] args){
        a20_cpu T = new a20_cpu();
        System.out.println(Arrays.toString(T.solution(new int[][]{{2, 3}, {1, 2}, {8, 2}, {3, 1}, {10, 2}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{5, 2}, {7, 3}, {1, 3}, {1, 5}, {2, 2}, {1, 1}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{1, 2}, {2, 3}, {1, 3}, {3, 3}, {8, 2}, {1, 5}, {2, 2}, {1, 1}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{999, 1000}, {996, 1000}, {998, 1000}, {999, 7}})));
    }
}
