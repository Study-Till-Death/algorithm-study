package com.example.algorithmstudy.lessons003;

import java.util.Arrays;

public class Lessons003 {
    public static void main(String[] args) {
        Lessons003 lessons003 = new Lessons003();

        System.out.println(lessons003.askToMagicConchShell(new int[][] {
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 2, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0}
        }));
        System.out.println(lessons003.askToMagicConchShell(new int[][] {
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 2, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 3}
        }));
    }
    private int askToMagicConchShell(int[][] mapOfForest){
        //3, 6, 9 ,12
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        int forestSize = 10;
        int count = 0;
        int max = 10000;

        int[] positionOfHuman = {0,0};
        int directionOfHuman = 3;

        outerLoop1:
        for(int x = 0; x < mapOfForest.length; x++){
            for(int y = 0; y < mapOfForest.length; y++){
                if(mapOfForest[x][y] == 2){
                    positionOfHuman = new int[]{x, y};
                    break outerLoop1;
                }
            }
        }

        int[] positionOfDog = {0,0};
        int directionOfDog = 3;

        outerLoop2:
        for(int x = 0; x < mapOfForest.length; x++){
            for(int y = 0; y < mapOfForest.length; y++){
                if(mapOfForest[x][y] == 3){
                    positionOfDog = new int[]{x, y};
                    break outerLoop2;
                }
            }
        }

        for( ; count < max; count++) {
            int nextPositionXOfHuman = positionOfHuman[0] + directions[directionOfHuman%4][0];
            int nextPositionYOfHuman = positionOfHuman[1] + directions[directionOfHuman%4][1];

            int nextPositionXOfDog = positionOfDog[0] + directions[directionOfDog%4][0];
            int nextPositionYOfDog = positionOfDog[1] + directions[directionOfDog%4][1];

            if(nextPositionXOfHuman >= 0 && nextPositionXOfHuman < forestSize && nextPositionYOfHuman >= 0 && nextPositionYOfHuman < forestSize && mapOfForest[nextPositionXOfHuman][nextPositionYOfHuman] != 1){
                positionOfHuman[0] = nextPositionXOfHuman;
                positionOfHuman[1] = nextPositionYOfHuman;
            } else {
                directionOfHuman++;
            }

            if(nextPositionXOfDog >= 0 && nextPositionXOfDog < forestSize && nextPositionYOfDog >= 0 && nextPositionYOfDog < forestSize && mapOfForest[nextPositionXOfDog][nextPositionYOfDog] != 1){
                positionOfDog[0] = nextPositionXOfDog;
                positionOfDog[1] = nextPositionYOfDog;
            } else {
                directionOfDog++;
            }

            if(Arrays.equals(positionOfHuman, positionOfDog)){
                count++;
                break;
            }

        }

        return count<max?count:0;
    }
}
