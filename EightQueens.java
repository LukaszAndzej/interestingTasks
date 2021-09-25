package com.company;

import java.util.Arrays;

/*
* Backtracking algorithm
* */

public class EightQueens {

    private static final int widthOfChessboard = 8;

    private static final int[] queensPositionsInRow = {-1,-1,-1,-1,-1,-1,-1,-1};

    private static boolean requirementsFromQueen(int queen) { return ((queen >= 0) && (queen < widthOfChessboard));}

    private static boolean positionNotFound(int position) { return (position < 0);}

    private static void arrayUpdate(int position, int value) { queensPositionsInRow[position] = value;}

    private static boolean queenIsHear(int queenOnChessBord, int myPosition, int direction) {
        return ((queenOnChessBord == (myPosition - direction)) || (queenOnChessBord == (myPosition + direction)));
    }

    private static boolean positionIsNotSave(int i, int row, int column) {
        int queenOnChessBord = queensPositionsInRow[row - i];
        return ( queenIsHear(queenOnChessBord,column,0) || queenIsHear(queenOnChessBord,column,i) );
    }

    private static boolean savePositionForQueen(int row, int column) {

        for(int i = 1; i <= row; i++) if(positionIsNotSave(i,row,column)) return false;

        return true;
    }

    private static int findPosition(int queen) {

        for(int i = queensPositionsInRow[queen] + 1; i < widthOfChessboard; i++) {
            if(savePositionForQueen(queen,i)) return i;
        }

        return -1;
    }

    private static void printSolution() {
        System.out.println("---------------------------------");
        for(int i = 0; i < widthOfChessboard; i++) {
            for(int j = 0; j < widthOfChessboard; j++) {
                if(j == queensPositionsInRow[i]) System.out.print("| Q ");
                else System.out.print("|   ");
            }
            System.out.print("|");
            System.out.println();
            System.out.println("---------------------------------");
        }
    }

    public static int[] solution() {

        int queenNumber = 0;
        while (requirementsFromQueen(queenNumber)) {
            int positionInRow = findPosition(queenNumber);

            if(positionNotFound(positionInRow)) {
                arrayUpdate(queenNumber,-1);
                queenNumber--;
            } else {
                arrayUpdate(queenNumber,positionInRow);
                queenNumber++;
            }
        }

        printSolution();

        return queensPositionsInRow;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(EightQueens.solution()));
    }
}
