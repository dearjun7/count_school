package com.dearjun.countschool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dearjun.countschool.type.FindSchoolType;
import com.dearjun.countschool.utils.CommentToListMaker;
import com.dearjun.countschool.utils.NLPAnalyzer;
import com.dearjun.countschool.utils.StringSimilarAnalyzer;

public class ExecuteCountSchool {

    private static final int ALLOWED_SIMILAR_PERSANTAGE = 60;

    public static void main(String... args) throws Exception {
        String filePath = ExecuteCountSchool.class.getResource("/data/comments.csv").getPath();
        List<String> commentList = CommentToListMaker.convertFileToList(filePath);

        NLPAnalyzer nlpAlanyzer = new NLPAnalyzer(FindSchoolType.HIGH_SCHOOL);
        List<String> analyzedWordList = nlpAlanyzer.getAnalyedWordList(commentList);
        List<String> tmpWordList = new ArrayList<String>();

        Collections.sort(analyzedWordList);
        tmpWordList.addAll(analyzedWordList);

        String[] list = (String[]) analyzedWordList.toArray(new String[analyzedWordList.size()]);

        for(String analyzedWord : list) {
            int count = 0;

            if(analyzedWord == null) {
                continue;
            }

            int index = 0;
            for(String tmpWord : tmpWordList) {
                //                String firstString = analyzedWord.replace("고등학교", "").replace("특성화", "").replace("여자", "").replace("남자", "");
                //                String secondString = tmpWord.replace("고등학교", "").replace("특성화", "").replace("여자", "").replace("남자", "");

                String firstString = analyzedWord.replace("특성화", "").replace("인터넷", "").replace("상업", "").replace("여자", "").replace("남자",
                        "");
                String secondString = tmpWord.replace("특성화", "").replace("인터넷", "").replace("상업", "").replace("여자", "").replace("남자", "");

                firstString = nlpAlanyzer.getWordExcludePatternStr(firstString, "NNG");
                secondString = nlpAlanyzer.getWordExcludePatternStr(secondString, "NNG");

                int firstLength = firstString.length();
                int secondLength = secondString.length();

                if(StringSimilarAnalyzer.similar(firstString, secondString) >= ALLOWED_SIMILAR_PERSANTAGE) {
                    String biggerStr = firstLength > secondLength ? firstString : firstLength < secondLength ? secondString : firstString;
                    String smallerStr = firstLength > secondLength ? secondString : firstLength < secondLength ? firstString : secondString;

                    if(nlpAlanyzer.getWordExcludePatternStr(biggerStr, "NNG")
                            .contains(nlpAlanyzer.getWordExcludePatternStr(smallerStr, "NNG"))) {
                        //                        list[index] = null;
                        count++;
                    }
                }

                index++;
            }
            System.out.println(analyzedWord + " : " + count);

        }

        System.out.println("highSchoolCount = " + analyzedWordList.size());
    }
}
