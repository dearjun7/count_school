package com.dearjun.countschool.utils;

public class StringSimilarAnalyzer {

    public static int similar(String firstString, String secondString) {
        char[] firstArr = firstString.toCharArray();
        char[] secondArr = secondString.toCharArray();
        int firstLength = firstArr.length;
        int secondLength = secondArr.length;

        int l;
        int positionFirstIndex = 0;
        int positionSecondIndex = 0;
        int max = 0;

        for(int p = 0; p < firstLength; p++) {
            for(int q = 0; q < secondLength; q++) {
                for(l = 0; (p + l < firstLength) && (q + l < secondLength) && (firstArr[p + l] == secondArr[q + l]); l++);

                if(l > max) {
                    max = l;
                    positionFirstIndex = p;
                    positionSecondIndex = q;
                }
            }
        }

        int sum = max;

        if(sum > 0) {
            if(positionFirstIndex > 0 && positionSecondIndex > 0) {
                sum += StringSimilarAnalyzer.similar(
                        firstString.substring(0, positionFirstIndex > firstLength ? firstLength : positionFirstIndex),
                        secondString.substring(0, positionSecondIndex > secondLength ? secondLength : positionSecondIndex));
            }

            if((positionSecondIndex + max < firstLength) && (positionSecondIndex + max < secondLength)) {
                sum += StringSimilarAnalyzer.similar(firstString.substring(positionFirstIndex + max, firstLength),
                        secondString.substring(positionSecondIndex + max, secondLength));
            }
        }

        return StringSimilarAnalyzer.similarPercent(firstLength, secondLength, sum);
    }

    private static int similarPercent(int firstLength, int secondLength, int sum) {
        int biggerLength = firstLength > secondLength ? firstLength : firstLength < secondLength ? secondLength : firstLength;
        int result = 0;

        if(biggerLength != 0) {
            result = 100 * sum / biggerLength;
        }

        return result;
    }
}
