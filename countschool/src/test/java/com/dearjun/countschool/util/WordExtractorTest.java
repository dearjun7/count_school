/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 16. First Draft
 */
package com.dearjun.countschool.util;

import org.junit.Test;

import com.dearjun.countschool.type.FindSchoolType;
import com.dearjun.countschool.word.WordExtractor;

import junit.framework.Assert;

/**
 * NLPAnalyzerTest.java
 * 
 * @author dearj
 */
public class WordExtractorTest {

    @Test
    public void getWordTest() {
        WordExtractor wordExtractor = new WordExtractor(FindSchoolType.HIGH_SCHOOL);

        String result = wordExtractor.getWordExcludePatternStr("고림고등학교", true, true);
        System.out.println(result);

        Assert.assertEquals("고림고", result);
    }
}
