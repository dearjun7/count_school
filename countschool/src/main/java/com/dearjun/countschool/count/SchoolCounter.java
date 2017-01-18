/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 19. First Draft
 */
package com.dearjun.countschool.count;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dearjun.countschool.external.OpenAPISearcher;
import com.dearjun.countschool.external.SchoolDataSearcher;
import com.dearjun.countschool.search.SearchResultCounter;
import com.dearjun.countschool.word.WordExtractor;

/**
 * SchoolCounter.java
 * 
 * @author dearj
 */
public interface SchoolCounter {

    public void printSchoolCount(List<String> commentList) throws Exception;

    public default void printSchoolCount(List<String> commentList, WordExtractor wordExtractor, int allowedSimilarPersantage,
            int minWordSize) throws Exception {
        List<String> analyzedWordList = wordExtractor.getAnalyedWordList(commentList);
        List<String> compareWordList = new ArrayList<String>();

        Collections.sort(analyzedWordList);
        compareWordList.addAll(analyzedWordList);

        String[] list1 = (String[]) analyzedWordList.toArray(new String[analyzedWordList.size()]);
        String[] list2 = (String[]) compareWordList.toArray(new String[compareWordList.size()]);

        OpenAPISearcher schSearcher = new SchoolDataSearcher();
        SearchResultCounter searchResultCounter = new SearchResultCounter(allowedSimilarPersantage, minWordSize, wordExtractor,
                schSearcher);

        searchResultCounter.printSearchResultWithCounting(list1, list2);
    }
}
