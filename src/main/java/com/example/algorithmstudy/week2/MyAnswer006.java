package com.example.algorithmstudy.week2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MyAnswer006 {
    public int solution(int[][] fruit){
        int answer = 0;

        int[] lowerFruitNumbers = new int[fruit.length];

        Set<Integer> excludedSet = new HashSet<>();

        for(int i=0;i<fruit.length;i++){
            int[] baseLowBarket = getMinNumber(fruit[i]);
            int[] baseMiddleBarket = getMiddleNumber(fruit[i]);
            int[] baseHighBarket = getMaxNumber(fruit[i]);
            if(excludedSet.contains(i)){
                continue;
            }
            lowerFruitNumbers[i] = baseLowBarket[0];
            for(int j=0; j <fruit.length; j++){
                int[] exchangeLowBarket = getMinNumber(fruit[j]);
                int[] exchangeMiddleBarket = getMiddleNumber(fruit[j]);
                int[] exchangeHighBarket = getMaxNumber(fruit[j]);
                //순서대로 교환이므로 본인, 이전학생 제외, 이미 교환한 학생 제외
                if(i >= j || excludedSet.contains(j)){
                    continue;
                }
                //교환해주는 학생 과일이 전부 동일할 경우
                if(exchangeMiddleBarket[0] == exchangeLowBarket[0] && exchangeMiddleBarket[0] == exchangeHighBarket[0]){
                    continue;
                }
                //교환 받는 학생 과일이 전부 동일할 경우
                if(baseMiddleBarket[0] == baseLowBarket[0] && baseMiddleBarket[0] == baseHighBarket[0]){
                    continue;
                }
                //가장 작은 과일 갯수의 종류가 같을 경우
                if(exchangeLowBarket[1] == baseLowBarket[1]){
                    continue;
                }
                //가장 작은값과 중간값이 1밖에 차이 안나는데 교환해주는 학생의 과일종류가 중간값의 과일 종류가 동일할 경우
                if(baseLowBarket[0]+1 == baseMiddleBarket[0]){
                    if(exchangeLowBarket[1] == baseMiddleBarket[1]){
                        continue;
                    }
                }
                //가장 작은값과 중간값이 1밖에 차이 안나는데 교환받는 학생의 과일종류가 중간값의 과일 종류가 동일할 경우
                if(exchangeLowBarket[0]+1 == exchangeMiddleBarket[0]){
                    if(baseLowBarket[1] == exchangeMiddleBarket[1]){
                        continue;
                    }
                }
                //가장 작은 값에 +1을 했더니 중간 값을 넘어버리는 경우 (교환시 이득이 없음)
                if((baseLowBarket[0]+1) > baseMiddleBarket[0] || (exchangeLowBarket[0] + 1) > exchangeMiddleBarket[0]){
                    continue;
                }
                lowerFruitNumbers[i] = baseLowBarket[0] + 1;
                lowerFruitNumbers[j] = exchangeLowBarket[0] + 1;
                //교환 한번 했으면 더 이상 교환 할 필요가 없으니 제외로 추가
                excludedSet.add(i);
                excludedSet.add(j);
                break;
            }
        }

        for (int lowerFruitNumber : lowerFruitNumbers) {
            answer += lowerFruitNumber;
        }

        return answer;
    }
    public int[] getMinNumber(int[] studentBarket){
        //과일숫자는 50이하
        int lowValue = 50;
        int lowIndex = 0;
        for(int i=0;i<studentBarket.length;i++){
            if(lowValue > studentBarket[i]){
                lowValue = studentBarket[i];
                lowIndex = i;
            }
        }
        return new int[]{lowValue, lowIndex};
    }

    public int[] getMiddleNumber(int[] studentBarket) {
        // 최소값과 최대값 구하기
        int[] sortedArray = studentBarket.clone();
        Arrays.sort(sortedArray);
        int middleValue = sortedArray[sortedArray.length / 2];
        // 중간값 찾기
        int index = -1;
        for (int i = 0; i < studentBarket.length; i++) {
            if (studentBarket[i] == middleValue) {
                index = i;
                break;
            }
        }
        return new int[]{middleValue, index};
    }

    public int[] getMaxNumber(int[] studentBarket){
        int highValue = 0;
        int highIndex = 0;
        for(int i=0;i<studentBarket.length;i++){
            if(highValue < studentBarket[i]){
                highValue = studentBarket[i];
                highIndex = i;
            }
        }
        return new int[]{highValue, highIndex};
    }

    public static void main(String[] args){
        MyAnswer006 T = new MyAnswer006();
        System.out.println(T.solution(new int[][]{{10, 20, 30}, {12, 15, 20}, {20, 12, 15}, {15, 20, 10}, {10, 15, 10}}));
        System.out.println(T.solution(new int[][]{{10, 9, 11}, {15, 20, 25}}));
        System.out.println(T.solution(new int[][]{{0, 3, 27}, {20, 5, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}}));
        System.out.println(T.solution(new int[][]{{3, 7, 20}, {10, 15, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}, {12, 12, 6}, {10, 20, 0}, {5, 10, 15}}));
    }
}
