/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 18. First Draft
 */
package com.dearjun.countschool.external;

import org.junit.Assert;
import org.junit.Test;

import com.dearjun.countschool.type.FindSchoolType;

/**
 * SchoolDataSeacherTest.java
 * 
 * @author dearj
 */
public class SchoolDataSeacherTest {

    @Test
    public void testCheckExistSearchKeyword() {
        SchoolDataSearcher searcher = new SchoolDataSearcher();
        String searchSchoolName = "영여고";
        boolean result = false;
        try {
            result = searcher.checkExistSearchKeyword(searchSchoolName, FindSchoolType.HIGH_SCHOOL.getSearchTypeKey());
        } catch(Exception e) {
            e.printStackTrace();
        }

        Assert.assertTrue(result);
    }
}
