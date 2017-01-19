/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 19. First Draft
 */
package com.dearjun.countschool.count;

import java.util.List;

import com.dearjun.countschool.type.FindSchoolType;
import com.dearjun.countschool.word.WordExtractor;

/**
 * MiddleSchoolCounter.java
 * 
 * @author dearj
 */
public class MiddleSchoolCounter implements SchoolCounter {

    private int allowedSimilarPersantage = 0;
    private int minWordSize = 0;

    public MiddleSchoolCounter(int allowedSimilarPersantage, int minWordSize) {
        this.allowedSimilarPersantage = allowedSimilarPersantage;
        this.minWordSize = minWordSize;
    }

    @Override
    public void printSchoolCount(List<String> commentList) throws Exception {
        WordExtractor wordExtractor = new WordExtractor(FindSchoolType.MIDDLE_SCHOOL);

        this.printSchoolCount(commentList, wordExtractor, allowedSimilarPersantage, minWordSize);
    }
}
