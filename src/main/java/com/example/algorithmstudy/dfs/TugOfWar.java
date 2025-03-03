package com.example.algorithmstudy.dfs;

public class TugOfWar {
    private boolean[][] fightArr;
    private int answer;
    private int maxDepth;

    public int solution(int[][] fight) {
        // 역방향도 적용해야 하니
        // 학생 7명 고정
        int studentNumber = 7;
        fightArr = new boolean[studentNumber + 1][studentNumber + 1];

        for (int[] ints : fight) {
            int s1 = ints[0];
            int s2 = ints[1];
            fightArr[s1][s2] = true;
            fightArr[s2][s1] = true;
        }
        answer = 0;
        maxDepth = studentNumber;
        boolean[] isUseds = new boolean[studentNumber + 1];

        dfs(0, isUseds,0);

        return answer;
    }

    private void dfs(int depth, boolean[] isUsed, int lastUsedNumber) {
        if (depth == maxDepth) {
            answer++;
            return;
        }
        for (int i = 1; i <= maxDepth; i++) {
            if (isUsed[i] || fightArr[lastUsedNumber][i]) {
                continue;
            }
            isUsed[i] = true;
            dfs(depth + 1, isUsed, i);
            isUsed[i] = false;
        }
    }


    public static void main(String[] args) {
        TugOfWar T = new TugOfWar();
        System.out.println(T.solution(new int[][]{{1, 3}, {5, 7}, {4, 2}}));
        System.out.println(T.solution(new int[][]{{3, 2}, {3, 5}, {5, 2}, {7, 3}}));
        System.out.println(T.solution(new int[][]{{1, 2}, {1, 5}, {1, 7}, {1, 3}}));
        System.out.println(T.solution(new int[][]{{1, 7}}));
        System.out.println(T.solution(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}}));
    }
}
