package com.example.algorithmstudy.week3;

public class MyAnswer007 {
    public int solution(int[] keypad, String password){
        int answer = 0;

        int [][] board = new int[3][3];
        int x = 0;
        int y = 0;

        for (int i = 0; i < keypad.length; i++) {
            board[i/3][i%3] = keypad[i];
        }
        int[] passwordArray = String.valueOf(password)
                .chars()
                .map(c -> c - '0')
                .toArray();

        for (int k = 0; k < passwordArray.length; k++){
            for (int i = 0; i < board.length; i++){
                for (int j = 0; j < board[i].length; j++){
                    if (passwordArray[k] == board[i][j]){
                        //동일한 위치는 0초
                        if(x == i && y == j){
                            continue;
                        }
                        if( Math.abs(x - i) > 1 || Math.abs(y - j) > 1){
                            x = i;
                            y = j;
                            //처음의 경우는 +안함(시작 위치)
                            if(k != 0){
                                answer = answer + 2;
                            }
                        }else {
                            x = i;
                            y = j;
                            //처음의 경우는 +안함(시작 위치)
                            if(k != 0){
                                answer = answer + 1;
                            }
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer007 T = new MyAnswer007();
        System.out.println(T.solution(new int[]{2, 5, 3, 7, 1, 6, 4, 9, 8}, "7596218"));
        System.out.println(T.solution(new int[]{1, 5, 7, 3, 2, 8, 9, 4, 6}, "63855526592"));
        System.out.println(T.solution(new int[]{2, 9, 3, 7, 8, 6, 4, 5, 1}, "323254677"));
        System.out.println(T.solution(new int[]{1, 6, 7, 3, 8, 9, 4, 5, 2}, "3337772122"));
    }
}
