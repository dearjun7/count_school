package com.dearjun.countschool;

import java.util.List;
import java.util.regex.Pattern;


public class Test {
	public static void main(String... args) throws Exception{
		String filePath = Test.class.getResource("/data/comments.csv").getPath();
		List<String> commentList = CommentToListMaker.convertFileToList(filePath);
		String patternStr = "고등학교|고";
		Pattern highSchoolPattern = Pattern.compile("^.{0,}(" + patternStr + ")$", 2);
		
		NLPAnalyzer nlpAlanyzer = new NLPAnalyzer(patternStr.toCharArray());
		List<String> analyzedWordList = nlpAlanyzer.getAnalyedWordList(commentList, highSchoolPattern);
		
		System.out.println("highSchoolCount = " + analyzedWordList.size());
	}
}
