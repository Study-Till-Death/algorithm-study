package com.example.algorithmstudy.lessons037;

import java.util.*;
class Solution {
    boolean[] selected;
    int length;
    int answer = Integer.MAX_VALUE;
    int[][] power;

    public int solution(int[][] cans){

        power = cans;
        length = cans.length;
        selected = new boolean[cans.length];

        dfs(0,0);

        return answer;
    }

    private void dfs(int index, int count) {
        //백돌이 이미 절반 선택되었다면, 결과계산
        if(count == length/2){
            getDiff();
            return;
        }

        //의미없는 경우의 수 제외
        if(index >= length) {
            return;
        }

        //이번이 백돌인 경우
        selected[index] = true;
        dfs(index+1, count+1);

        //이번이 흑돌인 경우
        selected[index] = false;
        dfs(index+1, count);
    }

    private void getDiff() {
        int whiteSum = 0;
        int blackSum = 0;

        for (int i = 0; i < length; i++) {
            if (selected[i]) {
                whiteSum += power[i][0]; // 흰 돌 능력치 합산
            } else {
                blackSum += power[i][1]; // 검은 돌 능력치 합산
            }
        }

        answer = Math.min(answer, Math.abs(whiteSum - blackSum));
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[][]{{87, 84}, {66, 78}, {94, 94}, {93, 87}, {72, 92}, {78, 63}}));
        System.out.println(T.solution(new int[][]{{10, 20}, {15, 25}, {35, 23}, {55, 20}}));
        System.out.println(T.solution(new int[][]{{11, 27}, {16, 21}, {35, 21}, {52, 21}, {25, 33},{25, 32}, {37, 59}, {33, 47}}));
    }
}
