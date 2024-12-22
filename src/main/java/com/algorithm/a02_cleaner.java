package com.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class a02_cleaner {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board; // 지도 정보

        System.out.println("초시간 입력 =>");
        int n = Integer.parseInt(br.readLine()); //초시간
        System.out.println("지도 정보 입력 =>");
        String boardInfo = br.readLine(); //지도 정보
        System.out.println();
        board = getBoardArr(boardInfo);
        System.out.println(Arrays.toString(getBoardResult(n,board)));
    }

    static int[][] getBoardArr(String boardInfo) {
        boardInfo = boardInfo.replaceAll("[\\s]", ""); //공백 제거
        boardInfo = boardInfo.substring(2, boardInfo.length() - 2); //앞뒤 대괄호 제거
        String[] boardInfoArr = boardInfo.split("\\],\\["); // ],[ 기준 split
        List<int[]> list = new ArrayList<>();
        for (String info : boardInfoArr) {
            String[] numbers = info.split(",");
            int[] row = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                row[i] = Integer.parseInt(numbers[i]);
            }
            list.add(row);
        }
        return list.toArray(new int[0][]);
    }
    static int[] getBoardResult(int n, int[][] board) {
        int width = board[0].length;
        int height = board.length;
        int way = 1; // 0:북 1:동 2:남 3:서
        int[] location = {0, 0}; // 현재 위치
        for (int i = 1; i <= n; i++) {
            if (way==1) {
                if (location[1]+1<width && board[location[0]][location[1]+1]==0) location[1] += 1;
                else way++;
            } else if (way==2) {
                if (location[0]+1<height && board[location[0]+1][location[1]]==0) location[0] += 1;
                else way++;
            } else if (way==3) {
                if (location[1]-1>=0 && board[location[0]][location[1]-1]==0) location[1] -= 1;
                else way-=3;
            } else if (way==0) {
                if (location[0]-1>=0 && board[location[0]-1][location[1]]==0) location[0] -= 1;
                else way++;
            }
        }
        return location;
    }
}
