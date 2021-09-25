package com.company;

import java.util.Arrays;

/*
Eight queens puzzle

The eight queens puzzle is the problem of placing eight chess queens
on an 8×8 chessboard so that no two queens threaten each other; thus,
a solution requires that no two queens share the same row, column, or diagonal.

The eight queens puzzle is an example of the more general n queens problem of placing n
non-attacking queens on an n×n chessboard, for which solutions exist for all natural
numbers n with the exception of n = 2 and n = 3.

* Backtracking algorithm
* */

public class EightQueens {

    private final int widthOfChessboard;

    public EightQueens(int n) {
        widthOfChessboard = n;
    }

    private int[] queensPositionsInRow;

    private boolean requirementsFromQueen(int queen) { return ((queen >= 0) && (queen < widthOfChessboard));}

    private boolean positionNotFound(int position) { return (position < 0);}

    private void arrayUpdate(int position, int value) { queensPositionsInRow[position] = value;}

    private  boolean queenIsHear(int queenOnChessBord, int myPosition, int direction) {
        return ((queenOnChessBord == (myPosition - direction)) || (queenOnChessBord == (myPosition + direction)));
    }

    private boolean positionIsNotSave(int i, int row, int column) {
        int queenOnChessBord = queensPositionsInRow[row - i];
        return ( queenIsHear(queenOnChessBord,column,0) || queenIsHear(queenOnChessBord,column,i) );
    }

    private boolean savePositionForQueen(int row, int column) {

        for(int i = 1; i <= row; i++) if(positionIsNotSave(i,row,column)) return false;

        return true;
    }

    private int findPosition(int queen) {

        for(int i = queensPositionsInRow[queen] + 1; i < widthOfChessboard; i++) {
            if(savePositionForQueen(queen,i)) return i;
        }

        return -1;
    }

    private void printLine(int n) {
        for(int i = 0; i <= (n*6); i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void printSolution() {
        printLine(widthOfChessboard);
        for(int i = 0; i < widthOfChessboard; i++) {
            for(int j = 0; j < widthOfChessboard; j++) {
                if(j == queensPositionsInRow[i]) System.out.print("|  Q  ");
                else System.out.print("|     ");
            }
            System.out.print("|");
            System.out.println();
            printLine(widthOfChessboard);
        }
    }

    public int[] solution() {

        queensPositionsInRow = new int[widthOfChessboard];
        Arrays.fill(queensPositionsInRow,-1);

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

        EightQueens queens = new EightQueens(4);
        System.out.println(Arrays.toString(queens.solution()));

    }
}
