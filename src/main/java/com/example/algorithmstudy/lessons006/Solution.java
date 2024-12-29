package com.example.algorithmstudy.lessons006;

import java.util.ArrayList;

public class Solution {
    public int solution(int[][] fruit){
        int answer = 0;

        ArrayList<Student> students = new ArrayList<Student>();

        for (int i = 0; i < fruit.length; i++) {
            students.add(new Student(fruit[i][0], fruit[i][1], fruit[i][2]));
        }

        for (int i = 0; i < students.size(); i++) {
            for(int j = i+1; j < students.size(); j++) {

                if(students.get(i).canSwap){
                    students.get(i).trade(students.get(j));
                }
            }
        }

        for (Student student : students) {
            answer+=student.fruitBasket.getFruitValue(student.fruitBasket.getMinFruit());
        }

      
        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[][]{{10, 20, 30}, {12, 15, 20}, {20, 12, 15}, {15, 20, 10}, {10, 15, 10}}));
        System.out.println(T.solution(new int[][]{{10, 9, 11}, {15, 20, 25}}));
        System.out.println(T.solution(new int[][]{{0, 3, 27}, {20, 5, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}}));
        System.out.println(T.solution(new int[][]{{3, 7, 20}, {10, 15, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}, {12, 12, 6}, {10, 20, 0}, {5, 10, 15}}));
    }
}

class Student {
    FruitBasket fruitBasket;
    boolean canSwap = true;

    Student(int apple, int pear, int mandarin) {
        this.fruitBasket = new FruitBasket(apple, pear, mandarin);
    }

    Boolean isProfit(FruitBasket othersFruitBasket) {
        int myFruitsTheyWant = this.fruitBasket.getFruitValue(othersFruitBasket.getMinFruit());
        int othersFruitsIWant = othersFruitBasket.getFruitValue(this.fruitBasket.getMinFruit());
        int minMyFruit = this.fruitBasket.getFruitValue(this.fruitBasket.getMinFruit());
        int minOthersFruit = othersFruitBasket.getFruitValue(othersFruitBasket.getMinFruit());
        
        if(!this.fruitBasket.getMinFruit().equals(othersFruitBasket.getMinFruit()) && myFruitsTheyWant > 0 && othersFruitsIWant > 0) {
            return minMyFruit + 1 < myFruitsTheyWant - 1 && minOthersFruit + 1 < othersFruitsIWant - 1;

        }
        return false;
    }

    void trade(Student other) {
        if(!(this.canSwap && other.canSwap)) {
            return;
        }

        if(this.isProfit(other.fruitBasket)) {
            this.fruitBasket.add(this.fruitBasket.getMinFruit());
            this.fruitBasket.substract(other.fruitBasket.getMinFruit());

            other.fruitBasket.add(other.fruitBasket.getMinFruit());
            other.fruitBasket.substract(this.fruitBasket.getMinFruit());

            this.canSwap = false;
            other.canSwap = false;
        }
    }
}

class FruitBasket {
    int apple;
    int pear;
    int mandarin;

    FruitBasket(int apple, int pear, int mandarin) {
        this.apple = apple;
        this.pear = pear;
        this.mandarin = mandarin;
    }

    FruitType getMinFruit() {
        if (apple <= pear && apple <= mandarin) {
            return FruitType.APPLE;
        } else if (pear <= apple && pear <= mandarin) {
            return FruitType.PEAR;
        } else {
            return FruitType.MANDARIN;
        }
    }

    int getFruitValue(FruitType fruitType) {
        return switch (fruitType) {
            case APPLE -> apple;
            case PEAR -> pear;
            case MANDARIN -> mandarin;
        };
    }

    void add(FruitType fruitType) {
        switch (fruitType) {
            case APPLE -> this.apple++;
            case PEAR -> this.pear++;
            case MANDARIN -> this.mandarin++;
        }
    }

    void substract(FruitType fruitType) {
        switch (fruitType) {
            case APPLE -> this.apple--;
            case PEAR -> this.pear--;
            case MANDARIN -> this.mandarin--;
        }
    }
}

enum FruitType {
    APPLE, PEAR, MANDARIN
}