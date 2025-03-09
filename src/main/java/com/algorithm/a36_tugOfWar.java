package com.algorithm;

import java.util.HashSet;
import java.util.Set;

public class a36_tugOfWar {
    static int answer = 0;

    public int solution(int[][] fight) {
        answer = 0;
        boolean[][] relation = new boolean[7][7];

        // 싸운 정보 기록
        for (int[] f : fight) {
            relation[f[0] - 1][f[1] - 1] = true;
            relation[f[1] - 1][f[0] - 1] = true; // 쌍방으로 기록해야 하므로..
        }


        findTeam(new HashSet<>(), relation, -1);

        return answer;
    }

    private static void findTeam(Set<Integer> team, boolean[][] fight, int lastStudent) {
        if (team.size() == 7) { // 7명 다 모았으면 answer++
            answer++;
            return;
        }

        for (int i = 0; i < 7; i++) {
            if (team.contains(i)) continue; // 이미 포함된 경우 건너뜀

            // 바로 앞 학생과 싸운 적이 있으면 안함
            if (lastStudent != -1 && fight[lastStudent][i]) continue;

            team.add(i);
            findTeam(team, fight, i); // 현재 학생을 마지막 학생으로 설정 후 재귀
            team.remove(i); // 되돌아감
        }
    }

    public static void main(String[] args) {
        a36_tugOfWar T = new a36_tugOfWar();
        System.out.println(T.solution(new int[][]{{1, 3}, {5, 7}, {4, 2}}));
        System.out.println(T.solution(new int[][]{{3, 2}, {3, 5}, {5, 2}, {7, 3}}));
        System.out.println(T.solution(new int[][]{{1, 2}, {1, 5}, {1, 7}, {1, 3}}));
        System.out.println(T.solution(new int[][]{{1, 7}}));
        System.out.println(T.solution(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}}));
    }
}
