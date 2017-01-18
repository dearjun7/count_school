/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 14. First Draft
 */
package com.dearjun.countschool.utils;

/**
 * StringUtil.java
 * 
 * @author dearj
 */
public class StringUtil {

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
                sum += StringUtil.similar(firstString.substring(0, positionFirstIndex > firstLength ? firstLength : positionFirstIndex),
                        secondString.substring(0, positionSecondIndex > secondLength ? secondLength : positionSecondIndex));
            }

            if((positionSecondIndex + max < firstLength) && (positionSecondIndex + max < secondLength)) {
                sum += StringUtil.similar(firstString.substring(positionFirstIndex + max, firstLength),
                        secondString.substring(positionSecondIndex + max, secondLength));
            }
        }

        return StringUtil.similarPercent(firstLength, secondLength, sum);
    }

    private static int similarPercent(int firstLength, int secondLength, int sum) {
        int biggerLength = firstLength > secondLength ? firstLength : firstLength < secondLength ? secondLength : firstLength;
        int result = 0;

        if(biggerLength != 0) {
            result = 100 * sum / biggerLength;
        }

        return result;
    }

    /**
     * targetWord가 containCheckWord에 중복되는 단어인지를 확인한다.<br>
     * 1. char Array에 속하는 단어인지 확인 (1글자 단어의 중첩여부 보정을 위하여)<br>
     * 2. containCheckWord에 targetWord가 속해있는지를 확인 <br>
     * 3. 1과 2를 논리 곱으로 하여 현재 단어의 앞에 붙여도 되는 중첩되지 않은 단어인지 결과를 리턴
     * 
     * @param checkCharArr
     *            - optional : null일 경우, containCheckWord에 targetWord가 속해있는지만을
     *            확인
     * @param targetWord
     * @param containCheckWord
     * @return 중복 단어 발견 : true, 중복 단어 없음 : false
     */
    public static boolean checkDuplicatedWord(char[] checkCharArr, String targetWord, String containCheckWord) {
        boolean result = false;

        for(char checkChar : checkCharArr) {
            if(targetWord.equals(String.valueOf(checkChar))) {
                result = true;

                break;
            }
        }

        return result || !containCheckWord.contains(targetWord);
    }

    public static boolean equalsPatternString(String patternStr, String word) {
        boolean result = false;

        if(word.equals(patternStr)) {
            result = true;
        }

        return result;
    }

}
