/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 16. First Draft
 */
package com.dearjun.countschool;

import org.junit.Test;

import com.dearjun.countschool.type.FindSchoolType;
import com.dearjun.countschool.utils.NLPAnalyzer;

import junit.framework.Assert;

/**
 * NLPAnalyzerTest.java
 * 
 * @author dearj
 */
public class NLPAnalyzerTest {

    @Test
    public void getWordTest() {
        NLPAnalyzer nlpAlanyzer = new NLPAnalyzer(FindSchoolType.HIGH_SCHOOL);

        System.out.println(nlpAlanyzer.getWordExcludePatternStr("고림고등학교", "NNG"));

        Assert.assertTrue(true);
    }
}
