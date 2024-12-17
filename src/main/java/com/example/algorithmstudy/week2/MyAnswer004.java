package com.example.algorithmstudy.week2;
import java.util.*;

//이 문제는 2 3번 강의를 보고 품
public class MyAnswer004 {
    public int[] solution(int c, int r, int k){
        int[] answer = new int[2];
        if ( k > c * r){
            return answer;
        }

        //방향 지정해주는 변수
        int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        int diretionIndex = 1;
        int seq = 1;
        int x = 0;
        int y = 0;
        int[][] board = new int[c][r];

        while(seq <k){
            int dx = x + direction[diretionIndex][0];
            int dy = y + direction[diretionIndex][1];
            if(dx < 0 || dy < 0 ||  dx >= c || dy >= r || board[dx][dy] == 1){
                //방향을 4로 나누는 나머지 값을 이용해 상하좌우를 판단
                diretionIndex = (diretionIndex + 1) % 4;
                continue;
            }
            board[x][y] = 1;
            seq++;
            x = dx;
            y = dy;
        }
        // 시작지점을 실제로는 0,0 이 아닌 1,1로 시작하므로 1씩 더함
        answer[0] = x+1;
        answer[1] = y+1;
        return answer;
    }

    public static void main(String[] args){
        MyAnswer004 T = new MyAnswer004();
        System.out.println(Arrays.toString(T.solution(6, 5, 12)));
        System.out.println(Arrays.toString(T.solution(6, 5, 20)));
        System.out.println(Arrays.toString(T.solution(6, 5, 30)));
        System.out.println(Arrays.toString(T.solution(6, 5, 31)));
    }
}
