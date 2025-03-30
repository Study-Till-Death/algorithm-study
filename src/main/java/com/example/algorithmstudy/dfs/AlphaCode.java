package com.example.algorithmstudy.dfs;

public class AlphaCode {
    int maxDepth;
    int count;
    String code;
    public int solution(String s){
        count = 0;
        code = s;
        maxDepth = s.length();
        dfs(0);

        return count;
    }

    private void dfs (int depth){
        if (depth == maxDepth){
            count++;
            return;
        }
        int firstNum = code.charAt(depth) - '0';
        if (firstNum == 0){ // 첫 숫자가 0이면 안된다
            return;
        }
        if (firstNum >= 3 || depth == maxDepth-1){ // 2자리인지 체크할 필요 없는 애들은 바로 넘김
            dfs(depth+1);
            return;
        }
        int secondNum = code.charAt(depth+1) - '0';
        if (firstNum == 2 && secondNum >= 7){
            return;
        }
        // 여기까지 남은 애들은 다음 숫자가 1,2 이고 다음 두자리가 10~26 까지인 숫자들은 1자리, 2자리일 경우로 나눠서 dfs ㄱㄱ
        dfs(depth+1);
        dfs(depth+2);
    }

    public static void main(String[] args){
        AlphaCode T = new AlphaCode();
        System.out.println(T.solution("25114"));
        System.out.println(T.solution("23251232"));
        System.out.println(T.solution("21020132"));
        System.out.println(T.solution("21350"));
        System.out.println(T.solution("120225"));
        System.out.println(T.solution("232012521"));
    }
}
