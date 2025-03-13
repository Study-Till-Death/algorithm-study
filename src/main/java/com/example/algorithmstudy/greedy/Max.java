package com.example.algorithmstudy.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Max {
    public int solution(int n, int[][] trans, int[][] bookings) {
        int answer = 0;
        // 대충 금방 내리는 애들 먼저 태우면 될 지도
        // 각 역마다 최대 탑승수 정리
        // 0번 index 안쓸거임 고로 +1
        int[] maxCounts = new int[n + 1];
        for (int[] tran : trans) {
            int start = tran[0];
            int end = tran[1];
            int count = tran[2];
            for (int i = start; i <= end; i++) {
                maxCounts[i] += count;
            }
        }
        // 그냥 타고 얼마 안가서 내리는 사람 우선 적용
        bookings = Arrays.stream(bookings).sorted((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(b[1], a[1])).toArray(int[][]::new);
        // 각 역에서 출발 시 출발 가능 인원 중 가낭 적은 거리 이용하는 사람만 체크할 예정

        // 현재역
        int now = 1;
        int bookIndex = 0; // queue 로 짤껄 단순 중복 방지용
        // 역 끝날 때까지
        LinkedList<Integer> nums = new LinkedList<>();
        int ix = 0;
        for(int i = 1; i <= n; i++){
            while(!nums.isEmpty() && nums.peek() == i){
                answer++;
                nums.pollFirst();
            }
            while(ix < bookings.length && bookings[ix][0] == i){
                nums.add(bookings[ix][1]);
                ix++;
            }
            Collections.sort(nums);
            while(nums.size() > maxCounts[i]){
                nums.pollLast();
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Max T = new Max();
        System.out.println(T.solution(5, new int[][]{{1, 4, 2}, {2, 5, 1}}, new int[][]{{1, 2}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {2, 5}, {3, 5}, {3, 4}}));
        System.out.println(T.solution(5, new int[][]{{2, 3, 1}, {1, 5, 1}}, new int[][]{{2, 5}, {1, 5}, {1, 3}, {2, 4}, {2, 5}, {2, 3}}));
        System.out.println(T.solution(8, new int[][]{{1, 8, 3}, {3, 8, 1}}, new int[][]{{1, 3}, {5, 8}, {2, 7}, {3, 8}, {2, 7}, {2, 8}, {3, 8}, {6, 8}, {7, 8}, {5, 8}, {2, 5}, {2, 7}, {3, 7}, {3, 8}}));
        System.out.println(T.solution(9, new int[][]{{1, 8, 3}, {3, 9, 2}, {1, 5, 3}}, new int[][]{{1, 9}, {5, 8}, {2, 9}, {3, 8}, {2, 9}, {1, 9}, {8, 9}, {3, 9}, {1, 8}, {6, 8}, {7, 8}, {5, 8}, {3, 5}, {3, 7}, {4, 7}, {5, 8}}));
        System.out.println(T.solution(9, new int[][]{{2, 7, 2}, {3, 9, 2}, {1, 5, 3}}, new int[][]{{1, 9}, {4, 8}, {2, 9}, {5, 9}, {3, 8}, {2, 9}, {1, 9}, {8, 9}, {3, 9}, {1, 8}, {6, 8}, {3, 6}, {7, 8}, {5, 8}, {3, 5}, {2, 7}, {1, 7}, {2, 8}}));
    }
}
