package com.example.algorithmstudy.dfs;

public class MaxNumber {

    private int answer;
    private int minimum;
    private int maxDepth;

    public int solution(int n) {
        answer = Integer.MAX_VALUE;
        // 이문젠  dfs 안해도 될 거 같은데
        // 일단 의도대로 풀이

        // int 숫자 1개씩 구분해서 배열화 + math.min 해서 최솟값 구하기
        minimum = n;
        int[] arr = String.valueOf(n).chars().map(c -> c - '0').toArray();
        boolean[] isUsed = new boolean[arr.length];
        maxDepth = arr.length;
        dfs(arr, 0, 0, isUsed);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private void dfs(int[] arr, int now, int depth, boolean[] isUsed) {
        if (depth == maxDepth) {
            if (now > minimum) {
                answer = Math.min(now, answer);
            }
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (isUsed[i]) {
                continue;
            }
            int next = 10 * now + arr[i];

            isUsed[i] = true;
            dfs(arr, next, depth + 1, isUsed);
            isUsed[i] = false;
        }
    }


    public static void main(String[] args) {
        MaxNumber T = new MaxNumber();
        System.out.println(T.solution(123));
        System.out.println(T.solution(321));
        System.out.println(T.solution(20573));
        System.out.println(T.solution(27711));
        System.out.println(T.solution(54312));
    }
}
