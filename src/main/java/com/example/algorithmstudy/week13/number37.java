package com.example.algorithmstudy.week13;

// 바둑대회
class Solution37 {
    static int answer;

    public int solution37(int[][] cans){
        int N = cans.length;
        answer = Integer.MAX_VALUE;

        // 모든 경우 탐색
        combination(cans, new boolean[N], 0, 0, N / 2);

        return answer;
    }

    private void combination(int[][] cans, boolean[] selected, int idx, int count, int target) {
        if (count == target) {
            // 팀 능력치 계산
            int whiteTeam = 0, blackTeam = 0;
            for (int i = 0; i < cans.length; i++) {
                if (selected[i]) {
                    whiteTeam += cans[i][0]; // 흰 돌
                } else {
                    blackTeam += cans[i][1]; // 검은 돌
                }
            }
            answer = Math.min(answer, Math.abs(whiteTeam - blackTeam));
            return;
        }

        if (idx >= cans.length) return;

        // 흰 돌에 포함해서 탐색
        selected[idx] = true;
        combination(cans, selected, idx + 1, count + 1, target);

        // 검은 돌에 포함해서 탐색
        selected[idx] = false;
        combination(cans, selected, idx + 1, count, target);
    }

    public static void main(String[] args){
        Solution37 T = new Solution37();
        System.out.println(T.solution37(new int[][]{{87, 84}, {66, 78}, {94, 94}, {93, 87}, {72, 92}, {78, 63}}));
        System.out.println(T.solution37(new int[][]{{10, 20}, {15, 25}, {35, 23}, {55, 20}}));
        System.out.println(T.solution37(new int[][]{{11, 27}, {16, 21}, {35, 21}, {52, 21}, {25, 33},{25, 32}, {37, 59}, {33, 47}}));
    }
}
