package com.algorithm;

public class a06_takeFruits {
    // 1부터 n까지 총 n명의 학생 존재
    // 책상위에는 각 바구니 3개 있고 가장 적은 양을 가진 바구니를 갖는다
    // 교환티켓 1
    public int solution(int[][] fruit){
        int answer = 0;
        boolean[] exchangeYn = new boolean[fruit.length]; // 교환여부
        int[][] minNumIndex = getMinNumIndex(fruit); // 각 배열 가장 작은 숫자와 해당하는 인덱스

        for (int i = 0; i < fruit.length-1; i++) { // 1번부터 교환 start
            if (exchangeYn[i]) continue; // 교환된 경우 skip
            for (int j = i+1; j < fruit.length; j++) { // i 다음숫자부터 체크
                if (minNumIndex[i][1] == minNumIndex[j][1]) continue; // 동일 인덱스가 가장 작은 수인경우 skip
                if (fruit[j][minNumIndex[i][1]] - 1 < minNumIndex[j][0]+1 // 교환 후 가장 적은 과일 수보다 적어지면 skip
                        || fruit[i][minNumIndex[j][1]] - 1 < minNumIndex[i][0]+1) continue;
                if (exchangeYn[j]) continue; // 교환상대가 이미 교환했을 경우

                exchangeYn[i] = true; // 교환
                exchangeYn[j] = true;
                minNumIndex[i][0]++;
                minNumIndex[j][0]++;
                break;
            }
        }

        for (int[] num : minNumIndex) {
            answer += num[0];
        }

        return answer;
    }

    int[][] getMinNumIndex (int[][] fruit){
        int tempMinNum;
        int tempMinIndex;
        int[][] minNumIndex = new int[fruit.length][2];
        for (int i = 0; i < fruit.length; i++) {
            tempMinNum=fruit[i][0];
            tempMinIndex=0;
            for (int j = 0; j < fruit[i].length; j++) {
                if (tempMinNum > fruit[i][j]) {
                    tempMinNum = fruit[i][j];
                    tempMinIndex = j;
                }
            }
            minNumIndex[i][0] = tempMinNum;
            minNumIndex[i][1] = tempMinIndex;
        }
        return minNumIndex;
    }

    public static void main(String[] args){
        a06_takeFruits T = new a06_takeFruits();
        System.out.println(T.solution(new int[][]{{10, 20, 30}, {12, 15, 20}, {20, 12, 15}, {15, 20, 10}, {10, 15, 10}}));
        System.out.println(T.solution(new int[][]{{10, 9, 11}, {15, 20, 25}}));
        System.out.println(T.solution(new int[][]{{0, 3, 27}, {20, 5, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}}));
        System.out.println(T.solution(new int[][]{{3, 7, 20}, {10, 15, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}, {12, 12, 6}, {10, 20, 0}, {5, 10, 15}}));
    }
}