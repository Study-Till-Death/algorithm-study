package com.example.algorithmstudy.lessons001;

import java.util.Arrays;

public class Lessons001 {
    public static void main(String[] args){
        Lessons001 lessons001 = new Lessons001();
        System.out.println(Arrays.toString(lessons001.askToMagicConchShell(5, new int[][]{{1, 3}, {2, 4}, {1, 4}})));
        System.out.println(Arrays.toString(lessons001.askToMagicConchShell(7, new int[][]{{1, 3, 5}, {1, 3, 6}, {2, 4}})));
        System.out.println(Arrays.toString(lessons001.askToMagicConchShell(8, new int[][]{{1, 5}, {2, 4, 7}, {1, 5, 7}, {2, 5, 7}})));
        System.out.println(Arrays.toString(lessons001.askToMagicConchShell(12, new int[][]{{1, 5, 8, 10}, {2, 4, 7}, {1, 5, 7, 9, 11}, {2, 5, 7, 10}, {3, 6, 8, 11}})));
    }

    private char[] askToMagicConchShell(int numberOfPeople, int[][] allLadderFootPositions){
        char[] people = new char[numberOfPeople];

        char currentCodeName = 'A';
        for(int i = 0; i < numberOfPeople; i++){
            people[i] = currentCodeName++;
        }

        for(int[] ladderFootPositionsByDepth : allLadderFootPositions){
            for(int rightSideOfLadderFoot : ladderFootPositionsByDepth){
                char temp = people[rightSideOfLadderFoot-1];
                people[rightSideOfLadderFoot-1] = people[rightSideOfLadderFoot];
                people[rightSideOfLadderFoot] = temp;
            }
        }

        return people;
    }
}
