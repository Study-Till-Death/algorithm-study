package com.example.algorithmstudy.week9;
import java.util.*;

//GPT가 시간 복잡도로 정답 이겼대요 매우 기분 좋음 근데 생각해보니 테마가 정렬인데 정렬 안했음
public class MyAnswer027 {
    public int solution(int[] tasks, long k) {

        //현재 남은 작업 개수
        int remainingTasks = tasks.length;

        while (true) {
            //현재 배열의 최소값 찾기
            int minValue = Arrays.stream(tasks).filter(i -> i > 0).min().getAsInt();

            //위에서 구한 최소값 갯수 구하기
            int countZeroValue = 0;
            for (int task : tasks) {
                if (task == minValue) {
                    countZeroValue++;
                }
            }

            //최소값 * 배열 길이 = 시간 소모량
            long spendTimeValue = (long) remainingTasks * minValue;
            //k(작업시간) 이 시간 소모량 보다 작으면
            if (k < spendTimeValue) {
                //k가 현재 남은 작업 갯수 보다 클 수 있으니 n을 나눈 나머지 만큼 가져옴 (어차피 multipleSpendTime가 더 큰거면 이제 0이되는 작업은 존재 할 수 없음)
                if(remainingTasks < k){
                    k %= remainingTasks;
                }
                //가져온 k값에 대해서 0인걸 제외 하고 반복문 돌려 가면서 k시간 만큼 돌려서 다음 작업 가져 오면 그게 정답
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] > 0) {
                        if (k == 0) return i + 1;
                        k--;
                    }
                }
            }

            //작업 시간이 시간 소모량 보다 큰 거니까 남은 작업 갯수 에서 0초가 되는 작업들 전부 뺌
            remainingTasks -= countZeroValue;
            //시간이 소모된 거니까 k에서 시간 소모량 만큼 뺌
            k -= spendTimeValue;
            //0이 된 값들에 대해 전부 0으로 처리
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] <= minValue) {
                    tasks[i] = 0;
                }else{
                    //0초가 되지 않는 작업 들에 대해서 최소값 만큼 빼기
                    tasks[i] -= minValue;
                }
            }

            //모든 값이 0이면 -1
            if (Arrays.stream(tasks).allMatch(i -> i == 0)) {
                return -1;
            }
        }
    }

    public static void main(String[] args){
        MyAnswer027 T = new MyAnswer027();
        System.out.println(T.solution(new int[]{1, 2, 3}, 5));
        System.out.println(T.solution(new int[]{8, 5, 2, 9, 10, 7}, 30));
        System.out.println(T.solution(new int[]{8, 9, 12, 23, 45, 16, 25, 50}, 100));
    }
}
