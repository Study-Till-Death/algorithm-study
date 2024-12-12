package com.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class a01_climbingALadder {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] ladder; // 사다리 정보
        // 1. 1~n까지 반복
        // 2. 사다리 가로줄 개수만큼 반복
        // 3. 가로줄이 존재하는 세로줄배열 안에 시작위치 or 시작위치-1 있을 경우 현재위치 +1/-1 하여 재설정
        // 4. 사다리 정보 반복 완료하면 위치변수에 해당하는 index에 학생(ex.A) 저장

        System.out.println("학생 수 입력 =>");
        int n = Integer.parseInt(br.readLine()); //학생 수
        System.out.println("사다리 정보 입력 =>");
        String ladderInfo = br.readLine(); //사다리 정보
        ladder = getLadderArr(ladderInfo);
        System.out.println(Arrays.toString(getLadderResult(n,ladder)));
    }

    static char getLargeAlphabet(int index) {
        char alphabet = (char)(index + 64);
        return alphabet;
    }

    static int[][] getLadderArr(String ladderInfo) {
        ladderInfo = ladderInfo.replaceAll("\\s", ""); //공백 제거
        ladderInfo = ladderInfo.substring(2, ladderInfo.length() - 2); //앞뒤 대괄호 제거
        String[] ladderInfoArr = ladderInfo.split("\\],\\["); // ],[ 기준 split
        List<int[]> list = new ArrayList<>();
        for (String info : ladderInfoArr) {
            String[] numbers = info.split(",");
            int[] row = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                row[i] = Integer.parseInt(numbers[i]);
            }
            list.add(row);
        }
        return list.toArray(new int[0][]);
    }

    static char[] getLadderResult(int n, int[][] ladder) {
        char[] result = new char[n]; //사다리 result
        int location; // 현재 위치
        for (int i = 1; i <= n; i++) {
            location = i;
            for (int[] line : ladder) {
                for (int l : line) {
                    if (l==location) location++;
                    else if (l==location-1) location--;
                }
            }
            result[location-1] = getLargeAlphabet(i);
        }
        return result;
    }
}
