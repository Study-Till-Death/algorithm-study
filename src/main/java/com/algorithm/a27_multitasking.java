package com.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class a27_multitasking {
    public int solution(int[] tasks, long k) {
        List<Integer> taskList = Arrays.stream(tasks)
                .boxed()
                .collect(Collectors.toList());
        int target = 0; //타겟 번호
        for (int i = 0; i < k; i++) { // 해결할 태스크 수만큼 반복
            if (taskList.get(target)!=0) { // 현재 작업번호에 처리할 작업이 0개가 아니면
                taskList.set(target, taskList.get(target)-1); // 작업 1개하는중..

            }else {
                target=getTarget(target, taskList); // 현재 작업번호에 처리할 작업이 0개면 다음 번호로 넘어감
                taskList.set(target, taskList.get(target)-1); // 작업 1개하는중..
            }
            target=getTarget(target, taskList); // 다음 타겟 번호 고를게요
        }

        Collections.sort(taskList);
        if (taskList.get(0)==0 && taskList.get(taskList.size()-1)==0) return -1; // 태스트리스트의 제일 작은수와 큰수가 0이면 처리할 작업 x
        return target+1; // 사실 타겟 번호는 1부터 시작이므로 +1
    }

    public int getTarget (int target, List<Integer> taskList) {
        int targetIdx = target+1; // 다음 타겟 인덱스
        int result=taskList.get(targetIdx%taskList.size()); // 다음 타겟의 값
        while (result==0) { // 다음 타겟의 값이 0이 아닐 때까지 인덱스를 돈다
            targetIdx++;
            result=taskList.get(targetIdx%taskList.size());
        }
        return targetIdx%taskList.size();
    }

    public static void main(String[] args){
        a27_multitasking T = new a27_multitasking();
        System.out.println(T.solution(new int[]{1, 2, 3}, 5));
        System.out.println(T.solution(new int[]{8, 5, 2, 9, 10, 7}, 30));
        System.out.println(T.solution(new int[]{8, 9, 12, 23, 45, 16, 25, 50}, 100));
    }
}
