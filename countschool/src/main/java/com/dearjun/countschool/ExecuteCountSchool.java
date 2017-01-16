package com.dearjun.countschool;

import java.util.List;

import com.dearjun.countschool.utils.CommentToListMaker;
import com.dearjun.countschool.utils.NLPAnalyzer;

public class ExecuteCountSchool {

    public static void main(String... args) throws Exception {
        String filePath = ExecuteCountSchool.class.getResource("/data/comments.csv").getPath();
        List<String> commentList = CommentToListMaker.convertFileToList(filePath);
        String patternStr = "고등학교";

        NLPAnalyzer nlpAlanyzer = new NLPAnalyzer(patternStr);
        List<String> analyzedWordList = nlpAlanyzer.getAnalyedWordList(commentList);

        for(String analyzedWord : analyzedWordList) {
            System.out.println(analyzedWord);
        }

        System.out.println("highSchoolCount = " + analyzedWordList.size());
    }
}
