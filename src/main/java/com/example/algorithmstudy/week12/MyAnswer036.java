package com.example.algorithmstudy.week12;
import java.util.*;

//1~7까지 사람을 나열하는데 fight로 들어온 경우는 서로 이웃하는 배열을 제외한 경우의 수를 반환해야함
public class MyAnswer036 {
    private int count = 0;
    private boolean[][] fightMap = new boolean[8][8];

    public int solution(int[][] fight) {
        count = 0;
        for (boolean[] row : fightMap) Arrays.fill(row, false);

        //싸우는 놈들 만들기
        for (int[] f : fight) {
            fightMap[f[0]][f[1]] = true;
            fightMap[f[1]][f[0]] = true;
        }

        boolean[] visited = new boolean[8];
        dfs(new ArrayList<>(), visited);
        return count;
    }

    //값 넣어보면서 순회하면서 체크
    private void dfs(List<Integer> current, boolean[] visited) {
        if (current.size() == 7) {
            count++;
            return;
        }

        for (int i = 1; i <= 7; i++) {
            if (!visited[i]) {
                if (!current.isEmpty() && fightMap[current.get(current.size() - 1)][i]) {
                    continue;
                }

                visited[i] = true;
                current.add(i);
                dfs(current, visited);
                current.remove(current.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args){
        MyAnswer036 T = new MyAnswer036();
        System.out.println(T.solution(new int[][]{{1, 3}, {5, 7}, {4, 2}}));
        System.out.println(T.solution(new int[][]{{3, 2}, {3, 5}, {5, 2}, {7, 3}}));
        System.out.println(T.solution(new int[][]{{1, 2}, {1, 5}, {1, 7}, {1, 3}}));
        System.out.println(T.solution(new int[][]{{1, 7}}));
        System.out.println(T.solution(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}}));
    }
}
