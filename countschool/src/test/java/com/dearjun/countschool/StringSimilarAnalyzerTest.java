/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 16. First Draft
 */
package com.dearjun.countschool;

import org.junit.Test;

import com.dearjun.countschool.utils.StringSimilarAnalyzer;

import junit.framework.Assert;

/**
 * StringSimilarAnalyzerTest.java
 * 
 * @author dearj
 */
public class StringSimilarAnalyzerTest {

    @Test
    public void testSimilar() {
        String string_1 = "경기국제통상";
        String string_2 = "국제통상";

        int expectedCount = string_2.length();

        int result = StringSimilarAnalyzer.similar(string_1, string_2);

        System.out.println(result);

        Assert.assertEquals(expectedCount, result);
    }
}
