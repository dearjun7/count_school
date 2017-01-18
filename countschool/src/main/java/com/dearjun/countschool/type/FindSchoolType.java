/**
 * ""
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 17. First Draft
 */
package com.dearjun.countschool.type;

import java.util.ArrayList;
import java.util.List;

import com.dearjun.countschool.vo.CalibrationWordVO;

/**
 * FindSchoolType.java
 * 
 * @author dearj
 */
public enum FindSchoolType implements FindWordType {
    UNIVERSIRY("대학교") {

        @Override
        public List<CalibrationWordVO> getCalibrationWordList() {
            List<CalibrationWordVO> result = new ArrayList<CalibrationWordVO>();
            CalibrationWordVO girlWordVO = new CalibrationWordVO();

            girlWordVO.setCalibSourceWord("여대");
            girlWordVO.setCalibDestWord("여자대");
            result.add(girlWordVO);

            return result;
        }

        @Override
        public String[] getWordForFoundedWordCalib() {
            return new String[]{"학부", "부속", "부설", "병설"};
        }

        @Override
        public String getPatternStr() {
            String seperater = this.getPatternSeperateStr();
            return this.getFindWordStr() + seperater + this.getFindWordStr().toCharArray()[0] + seperater + this.getExceptedWord();
        }
    },
    HIGH_SCHOOL("고등학교") {

        @Override
        public List<CalibrationWordVO> getCalibrationWordList() {
            List<CalibrationWordVO> result = new ArrayList<CalibrationWordVO>();
            CalibrationWordVO girlWordVO = new CalibrationWordVO();

            girlWordVO.setCalibSourceWord("여고");
            girlWordVO.setCalibDestWord("여자고등학교");
            result.add(girlWordVO);

            CalibrationWordVO boyWordVO = new CalibrationWordVO();

            boyWordVO.setCalibSourceWord("남고");
            boyWordVO.setCalibDestWord("남자고등학교");
            result.add(boyWordVO);

            CalibrationWordVO artWordVO = new CalibrationWordVO();

            artWordVO.setCalibSourceWord("예고");
            artWordVO.setCalibDestWord("");
            result.add(artWordVO);

            return result;
        }

        @Override
        public String[] getWordForFoundedWordCalib() {
            return null;
        }

        @Override
        public String getPatternStr() {
            String seperater = this.getPatternSeperateStr();
            return this.getFindWordStr() + seperater + this.getFindWordStr().toCharArray()[0] + seperater + this.getExceptedWord();
        }
    },
    MIDDLE_SCHOOL("중학교") {

        @Override
        public List<CalibrationWordVO> getCalibrationWordList() {
            List<CalibrationWordVO> result = new ArrayList<CalibrationWordVO>();
            CalibrationWordVO girlWordVO = new CalibrationWordVO();

            girlWordVO.setCalibSourceWord("여중");
            girlWordVO.setCalibDestWord("여자중");
            result.add(girlWordVO);

            CalibrationWordVO boyWordVO = new CalibrationWordVO();

            boyWordVO.setCalibSourceWord("남중");
            boyWordVO.setCalibDestWord("남자중");
            result.add(boyWordVO);

            return result;
        }

        @Override
        public String[] getWordForFoundedWordCalib() {
            return null;
        }

        @Override
        public String getPatternStr() {
            String seperater = this.getPatternSeperateStr();
            return this.getFindWordStr() + seperater + this.getFindWordStr().toCharArray()[0] + seperater + this.getExceptedWord();
        }
    },
    ELEMENTARY_SCHOOL("초등학교") {

        @Override
        public List<CalibrationWordVO> getCalibrationWordList() {
            List<CalibrationWordVO> result = new ArrayList<CalibrationWordVO>();
            return result;
        }

        @Override
        public String[] getWordForFoundedWordCalib() {
            return null;
        }

        @Override
        public String getPatternStr() {
            String seperater = this.getPatternSeperateStr();
            return this.getFindWordStr() + seperater + this.getFindWordStr().toCharArray()[0] + seperater + this.getExceptedWord();
        }
    };

    private String findWordStr = null;
    private String exceptedWord = null;
    private final String defaultExceptedWord = "학생";
    private final String patternSeperateStr = "|";
    private final String[] delCalibrationWordArr = {"등학생", "학생", "특성화", "인터넷", "여자", "남자", "대사범", "감사"};

    FindSchoolType(String findWordStr) {
        this.findWordStr = findWordStr;
    }

    @Override
    public String getFindWordStr() {
        return this.findWordStr;
    }

    @Override
    public abstract String getPatternStr();

    @Override
    public String getPatternSeperateStr() {
        return this.patternSeperateStr;
    }

    @Override
    public String[] getDelCalibrationWordArr() {
        return this.delCalibrationWordArr;
    }

    @Override
    public abstract String[] getWordForFoundedWordCalib();

    @Override
    public abstract List<CalibrationWordVO> getCalibrationWordList();

    protected String getExceptedWord() {
        String findWord = this.getFindWordStr();
        String keyWord = "등";
        String result = null;

        if(findWord.contains(keyWord)) {
            result = String.valueOf(findWord.toCharArray()[0]) + keyWord;
        } else {
            result = String.valueOf(findWord.toCharArray()[0]);
        }

        return result + this.defaultExceptedWord;
    }
}
