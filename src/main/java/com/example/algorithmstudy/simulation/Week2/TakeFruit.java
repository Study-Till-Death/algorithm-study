package com.example.algorithmstudy.simulation.Week2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TakeFruit {
    public int solution(int[][] fruit) {
        int answer = 0;
        // 아하 1번부터 돌아가면서 들고있는 과일 바구니 중 크기가 가장 작은 과일바구니의 종류가 다르면 교환한다 인듯
        // 서로 손해가 되면 안되기에 내가 뭔가를 줬을 때 그 과일 바구니의 크기가 최소크기가 되버리면 안된다.

        // 1. 각각 서로 뭐필요한지 체크
        // 2. 종류가 다르다면 서로 필요한 걸 줘도 문제가 안생기는지 체크
        // 3. 문제가 없다면 교환완료


        boolean[] alreadyTrade = new boolean[fruit.length];
        for (int i = 0; i < fruit.length; i++) {
            int traderIndex = i;
            // 1. 가장 필요한 것 체크
            // 각 행에서 최솟값의 모든 인덱스를 구하기
            int min = Arrays.stream(fruit[i]).min().orElse(51); // 과일 바구니별 최댓값 50
            //minIndexes -> 최솟값 가지는 항목들 index / 교환희망 품목 타입 0 사과 1 배 2 귤
            int[] minIndexes = IntStream.range(0, fruit[traderIndex].length)
                    .filter(j -> fruit[traderIndex][j] == min)
                    .toArray();

            // 생각해보니 minIndexes 가 여러개면 애초에 교환 불가임
            if (minIndexes.length != 1) {
                continue;
            }

            for (int j = traderIndex + 1; j < fruit.length; j++) {
                int partnerIndex = j;
                if (alreadyTrade[partnerIndex] || alreadyTrade[traderIndex]) {
                    // aleradyTrade 에 추가된 친구면 더이상 교환X
                    continue;
                }
                // 매칭 되면 바로 진행
                int partnerMin = Arrays.stream(fruit[j]).min().orElse(51);
                int[] partnerMinIndexes = IntStream.range(0, fruit[partnerIndex].length)
                        .filter(k -> fruit[partnerIndex][k] == partnerMin)
                        .toArray();

                if (partnerMinIndexes.length != 1 // 마찬가지로 상대도 최소가 2개면 교환 불가임 뭘 교환해도 가져가는 값이 커지지 않음
                        || Arrays.equals(partnerMinIndexes, minIndexes) // 상대화 희망 품목이 같아도 안된다
                        || fruit[traderIndex][partnerMinIndexes[0]] - 1 == min // 서로 희망품목이 하나씩이고 겹치지도 않는다면 줘도 되는지 체크
                        || fruit[partnerIndex][minIndexes[0]] - 1 == partnerMin) { // 하나 주면 최솟값이 되는 경우 주면 안된다
                    continue;
                }
                // 여기까지 문제 없다면 교환하고 교환완료 리스트에 추가
                fruit[traderIndex][partnerMinIndexes[0]]--;
                fruit[traderIndex][minIndexes[0]]++;
                fruit[partnerIndex][partnerMinIndexes[0]]++;
                fruit[partnerIndex][minIndexes[0]]--;
                alreadyTrade[traderIndex] = true;
                alreadyTrade[partnerIndex] = true;
            }
        }

        for (int i = 0; i < fruit.length; i++) {
            answer += Arrays.stream(fruit[i]).min().orElse(51); // 과일 바구니별 최댓값 50
        }

        return answer;
    }

    public static void main(String[] args) {
        TakeFruit T = new TakeFruit();
        System.out.println(T.solution(new int[][]{{10, 20, 30}, {12, 15, 20}, {20, 12, 15}, {15, 20, 10}, {10, 15, 10}}));
        System.out.println(T.solution(new int[][]{{10, 9, 11}, {15, 20, 25}}));
        System.out.println(T.solution(new int[][]{{0, 3, 27}, {20, 5, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}}));
        System.out.println(T.solution(new int[][]{{3, 7, 20}, {10, 15, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}, {12, 12, 6}, {10, 20, 0}, {5, 10, 15}}));
    }
}
