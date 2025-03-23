package com.example.algorithmstudy.dfs;

public class Go {
    int answer;
    int N;

    public int solution(int[][] cans) {
        answer = Integer.MAX_VALUE;
        N = cans.length;
        boolean[] isWhite = new boolean[N];
        // 절반 흰돌 골라서 한다는 뜻
        dfs(0, cans, isWhite);
        return answer;
    }

    private void dfs(int depth, int[][] cans, boolean[] isWhite) {
        if (depth >= N / 2) {
            calculateAnswer(cans, isWhite);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (isWhite[i]) {
                continue;
            }
            isWhite[i] = true;
            dfs(depth + 1, cans, isWhite);
            isWhite[i] = false;
        }
    }

    private void calculateAnswer(int[][] cans, boolean[] isWhite) {
        int white = 0;
        int black = 0;
        for (int i = 0; i < N; i++) {
            if (isWhite[i]) {
                white += cans[i][0];
            } else {
                black += cans[i][1];
            }
        }
        answer = Math.min(answer, Math.abs(white - black));
    }

    public static void main(String[] args) {
        Go T = new Go();
        System.out.println(T.solution(new int[][]{{87, 84}, {66, 78}, {94, 94}, {93, 87}, {72, 92}, {78, 63}}));
        System.out.println(T.solution(new int[][]{{10, 20}, {15, 25}, {35, 23}, {55, 20}}));
        System.out.println(T.solution(new int[][]{{11, 27}, {16, 21}, {35, 21}, {52, 21}, {25, 33}, {25, 32}, {37, 59}, {33, 47}}));
    }

}
