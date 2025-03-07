package com.algorithm;

import java.util.HashSet;
import java.util.Set;

public class a35_largestNumber {
    public int solution(int n){
        int answer = -1;
        String numStr = String.valueOf(n);
        boolean[] visited = new boolean[numStr.length()];
        Set<Integer> resultSet = new HashSet<>();

        findAllType(numStr, "",visited, resultSet);

        for (Integer num : resultSet) { // 모든 경우의 숫자를 담은 set과 비교하며 원형보다 크되 가장 작은 수를 찾는다
            if (n < num && (answer > num || answer == -1)) {
                answer = num;
            }
        }

        return answer;
    }

    private static void findAllType(String numStr, String current, boolean[] visited, Set<Integer> resultSet) {
        if (current.length() == numStr.length()) { // 만든 문자열이 원형의 길이와 동일할 경우 resultSet에 추가
            if (!current.equals(numStr)) resultSet.add(Integer.parseInt(current)); // 원형이랑 동일하면 신경쓰이니까 뺌
            return;
        }

        for (int i = 0; i < numStr.length(); i++) {
            if (!visited[i]) { // 방문한적 없는 경우
                visited[i] = true; // 방문표시
                findAllType(numStr, current + numStr.charAt(i), visited, resultSet); // 재귀돌며 문자열만듦
                visited[i] = false; // resultSet에 문자열 추가 이후 시점
            }
        }
    }

    public static void main(String[] args){
        a35_largestNumber T = new a35_largestNumber();
        System.out.println(T.solution(123));
        System.out.println(T.solution(321));
        System.out.println(T.solution(20573));
        System.out.println(T.solution(27711));
        System.out.println(T.solution(54312));
    }
}
