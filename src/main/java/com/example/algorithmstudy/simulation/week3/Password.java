package com.example.algorithmstudy.simulation.week3;

public class Password {
    public int solution(int[] keypad, String password){
        int answer = 0;

        // 상남자 특 다 만듬
        // 각 자리에서 상대자리로 이동시 걸리는 시간 int[4][3] 이면 5에서 4로 가는데 걸리는 시간 -> 1
        int[][] time = {
                {0,1,2,1,1,2,2,2,2},
                {1,0,1,1,1,1,2,2,2},
                {2,1,0,2,1,1,2,2,2},
                {1,1,2,0,1,2,1,1,2},
                {1,1,1,1,0,1,1,1,1},
                {2,1,1,2,1,0,2,1,1},
                {2,2,2,1,1,2,0,1,2},
                {2,2,2,1,1,1,1,0,1},
                {2,2,2,2,1,1,2,1,0}
        };

        int[] normalizedKeypad = new int[9];
        // 키패드 정상화
        // 변형된 키패드 숫자를 넣으면 그 키패드의 원래 숫자 반환
        // ex 253716498 -> 142605387(index 맞게 -1 해줌) 입력시 012345678 뱉는 정상화 키패드 생성
        for(int i =0; i < keypad.length ; i++){
            normalizedKeypad[keypad[i]-1] = i;
        }

        // 비밀번호 정상화 -> 앞서 만든 정상화 키패드에 비밀번호 입력하여 각 숫자의 원래 위치값  정상화된 비밀번호 생성
        StringBuilder normalizedPassword = new StringBuilder();
        for(int i = 0; i < password.length(); i++){
            normalizedPassword.append(normalizedKeypad[Character.getNumericValue(password.charAt(i))-1]+1);
        }
        password = normalizedPassword.toString();

        for (int i = 0; i < password.length()-1; i++){
            answer += time[Character.getNumericValue(password.charAt(i))-1][Character.getNumericValue(password.charAt(i+1))-1];
        }

        return answer;
    }

    public static void main(String[] args){
        Password T = new Password();
        System.out.println(T.solution(new int[]{2, 5, 3, 7, 1, 6, 4, 9, 8}, "7596218"));
        System.out.println(T.solution(new int[]{1, 5, 7, 3, 2, 8, 9, 4, 6}, "63855526592"));
        System.out.println(T.solution(new int[]{2, 9, 3, 7, 8, 6, 4, 5, 1}, "323254677"));
        System.out.println(T.solution(new int[]{1, 6, 7, 3, 8, 9, 4, 5, 2}, "3337772122"));
    }
}
