package com.company;

/*
First we need to discuss Compute Prefix Function.
It's really important function because it tells us from which character in pattern
I should start comparing rest of the text.

The main comparing is in the matcher method.
We compare one by one a characters in the Text ( O(n) ) but we're using a Compute Prefix Function
which gives us an array and this array tells us from where I should start comparing/matching
character from pattern with character in the text.

            The complexity of the overall algorithm is O(n + m)
* */

public class KnuthMorrisPratt {

    private static boolean charactersAreTheSame(char c1, char c2) { return (c1 == c2);}

    private static boolean subtextIsTheSameWithPattern(int q, int patternLength) { return (q == patternLength);}

    private static boolean backToEarlierCharacter(int k, char c1, char c2) { return ((k > 0) && !charactersAreTheSame(c1,c2));}

    private static int[] computePrefixFunction(String pattern) {

        int patternLength = pattern.length();

        int[] arrayPrefix = new int[patternLength];

        arrayPrefix[0] = 0; // first always zero

        int k = 0;
        for(int q = 1; q < patternLength; q++) {

            while ( backToEarlierCharacter(k, pattern.charAt(k), pattern.charAt(q))) {
                k = arrayPrefix[k-1];
            }

            if(charactersAreTheSame(pattern.charAt(k), pattern.charAt(q))) k++;

            arrayPrefix[q] = k;
        }

        return arrayPrefix;
    }

    public static void matcher(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();

        int[] arrayPrefixOfPattern = computePrefixFunction(pattern);

        int prefix = 0;
        for(int i = 0; i < textLength; i++) {
            while (backToEarlierCharacter(prefix, pattern.charAt(prefix), text.charAt(i))) {
                prefix = arrayPrefixOfPattern[prefix];
            }

            if(charactersAreTheSame(pattern.charAt(prefix),text.charAt(i))) prefix++;

            if(subtextIsTheSameWithPattern(prefix, patternLength)) {
                System.out.println("Found pattern in the text: " + (i - (patternLength - 1)) + "-" + i);
                prefix = arrayPrefixOfPattern[prefix - 1];
            }
        }
    }

}
