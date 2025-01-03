package com.example.algorithmstudy.week3;
import java.util.*;

public class MyAnswer008 {
    public int[] solution(int[] enter, int[] exit){
        int personCount = enter.length;
        int[] answer = new int[personCount];
        Set<Integer> room = new HashSet<>();
        Map<Integer, Set<Integer>> meetTracking = new HashMap<>();

        //사람 1부터 차례대로 생성
        for (int i = 1; i <= personCount; i++) {
            meetTracking.put(i, new HashSet<>());
        }

        int enterIndex = 0;

        for (int person : exit) {
            while (!room.contains(person)) {
                int entering = enter[enterIndex++];
                for (int existingPerson : room) {
                    meetTracking.get(entering).add(existingPerson);
                    meetTracking.get(existingPerson).add(entering);
                }
                room.add(entering);
            }
            room.remove(person);
        }

        for (int i = 1; i <= personCount; i++) {
            answer[i - 1] = meetTracking.get(i).size();
        }

        return answer;
    }

    public static void main(String[] args){
        MyAnswer008 T = new MyAnswer008();
        System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 5, 3, 4}, new int[]{2, 3, 1, 4, 5})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 3, 2, 4, 5, 7, 6, 8}, new int[]{2, 3, 5, 6, 1, 4, 8, 7})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 7, 2, 3, 5, 6}, new int[]{5, 2, 6, 1, 7, 3, 4})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 2, 3}, new int[]{2, 1, 4, 3})));
    }
}
