/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 19. First Draft
 */
package com.dearjun.countschool.search;

import com.dearjun.countschool.external.OpenAPISearcher;
import com.dearjun.countschool.word.WordComparator;
import com.dearjun.countschool.word.WordExtractor;

/**
 * SearchResultCounter.java
 * 
 * @author dearj
 */
public class SearchResultCounter {

    private int allowedSimilarPersantage = 0;
    private int minWordSize = 0;
    private WordExtractor wordExtractor = null;
    private OpenAPISearcher apiSearcher = null;

    public SearchResultCounter(int allowedSimilarPersantage, int minWordSize, WordExtractor wordExtractor, OpenAPISearcher apiSearcher) {
        this.allowedSimilarPersantage = allowedSimilarPersantage;
        this.minWordSize = minWordSize;
        this.wordExtractor = wordExtractor;
        this.apiSearcher = apiSearcher;
    }

    public void printSearchResultWithCounting(String[] list1, String[] list2) throws Exception {
        boolean addPatternWordToEnd = true;
        WordComparator wordComparator = new WordComparator(allowedSimilarPersantage, addPatternWordToEnd, wordExtractor.getFindWordType());

        for(String firstString : list1) {
            int count = 0;
            String existCheckedWord = null;

            if(firstString == null) {
                continue;
            }

            boolean isExistSchool = false;

            int index = 0;
            for(String secondString : list2) {
                if(secondString == null) {
                    index++;
                    continue;
                }

                boolean result = wordComparator.isCalibratedSimilarWord(firstString, secondString, wordExtractor);

                if(result) {
                    existCheckedWord = wordComparator.doCalibrateLocation(firstString);

                    if(existCheckedWord.length() > this.minWordSize
                            && apiSearcher.checkExistSearchKeyword(existCheckedWord, wordExtractor.getFindWordType().getSearchTypeKey())) {
                        isExistSchool = true;
                    }

                    list1[index] = null;
                    list2[index] = null;
                    count++;
                }

                index++;
            }

            if(isExistSchool) {
                System.out.println(existCheckedWord + " \t " + count);
            }
        }
    }
}
