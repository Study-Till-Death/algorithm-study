package com.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class a03_lostDog {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board; // 지도 정보

        System.out.println("지도 정보 입력 =>");
        String boardInfo = br.readLine(); //지도 정보
        System.out.println();
        board = getBoardArr(boardInfo);
        System.out.println(getBoardResult(board));
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
    static int getBoardResult(int[][] board) {
        int[] dog = new int[2];
        int[] hyun = new int[2];
        int cnt=0;
        for (int i = 0; i < board.length; i++) { // 현수, 강아지 위치
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]==2) {
                    hyun[0] = i;
                    hyun[1] = j;
                } else if (board[i][j]==3) {
                    dog[0] = i;
                    dog[1] = j;
                }
            }
        }

        int hyunWay = 0; // 0:북 1:동 2:남 3:서
        int dogWay = 0; // 0:북 1:동 2:남 3:서

        while (10000>cnt) {
            int[] temp = getLocation(hyun,hyunWay,board); //한 칸 이동 결과
            int[] temp2 = getLocation(dog,dogWay,board);  //한 칸 이동 결과
            if (hyun[0]==temp[0] && hyun[1]==temp[1]) {
                if (hyunWay!=3) hyunWay++;
                else hyunWay=0;
            }
            if (dog[0]==temp2[0] && dog[1]==temp2[1]) {
                if (dogWay!=3) dogWay++;
                else dogWay=0;
            }
            cnt++;
            if (hyun[0]==dog[0] && hyun[1]==dog[1]) break;
        }

        return cnt;
    }

    static int[] getLocation(int[] location, int way, int[][] board) {
        if (way==1) {
            if (location[1]+1<10 && board[location[0]][location[1]+1]!=1) location[1] += 1;
        } else if (way==2) {
            if (location[0]+1<10 && board[location[0]+1][location[1]]!=1) location[0] += 1;
        } else if (way==3) {
            if (location[1]-1>=0 && board[location[0]][location[1]-1]!=1) location[1] -= 1;
        } else if (way==0) {
            if (location[0]-1>=0 && board[location[0]-1][location[1]]!=1) location[0] -= 1;
        }

        return location;
    }
}
