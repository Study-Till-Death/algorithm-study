package com.example.algorithmstudy.lessons007;

import java.util.*;

class Solution {
    public int solution(int[] keypad, String password){
        int answer = 0;
        int keypadSize = 3;
        int[][] tenKeypad = new int[keypadSize][keypadSize];
        int[][] distances = new int[keypad.length+1][keypad.length+1];
        int[] intPassword = new int[password.length()];

        for(int i = 0; i < password.length(); i++){
            intPassword[i] = Character.getNumericValue(password.charAt(i));
        }

        //3*3 키패드로 바꾸기
        for(int i = 0; i < keypad.length; i++){
            tenKeypad[i/keypadSize][i%keypadSize] = keypad[i];
        }

        //각 숫자의 입장에서 다른 숫자와의 거리 알아내기
        for(int i = 0; i < keypad.length; i++){
            int x = 0;
            int y = 0;

            //숫자 본인의 좌표 알아내기
            outerLoop :
            for(int j = 0; j < keypadSize; j++){
                for(int k = 0; k < keypadSize; k++){
                    if(tenKeypad[j][k] == keypad[i]){
                        x = j;
                        y = k;
                        break outerLoop;
                    }
                }
            }

            //다른 숫자와의 거리 알아내기
            for(int j = 0; j < keypadSize; j++){
                for(int k = 0; k < keypadSize; k++){
                    int xDistance = Math.abs(x-j);
                    int yDistance = Math.abs(y-k);

                    //숫자 keypad[i]와 tenKeypad[j][k]의 거리는 Math.max(xDistance, yDistance)
                    distances[keypad[i]][tenKeypad[j][k]] = Math.max(xDistance, yDistance);
                }
            }
        }

        //시작은 비밀번호의 첫번째 숫자에서
        int focus = intPassword[0];
        for(int word : intPassword){
            answer+=distances[focus][word];
            focus = word;
        }

        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{2, 5, 3, 7, 1, 6, 4, 9, 8}, "7596218"));
        System.out.println(T.solution(new int[]{1, 5, 7, 3, 2, 8, 9, 4, 6}, "63855526592"));
        System.out.println(T.solution(new int[]{2, 9, 3, 7, 8, 6, 4, 5, 1}, "323254677"));
        System.out.println(T.solution(new int[]{1, 6, 7, 3, 8, 9, 4, 5, 2}, "3337772122"));
    }
}
