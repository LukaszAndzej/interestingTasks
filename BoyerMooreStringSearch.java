package com.company;

import java.util.Scanner;

/*
The Boyer–Moore algorithm searches for occurrences of P in T by performing explicit character comparisons
at different alignments. Instead of a brute-force search of all alignments (of which there are m-n+1),
Boyer–Moore uses information gained by preprocessing P to skip as many alignments as possible.

The bad-character rule considers the character in T at which the comparison process failed
(assuming such a failure occurred). The next occurrence of that character to the left in P is found,
and a shift which brings that occurrence in line with the mismatched occurrence in T is proposed.
If the mismatched character does not occur to the left in P, a shift is proposed that moves
the entirety of P past the point of mismatch.
* */

class Match {

    private static boolean indexIsZero(int index) { return (index == 0);}

    private static boolean twoCharsAreTheSame(char charText, char charPattern) { return (charText == charPattern);}

    private static int findLastIndex(char charText, int i ,String pattern) {

        for(int k = i, shiftRight = 0; k >= 0; k--, shiftRight++) {
            if(twoCharsAreTheSame(charText, pattern.charAt(k))) return shiftRight;
        }

        return 0;
    }

    private static int getShiftRightIndex(String subText, String pattern) {

        for(int i = subText.length() - 1; i >= 0; i--) {
            if(subText.charAt(i) != pattern.charAt(i)) {
                if(indexIsZero(findLastIndex(subText.charAt(i),i,pattern))) return pattern.length();
                else return findLastIndex(subText.charAt(i),i,pattern);
            }
        }

        return 0;
    }

    public static int occurs(String text, String pattern) {

        int indexEnd = pattern.length();

        int indexBeginning = 0;
        while (indexEnd <= text.length()) {

            String substringFromText = text.substring(indexBeginning,indexEnd);

            int index = getShiftRightIndex(substringFromText,pattern);

            if (indexIsZero(index)) return indexBeginning;
            else {
                indexEnd += index;
                indexBeginning += index;
            }
        }

        return -1;
    }

}

public class BoyerMooreStringSearch {

    private boolean textContainsPattern(int index) { return (index >= 0);}

    public void solution() {

        Scanner input = new Scanner(System.in);
        System.out.println("Text: ");
        String text = input.nextLine();
        System.out.println("Pattern: ");
        String pattern = input.nextLine();

        int indexMatchOccurs = Match.occurs(text,pattern);

        if(textContainsPattern(indexMatchOccurs)) System.out.println("Index: " + indexMatchOccurs);
        else System.out.println("No match! :(");

    }

    public static void main(String[] args) {

        BoyerMooreStringSearch test = new BoyerMooreStringSearch();
        test.solution();
    }
}
