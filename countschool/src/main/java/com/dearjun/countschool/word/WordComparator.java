/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 17. First Draft
 */
package com.dearjun.countschool.word;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dearjun.countschool.type.FindWordType;
import com.dearjun.countschool.utils.StringUtil;
import com.dearjun.countschool.vo.CalibrationWordVO;

/**
 * WordComparator.java
 * 
 * @author dearj
 */
public class WordComparator {

    private int similarPersantage = 0;
    private boolean addPatternWordToEnd = true;
    private String[] delCalibrationWordArr = null;
    private List<CalibrationWordVO> calibrationWordList = null;
    private List<CalibrationWordVO> calibLocationList = null;

    public WordComparator(int similarPersantage, boolean addPatternWordToEnd, FindWordType findWordType) {
        this.similarPersantage = similarPersantage;
        this.addPatternWordToEnd = addPatternWordToEnd;
        this.delCalibrationWordArr = findWordType.getDelCalibrationWordArr();
        this.calibrationWordList = findWordType.getCalibrationWordList();
        this.calibLocationList = findWordType.getCalibrationLocationList();
    }

    public boolean isCalibratedSimilarWord(String firstStringParam, String secondStringParam, WordExtractor wordExtractor) {
        boolean result = false;
        boolean isCalibFirstWord = false;
        boolean isCalibSecondWord = false;

        String firstString = this.doCalibrateWord(firstStringParam);
        String secondString = this.doCalibrateWord(secondStringParam);

        for(String calibrationWord : this.delCalibrationWordArr) {
            if(firstString.contains(calibrationWord)) {
                firstString = firstString.replace(calibrationWord, "");

                if(!isCalibFirstWord) {
                    isCalibFirstWord = true;
                }
            }

            if(secondString.contains(calibrationWord)) {
                secondString = secondString.replace(calibrationWord, "");

                if(!isCalibSecondWord) {
                    isCalibSecondWord = true;
                }
            }
        }

        firstString = wordExtractor.getWordExcludePatternStr(firstString, addPatternWordToEnd, isCalibFirstWord);
        secondString = wordExtractor.getWordExcludePatternStr(secondString, addPatternWordToEnd, isCalibSecondWord);

        int firstLength = firstString.length();
        int secondLength = secondString.length();

        if(firstLength == 1 || secondLength == 1) {
            return false;
        }

        if(addPatternWordToEnd && (firstString.equals(wordExtractor.getFindWordType().getFindWordStr())
                || secondString.equals(wordExtractor.getFindWordType().getFindWordStr()))) {
            return false;
        }

        if(StringUtil.similar(firstString, secondString) >= similarPersantage) {
            String biggerStr = firstLength > secondLength ? firstString : firstLength < secondLength ? secondString : firstString;
            String smallerStr = firstLength > secondLength ? secondString : firstLength < secondLength ? firstString : secondString;

            if(biggerStr.contains(smallerStr)) {
                result = true;
            }
        }

        return result;
    }

    public String doCalibrateWord(String calibTargetWord) {
        String result = calibTargetWord;

        for(CalibrationWordVO tmpCalibWord : this.calibrationWordList) {
            Pattern calibPattern = Pattern.compile("^.{0,}(" + tmpCalibWord.getCalibSourceWord() + ")$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = calibPattern.matcher(calibTargetWord);

            if(matcher.find()) {
                result = result.replace(tmpCalibWord.getCalibSourceWord(), tmpCalibWord.getCalibDestWord());
            }
        }

        return result;
    }

    public String doCalibrateLocation(String calibTargetWord) {
        String result = calibTargetWord;

        if(this.calibLocationList == null) {
            return result;
        }

        for(CalibrationWordVO tmpCalibLocation : this.calibLocationList) {
            String sourceWord = tmpCalibLocation.getCalibSourceWord();

            if(result.contains(sourceWord)) {
                result = result.replace(sourceWord, tmpCalibLocation.getCalibDestWord());
            }
        }

        return result;
    }
}
