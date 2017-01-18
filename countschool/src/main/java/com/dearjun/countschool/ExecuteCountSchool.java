package com.dearjun.countschool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dearjun.countschool.type.FindSchoolType;
import com.dearjun.countschool.utils.CommentToListMaker;
import com.dearjun.countschool.word.WordComparator;
import com.dearjun.countschool.word.WordExtractor;

public class ExecuteCountSchool {

    private static final int ALLOWED_SIMILAR_PERSANTAGE = 60;

    public static void main(String... args) throws Exception {
        String filePath = ExecuteCountSchool.class.getResource("/data/comments.csv").getPath();
        List<String> commentList = CommentToListMaker.convertFileToList(filePath);

        WordExtractor wordExtractor = new WordExtractor(FindSchoolType.MIDDLE_SCHOOL);
        List<String> analyzedWordList = wordExtractor.getAnalyedWordList(commentList);
        List<String> compareWordList = new ArrayList<String>();

        Collections.sort(analyzedWordList);
        compareWordList.addAll(analyzedWordList);

        String[] list = (String[]) analyzedWordList.toArray(new String[analyzedWordList.size()]);
        boolean addPatternWordToEnd = true;
        WordComparator wordComparator = new WordComparator(ALLOWED_SIMILAR_PERSANTAGE, addPatternWordToEnd,
                wordExtractor.getFindWordType());

        for(String firstString : list) {
            int count = 0;

            if(firstString == null) {
                continue;
            }

            int index = 0;
            for(String secondString : compareWordList) {
                boolean result = wordComparator.isCalibratedSimilarWord(firstString, secondString, wordExtractor);

                if(result) {
                    //                    list[index] = null;
                    count++;
                } else {
                    continue;
                }

                index++;
            }
            System.out.println(firstString + " : " + count);

        }

        System.out.println("highSchoolCount = " + analyzedWordList.size());
    }
}
