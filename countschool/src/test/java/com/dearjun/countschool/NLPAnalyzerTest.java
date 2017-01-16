/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 16. First Draft
 */
package com.dearjun.countschool;

import org.junit.Test;

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
        String patternStr = "고등학교";
        NLPAnalyzer nlpAlanyzer = new NLPAnalyzer(patternStr);

        System.out.println(nlpAlanyzer.getWord("부천경기국제통상"));

        Assert.assertTrue(true);
    }
}
