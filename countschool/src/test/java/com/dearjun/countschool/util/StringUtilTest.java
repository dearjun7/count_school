/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 16. First Draft
 */
package com.dearjun.countschool.util;

import org.junit.Test;

import com.dearjun.countschool.utils.StringUtil;

import junit.framework.Assert;

/**
 * StringSimilarAnalyzerTest.java
 * 
 * @author dearj
 */
public class StringUtilTest {

    @Test
    public void testSimilar() {
        String string_1 = "청라인천체육고";
        String string_2 = "택시타인천체육고";

        int expectedCount = string_2.length();

        int result = StringUtil.similar(string_1, string_2);

        System.out.println(result);

        Assert.assertEquals(expectedCount, result);
    }
}
