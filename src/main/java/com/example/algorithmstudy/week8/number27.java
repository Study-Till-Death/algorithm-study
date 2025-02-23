package com.example.algorithmstudy.week8;

class Solution27 {
    public int solution27(int[] tasks, long k) {
        // (작업시간, 인덱스) 로 저장해서 작업 시간을 기준으로 정렬
        // 남은 작업과 k 비교해서 k에서 차감하다가 소진되면 k % 남은 작업 수 찾아서 반환
        // 작업 모두 처리했는데 k 초과되면 -1 반환

        int n = tasks.length;
        int[][] task = new int[n][2];

        for (int i = 0; i < n; i++) {
            task[i][0] = tasks[i];
            task[i][1] = i + 1;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (task[j][0] > task[j + 1][0]) {
                    int[] temp = task[j];
                    task[j] = task[j + 1];
                    task[j + 1] = temp;
                }
            }
        }

        long time = 0;
        long remain = n;
        int index = 0;

        while (index < n) {
            long minTime = task[index][0];

            long totalTime = (minTime - time) * remain;

            if (k >= totalTime) {
                k -= totalTime;
                time = minTime;

                while (index < n && task[index][0] == minTime) {
                    index++;
                    remain--;
                }
            } else {
                int[][] remainTask = new int[(int) remain][2];
                int pos = 0;

                for (int i = index; i < n; i++) {
                    remainTask[pos][0] = task[i][0];
                    remainTask[pos][1] = task[i][1];
                    pos++;
                }

                for (int i = 0; i < remain - 1; i++) {
                    for (int j = 0; j < remain - i - 1; j++) {
                        if (remainTask[j][1] > remainTask[j + 1][1]) {
                            int[] temp = remainTask[j];
                            remainTask[j] = remainTask[j + 1];
                            remainTask[j + 1] = temp;
                        }
                    }
                }

                return remainTask[(int) (k % remain)][1];
            }
        }

        return -1;
    }

    public static void main(String[] args){
        Solution27 T = new Solution27();
        System.out.println(T.solution27(new int[]{1, 2, 3}, 5));
        System.out.println(T.solution27(new int[]{8, 5, 2, 9, 10, 7}, 30));
        System.out.println(T.solution27(new int[]{8, 9, 12, 23, 45, 16, 25, 50}, 100));
    }
}
