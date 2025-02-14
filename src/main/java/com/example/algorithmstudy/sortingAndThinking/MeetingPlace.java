package com.example.algorithmstudy.sortingAndThinking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MeetingPlace {
    public int solution(int[][] board) {

        // 평균값하고 소숫 점 있으면  양쪽 숫자만 비교해야 되나

        // 일단 당연히 0~board.length -1 만큼 x좌표 y좌표 돌려보면 답은 나오는데 좀 아쉬움
        // 중간 값이네
        // 결국 각 값들이 자기 기준으로 얼마나 떨어져 있냐가 관건인데
        //  1 2 5 이면  2에서 최소가 된다 왜냐 2->3 가면 1,2 2칸 손해 5 에서 1칸 이득이라 가면 손해임 즉 갈 필요 없음
        // 즉 각 값들이 서로 손해 안보고 모이는 지점은 중간값이 된다 왜냐
        // 중간값에서 벗어나면 반드시 한 쪽은 손해보는 이동이 발생하기 때문

        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1) {
                    xs.add(j);
                    ys.add(i);
                }
            }
        }

        int middleX = xs.stream().sorted().skip(xs.size() / 2).findFirst().orElse(0); // 중간 값 없는게 어딨겠어
        int middleY = ys.stream().sorted().skip(xs.size() / 2).findFirst().orElse(0); // 중간 값 없는게 어딨겠어
        return IntStream.range(0, xs.size()).map(i -> Math.abs(xs.get(i) - middleX) + Math.abs(ys.get(i) - middleY)).sum();
    }

    public static void main(String[] args) {
        MeetingPlace T = new MeetingPlace();
        System.out.println(T.solution(new int[][]{{1, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
        System.out.println(T.solution(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}}));
        System.out.println(T.solution(new int[][]{{1, 0, 0, 0, 1, 1}, {0, 1, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 1, 1}}));
    }
}
