package com.dearjun.countschool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dearjun.countschool.utils.CommentToListMaker;
import com.dearjun.countschool.utils.NLPAnalyzer;
import com.dearjun.countschool.utils.StringSimilarAnalyzer;

public class ExecuteCountSchool {

    public static void main(String... args) throws Exception {
        String filePath = ExecuteCountSchool.class.getResource("/data/comments.csv").getPath();
        List<String> commentList = CommentToListMaker.convertFileToList(filePath);
        String patternStr = "고등학교";

        NLPAnalyzer nlpAlanyzer = new NLPAnalyzer(patternStr);
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
                String firstString = analyzedWord.replace("고등학교", "").replace("특성화", "").replace("여자", "").replace("남자", "");
                String secondString = tmpWord.replace("고등학교", "").replace("특성화", "").replace("여자", "").replace("남자", "");
                int firstLength = firstString.length();
                int secondLength = firstString.length();

                if(StringSimilarAnalyzer.similar(firstString, secondString) >= 60) {
                    String biggerStr = firstLength > secondLength ? firstString : firstLength < secondLength ? secondString : firstString;
                    String smallerStr = firstLength > secondLength ? secondString : firstLength < secondLength ? firstString : secondString;

                    if(nlpAlanyzer.getWord(biggerStr).contains(nlpAlanyzer.getWord(smallerStr))) {
                        list[index] = null;
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
