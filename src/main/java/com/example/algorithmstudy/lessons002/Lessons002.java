package com.example.algorithmstudy.lessons002;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Lessons002 {
    public static void main(String[] args) {
        Lessons002 lessons002 = new Lessons002();

        System.out.println(Arrays.toString(lessons002.askToMagicConchShell(
                new int[][]{{0, 0, 0, 0, 0}, {0, 1, 1, 0, 0}, {0, 0, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 0, 0, 0, 0}}, 10
        )));
        System.out.println(Arrays.toString(lessons002.askToMagicConchShell(
                new int[][]{{0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}}, 20
        )));
        System.out.println(Arrays.toString(lessons002.askToMagicConchShell(
                new int[][]{{0, 0, 1, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}}, 25
        )));
    }

    private int[] askToMagicConchShell(int[][] mapOfRoom, int count){
        //3, 6, 9 ,12
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        int roomSize = mapOfRoom.length;
        int[] position = {0,0};
        int direction = 0;

        for(int i = 0; i < count; i++){
            int nextPositionX = position[0] + directions[direction%4][0];
            int nextPositionY = position[1] + directions[direction%4][1];

            if(nextPositionX >= 0 && nextPositionX < roomSize && nextPositionY >= 0 && nextPositionY < roomSize && mapOfRoom[nextPositionX][nextPositionY] != 1){
                position[0] = nextPositionX;
                position[1] = nextPositionY;
            } else {
                direction++;
            }
        }

        return position;
    }
}
