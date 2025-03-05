package com.example.algorithmstudy.dfs;

public class TugOfWar {
    private boolean[][] fightArr;
    private int answer;

    private final int studentNumber = 7; // 학생 7명 고정

    public int solution(int[][] fights) {
        setFightRelations(fights);
        resetAnswer();
        boolean[] isUseds = resetIsUsed();
        dfs(0, isUseds,0);
        return answer;
    }

    private void setFightRelations(int[][] fights) {
        resetFightArray();
        for (int[] fight : fights) {
            int fighter1 = getFirstFighter(fight);
            int fighter2 = getSecondFighter(fight);
            // 역방향도 적용해야 하니
            markFight(fighter1, fighter2);
            markFight(fighter2, fighter1);
        }
    }

    private void resetFightArray() {
        fightArr = new boolean[studentNumber + 1][studentNumber + 1];
    }

    private void markFight(int fighter1, int fighter2) {
        fightArr[fighter1][fighter2] = true;
    }

    private void resetAnswer() {
        answer = 0;
    }

    private boolean[] resetIsUsed() {
        return new boolean[studentNumber + 1];
    }

    private static int getSecondFighter(int[] fight) {
        return fight[1];
    }

    private static int getFirstFighter(int[] fight) {
        return fight[0];
    }

    private void dfs(int depth, boolean[] isUsed, int lastUsedNumber) {
        if (isAllUsed(depth)) {
            answer++;
            return;
        }
        for (int i = 1; i <= studentNumber; i++) {
            if (cannotBeNeighbor(isUsed, lastUsedNumber, i)) {
                continue;
            }
            markAsUsed(isUsed, i);
            dfs(depth + 1, isUsed, i);
            maekAsUnused(isUsed, i);
        }
    }

    private static void maekAsUnused(boolean[] isUsed, int i) {
        isUsed[i] = false;
    }

    private static void markAsUsed(boolean[] isUsed, int i) {
        isUsed[i] = true;
    }

    private boolean cannotBeNeighbor(boolean[] isUsed, int lastUsedNumber, int i) {
        return isUsed[i] || fightArr[lastUsedNumber][i];
    }

    private boolean isAllUsed(int depth) {
        return depth == studentNumber;
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
