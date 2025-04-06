package com.example.algorithmstudy.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class SongAG {
    public int solution(int s, int e) {
        int answer = 0;
        // 매 초마다 갈 수 있는 곳을 HashSet 으로 정리한 걸 queue 에 담아서 진행
        // 매 초마다로 구별해야하니 무분별하게 담지말고 HashSet 여러개로 담아야할듯 (초마다 구분) , 중복값 안들어가게
        Queue<HashSet<Integer>> q = new LinkedList<>();
        // 0초 기준 출발지만 넣음
        HashSet<Integer> zeroSecond = new HashSet<>();
        zeroSecond.add(s);
        q.add(zeroSecond);
        while (!q.isEmpty()) {
            HashSet<Integer> temp = q.poll();
            HashSet<Integer> nextSecond = new HashSet<>();
            for (Integer i : temp) {
                if (i == e) {
                    return answer;
                }
                nextSecond.add(i + 1);
                if (i >= 1) {
                    nextSecond.add(i - 1);
                }
                nextSecond.add(i * 2);
            }
            q.add(nextSecond);
            e += ++answer; // 초 증가시키고 그만큼 소 이동
            if (e > 200000) { // 20만 넘음 못잡여
                return -1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        SongAG T = new SongAG();
        System.out.println(T.solution(1, 11));
        System.out.println(T.solution(10, 3));
        System.out.println(T.solution(1, 34567));
        System.out.println(T.solution(5, 6));
        System.out.println(T.solution(2, 54321));
    }
}
