package com.example.algorithmstudy.week12;

import java.util.*;

// 줄다리기
class Solution36 {
    static List<List<Integer>> permutations = new ArrayList<>();

    // 순열 생성 (지피티 참고)
    public void generatePermutations(int[] arr, int depth, int n) {
        if (depth == n) {
            List<Integer> list = new ArrayList<>();
            for (int num : arr) list.add(num); // 현재 배열 상태를 리스트로 변환하여 저장
            permutations.add(list); // 저장된 순열을 리스트에 추가
            return; // 재귀 종료
        }

        // 현재 위치부터 끝까지 모든 원소를 한 번씩 선택하여 순열을 만든다.
        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);  // 선택한 원소를 depth 위치로 이동 (자리 변경)
            generatePermutations(arr, depth + 1, n); // 다음 위치(depth + 1)로 이동하여 순열을 계속 생성
            swap(arr, depth, i);  // 원래 상태로 복구 (백트래킹)
        }
    }

    // 교환하기
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 얘랑 같이 줄다리기 못하겠어요
    public boolean isValid(List<Integer> order, Set<String> hatePairs) {
        for (int i = 0; i < order.size() - 1; i++) {
            String pair1 = order.get(i) + "," + order.get(i + 1);
            String pair2 = order.get(i + 1) + "," + order.get(i);
            if (hatePairs.contains(pair1) || hatePairs.contains(pair2)) {
                return false; // 싫어하는 학생이 옆에 있으면 false
            }
        }
        return true;
    }

    public int solution36(int[][] fight) {
        int[] students = {1, 2, 3, 4, 5, 6, 7};
        permutations.clear();
        generatePermutations(students, 0, students.length); // 순열 만들기

        // 나 얘랑 같이 줄다리기 못하겠어요 만들기
        Set<String> hatePairs = new HashSet<>();
        for (int[] pair : fight) {
            hatePairs.add(pair[0] + "," + pair[1]);
            hatePairs.add(pair[1] + "," + pair[0]); // 양방향 다 저장
        }

        // 같이 줄다리기 못하는 경우들 빼고 세기
        int count = 0;
        for (List<Integer> order : permutations) {
            if (isValid(order, hatePairs)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution36 T = new Solution36();
        System.out.println(T.solution36(new int[][]{{1, 3}, {5, 7}, {4, 2}}));
        System.out.println(T.solution36(new int[][]{{3, 2}, {3, 5}, {5, 2}, {7, 3}}));
        System.out.println(T.solution36(new int[][]{{1, 2}, {1, 5}, {1, 7}, {1, 3}}));
        System.out.println(T.solution36(new int[][]{{1, 7}}));
        System.out.println(T.solution36(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}}));
    }
}