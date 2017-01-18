/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 14. First Draft
 */
package com.dearjun.countschool;

import java.util.List;

import com.dearjun.countschool.count.ElementarySchoolCounter;
import com.dearjun.countschool.count.HighSchoolCounter;
import com.dearjun.countschool.count.MiddleSchoolCounter;
import com.dearjun.countschool.count.SchoolCounter;
import com.dearjun.countschool.count.UniversityCounter;
import com.dearjun.countschool.utils.CommentToListMaker;

/**
 * ExecuteCountSchool.java
 * 
 * @author dearj
 */
public class ExecuteCountSchool {

    private static final int ALLOWED_SIMILAR_PERSANTAGE = 60;
    private static final int MIN_WORD_SIZE = 2;

    public static void main(String... args) throws Exception {
        String filePath = ExecuteCountSchool.class.getResource("/data/comments.csv").getPath();
        List<String> commentList = CommentToListMaker.convertFileToList(filePath);

        SchoolCounter elementarySchoolCounter = new ElementarySchoolCounter(ALLOWED_SIMILAR_PERSANTAGE, MIN_WORD_SIZE);
        SchoolCounter middleSchoolCounter = new MiddleSchoolCounter(ALLOWED_SIMILAR_PERSANTAGE, MIN_WORD_SIZE);
        SchoolCounter highSchoolCounter = new HighSchoolCounter(ALLOWED_SIMILAR_PERSANTAGE, MIN_WORD_SIZE);
        SchoolCounter universityCounter = new UniversityCounter(ALLOWED_SIMILAR_PERSANTAGE, MIN_WORD_SIZE);

        try {
            elementarySchoolCounter.printSchoolCount(commentList);
            middleSchoolCounter.printSchoolCount(commentList);
            highSchoolCounter.printSchoolCount(commentList);
            universityCounter.printSchoolCount(commentList);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
