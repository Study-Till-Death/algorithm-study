package com.algorithm;

public class a37 {
    int n;
    int[][] cans;
    int minDiff = Integer.MAX_VALUE;

    public int solution(int[][] cans){
        this.n = cans.length;
        this.cans = cans;
        this.minDiff = Integer.MAX_VALUE; // 초기화

        boolean[] selected = new boolean[n];
        combine(0, 0, selected);

        return minDiff;
    }

    // 흰돌로 선택할 인덱스 n/2개 선택
    private void combine(int idx, int count, boolean[] selected) {
        if (count == n / 2) { // n/2개 선택 완료
            int whiteSum = 0;
            int blackSum = 0;

            for (int i = 0; i < n; i++) {
                if (selected[i]) whiteSum += cans[i][0]; // 흰 돌로 선택된 사람
                else blackSum += cans[i][1];             // 검은 돌로 선택된 사람
            }

            int diff = Math.abs(whiteSum - blackSum);
            minDiff = Math.min(minDiff, diff); // 흰 돌 검은 돌 능력치 차 기존 minDiff와 비교하여 저장
            return;
        }

        if (idx == n) return;

        // 현재 idx를 선택 (흰돌)
        selected[idx] = true;
        combine(idx + 1, count + 1, selected);

        // 현재 idx를 선택 안 함 (검은 돌)
        selected[idx] = false;
        combine(idx + 1, count, selected);
    }

    public static void main(String[] args){
        a37 T = new a37();
        System.out.println(T.solution(new int[][]{{87, 84}, {66, 78}, {94, 94}, {93, 87}, {72, 92}, {78, 63}}));
        System.out.println(T.solution(new int[][]{{10, 20}, {15, 25}, {35, 23}, {55, 20}}));
        System.out.println(T.solution(new int[][]{{11, 27}, {16, 21}, {35, 21}, {52, 21}, {25, 33},{25, 32}, {37, 59}, {33, 47}}));
    }
}
