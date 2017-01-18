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
import java.util.regex.Pattern;

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
            CalibrationWordVO calibWordVO = new CalibrationWordVO();

            calibWordVO.setCalibSourceWord("여대");
            calibWordVO.setCalibDestWord("여자대");
            result.add(calibWordVO);

            return result;
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
    },
    ELEMENTARY_SCHOOL("초등학교") {

        @Override
        public List<CalibrationWordVO> getCalibrationWordList() {
            List<CalibrationWordVO> result = new ArrayList<CalibrationWordVO>();
            return result;
        }
    };

    private String findWordStr = null;
    private final String patternSeperateStr = "\\|";
    private final String[] delCalibrationWordArr = {"특성화", "인터넷", "여자", "남자"};

    FindSchoolType(String findWordStr) {
        this.findWordStr = findWordStr;
    }

    @Override
    public String getFindWordStr() {
        return this.findWordStr;
    }

    @Override
    public String getPatternStr() {
        return this.findWordStr + patternSeperateStr.replace("\\", "") + this.findWordStr.toCharArray()[0];
    }

    @Override
    public Pattern getFindPattern() {
        return Pattern.compile("^.{0,}(" + this.getPatternStr() + ")$", 2);
        //        return Pattern.compile("^.{0,}(" + this.findWordStr + ")$", 2);
    }

    @Override
    public String[] getDelCalibrationWordArr() {
        return this.delCalibrationWordArr;
    }

    @Override
    public abstract List<CalibrationWordVO> getCalibrationWordList();
}
