package com.example.algorithmstudy.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IP {
    int N;
    List<String> answer;
    String ip;
    public String[] solution(String s){
        // 점 어디 찍는지로 하면 될 듯?
        // 점은 3개 찍을 거고
        // N자리면 N-1 만큼의 자리가 있음
        ip = s;
        N = s.length()-1;
        answer = new ArrayList<String>();
        int[] isPoint = new int[3];
        dfs(0,isPoint);
        return answer.toArray(new String[answer.size()]);
    }

    private void dfs(int depth, int[] isPoint) {
        if (depth == 3) {
            checkIsGoodAddress(isPoint);
            return;
        }
        // 기본적으로 1번부터인디 가장최근 점 찍은 곳 있으면 그 다음부터 고려
        int startIdx = getStartIndex(depth, isPoint);
        for (int i = startIdx ; i <= N; i++) { // N번째 자리까지 전부 고려
            isPoint[depth] = i;
            dfs(depth+1, isPoint);
            isPoint[depth] = 0;
        }
    }

    private static int getStartIndex(int depth, int[] isPoint) {
        int startIdx = 1;
        if (depth > 0 ){
            startIdx = isPoint[depth -1] +1;
        }
        return startIdx;
    }

    private void checkIsGoodAddress(int[] isPoint) {
        StringBuilder sb = new StringBuilder(ip);
        // 뒤에서부터 .을 삽입 (뒤에서부터 삽입해야 인덱스가 밀리지 않음)
        for (int i = isPoint.length - 1; i >= 0; i--) {
            sb.insert(isPoint[i], ".");
        }
        String str = sb.toString();
        String[] split = str.split("\\.");

        boolean isGood = true;
        for (String s : split) {
            if (s.length() >= 4 ||
                    Integer.valueOf(s) > 255 ||
                    (s.startsWith("0") && s.length()>=2)) {
                isGood = false;
                break;
            }
        }
        if (isGood) {
            answer.add(str);
        }
    }

    public static void main(String[] args){
        IP T = new IP();
        System.out.println(Arrays.toString(T.solution("2025505")));
        System.out.println(Arrays.toString(T.solution("0000")));
        System.out.println(Arrays.toString(T.solution("255003")));
        System.out.println(Arrays.toString(T.solution("155032012")));
        System.out.println(Arrays.toString(T.solution("02325123")));
        System.out.println(Arrays.toString(T.solution("121431211")));
    }

}
