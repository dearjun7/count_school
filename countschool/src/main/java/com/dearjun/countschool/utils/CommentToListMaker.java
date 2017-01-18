/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 14. First Draft
 */
package com.dearjun.countschool.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CommentToListMaker.java
 * 
 * @author dearj
 */
public class CommentToListMaker {

    /**
     * csv 기반 comment 파일을 List 객체로 변환하여 반환한다.<br>
     * 반환되는 List는 하나의 Array가 하나의 코멘트로 구성이 되며, 특수 문자 및 숫자, 영어를 지외하고 한글만 반환한다.
     * 
     * @param commentFilePath
     *            String - csv 파일 경로
     * @return ArrayList 타입의 한글 텍스트만 있는 comment가 반환된다. 입력된 csv 파일의 더블쿼테이션("")
     *         기준으로 줄바꿈 문자에 상관 없이 하나의 코멘트로 저장하여 구성되어 있다.
     * @throws Exception
     */
    public static List<String> convertFileToList(String commentFilePath) throws Exception {
        File commentFile = new File(commentFilePath);

        if(!commentFile.exists()) {
            throw new FileNotFoundException();
        }

        BufferedReader bufferedReader = null;
        List<String> commentList = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(commentFile));
            String commentLine = null;
            commentList = new ArrayList<String>();

            while((commentLine = bufferedReader.readLine()) != null) {
                commentList.addAll(CommentToListMaker.getRefinedCommentList(commentLine, bufferedReader));
            }
        } catch(Exception e) {
            throw new Exception(e);
        } finally {
            if(bufferedReader != null) {
                bufferedReader.close();
            }
        }

        return commentList;
    }

    private static List<String> getRefinedCommentList(String commentLine, BufferedReader bufferedReader) throws IOException {
        StringBuffer tmpCommentBuffer = new StringBuffer();
        String tmpComment = null;

        tmpCommentBuffer.append(commentLine);

        while(!commentLine.endsWith("\"")) {
            commentLine = bufferedReader.readLine();
            tmpCommentBuffer.append(commentLine);
        }

        String match = "[^가-힣\\s]";
        tmpComment = tmpCommentBuffer.toString().replaceAll("-", " ").replaceAll(match, "");

        List<String> resultList = new ArrayList<String>();

        if(!"".equals(tmpComment)) {
            resultList.add(tmpComment);
        }

        return resultList;
    }
}
