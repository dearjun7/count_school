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
    UNIVERSITY("대학교") {

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

        @Override
        public String getSearchTypeKey() {
            return univOpenApiTypeKey;
        }

        @Override
        public List<CalibrationWordVO> getCalibrationLocationList() {
            List<CalibrationWordVO> result = new ArrayList<CalibrationWordVO>();
            CalibrationWordVO gyunbukVO = new CalibrationWordVO();

            gyunbukVO.setCalibSourceWord("대구경북");
            gyunbukVO.setCalibDestWord("경북");
            result.add(gyunbukVO);

            CalibrationWordVO sungshinVO = new CalibrationWordVO();

            sungshinVO.setCalibSourceWord("서울성신");
            sungshinVO.setCalibDestWord("성신");
            result.add(sungshinVO);

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
            artWordVO.setCalibDestWord("예술고등학교");
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

        @Override
        public String getSearchTypeKey() {
            return highOpenApiTypeKey;
        }

        @Override
        public List<CalibrationWordVO> getCalibrationLocationList() {
            List<CalibrationWordVO> result = new ArrayList<CalibrationWordVO>();
            CalibrationWordVO gyunggiVO = new CalibrationWordVO();

            gyunggiVO.setCalibSourceWord("경기도");
            gyunggiVO.setCalibDestWord("");
            result.add(gyunggiVO);

            CalibrationWordVO howonVO = new CalibrationWordVO();

            howonVO.setCalibSourceWord("동호원");
            howonVO.setCalibDestWord("호원");
            result.add(howonVO);

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

        @Override
        public String[] getWordForFoundedWordCalib() {
            return null;
        }

        @Override
        public String getPatternStr() {
            String seperater = this.getPatternSeperateStr();
            return this.getFindWordStr() + seperater + this.getFindWordStr().toCharArray()[0] + seperater + this.getExceptedWord();
        }

        @Override
        public String getSearchTypeKey() {
            return middOpenApiTypeKey;
        }

        @Override
        public List<CalibrationWordVO> getCalibrationLocationList() {
            List<CalibrationWordVO> result = new ArrayList<CalibrationWordVO>();
            CalibrationWordVO hwasungVO = new CalibrationWordVO();

            hwasungVO.setCalibSourceWord("경기도화성시");
            hwasungVO.setCalibDestWord("");
            result.add(hwasungVO);

            CalibrationWordVO thanksVO = new CalibrationWordVO();

            thanksVO.setCalibSourceWord("감사");
            thanksVO.setCalibDestWord("");
            result.add(thanksVO);

            CalibrationWordVO yungchunVO = new CalibrationWordVO();

            yungchunVO.setCalibSourceWord("경북영천");
            yungchunVO.setCalibDestWord("영천");
            result.add(yungchunVO);

            CalibrationWordVO goVO = new CalibrationWordVO();

            goVO.setCalibSourceWord("고고고");
            goVO.setCalibDestWord("");
            result.add(goVO);

            CalibrationWordVO whiteVO = new CalibrationWordVO();

            whiteVO.setCalibSourceWord("경산하양");
            whiteVO.setCalibDestWord("하양");
            result.add(whiteVO);

            CalibrationWordVO yunmuVO = new CalibrationWordVO();

            yunmuVO.setCalibSourceWord("수원연무");
            yunmuVO.setCalibDestWord("연무");
            result.add(yunmuVO);

            CalibrationWordVO emokVO = new CalibrationWordVO();

            emokVO.setCalibSourceWord("수원이목");
            emokVO.setCalibDestWord("이목");
            result.add(yunmuVO);

            CalibrationWordVO studentVO = new CalibrationWordVO();

            studentVO.setCalibSourceWord("학생");
            studentVO.setCalibDestWord("");
            result.add(studentVO);

            CalibrationWordVO univVO_1 = new CalibrationWordVO();

            univVO_1.setCalibSourceWord("대사범");
            univVO_1.setCalibDestWord("대학교사범");
            result.add(univVO_1);

            CalibrationWordVO univVO_2 = new CalibrationWordVO();

            univVO_2.setCalibSourceWord("대구사대부");
            univVO_2.setCalibDestWord("경북대학교사범대학부설");
            result.add(univVO_2);

            CalibrationWordVO locationVO_1 = new CalibrationWordVO();

            locationVO_1.setCalibSourceWord("서울");
            locationVO_1.setCalibDestWord("");
            result.add(locationVO_1);

            CalibrationWordVO locationVO_2 = new CalibrationWordVO();

            locationVO_2.setCalibSourceWord("부산");
            locationVO_2.setCalibDestWord("");
            result.add(locationVO_2);

            CalibrationWordVO locationVO_3 = new CalibrationWordVO();

            locationVO_3.setCalibSourceWord("분당");
            locationVO_3.setCalibDestWord("");
            result.add(locationVO_3);

            CalibrationWordVO locationVO_4 = new CalibrationWordVO();

            locationVO_4.setCalibSourceWord("구로구");
            locationVO_4.setCalibDestWord("");
            result.add(locationVO_4);

            CalibrationWordVO locationVO_5 = new CalibrationWordVO();

            locationVO_5.setCalibSourceWord("고양시");
            locationVO_5.setCalibDestWord("");
            result.add(locationVO_5);

            CalibrationWordVO locationVO_6 = new CalibrationWordVO();

            locationVO_6.setCalibSourceWord("대구");
            locationVO_6.setCalibDestWord("");
            result.add(locationVO_6);

            CalibrationWordVO locationVO_7 = new CalibrationWordVO();

            locationVO_7.setCalibSourceWord("동타");
            locationVO_7.setCalibDestWord("");
            result.add(locationVO_7);

            CalibrationWordVO locationVO_8 = new CalibrationWordVO();

            locationVO_8.setCalibSourceWord("성남은행");
            locationVO_8.setCalibDestWord("은행");
            result.add(locationVO_8);

            CalibrationWordVO locationVO_9 = new CalibrationWordVO();

            locationVO_9.setCalibSourceWord("인천서구");
            locationVO_9.setCalibDestWord("");
            result.add(locationVO_9);

            CalibrationWordVO locationVO_10 = new CalibrationWordVO();

            locationVO_10.setCalibSourceWord("수원시");
            locationVO_10.setCalibDestWord("");
            result.add(locationVO_10);

            CalibrationWordVO locationVO_11 = new CalibrationWordVO();

            locationVO_11.setCalibSourceWord("용인");
            locationVO_11.setCalibDestWord("");
            result.add(locationVO_11);
            return result;
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

        @Override
        public String getSearchTypeKey() {
            return elemOpenApiTypeKey;
        }

        @Override
        public List<CalibrationWordVO> getCalibrationLocationList() {
            List<CalibrationWordVO> result = new ArrayList<CalibrationWordVO>();
            CalibrationWordVO thanksVO = new CalibrationWordVO();

            thanksVO.setCalibSourceWord("감사");
            thanksVO.setCalibDestWord("");
            result.add(thanksVO);

            CalibrationWordVO studentVO = new CalibrationWordVO();

            studentVO.setCalibSourceWord("학생");
            studentVO.setCalibDestWord("");
            result.add(studentVO);

            studentVO.setCalibSourceWord("학생");
            studentVO.setCalibDestWord("");
            result.add(studentVO);

            CalibrationWordVO locationVO_1 = new CalibrationWordVO();

            locationVO_1.setCalibSourceWord("상주");
            locationVO_1.setCalibDestWord("");
            result.add(locationVO_1);

            CalibrationWordVO locationVO_2 = new CalibrationWordVO();

            locationVO_2.setCalibSourceWord("제주");
            locationVO_2.setCalibDestWord("");
            result.add(locationVO_2);

            return result;
        }
    };

    private String findWordStr = null;
    private final String defaultExceptedWord = "학생";
    private final String patternSeperateStr = "|";
    private final String[] delCalibrationWordArr = {"등학생", "학생", "특성화", "인터넷", "여자", "남자", "대사범", "감사"};

    protected final String elemOpenApiTypeKey = "elem_list";
    protected final String middOpenApiTypeKey = "midd_list";
    protected final String highOpenApiTypeKey = "high_list";
    protected final String univOpenApiTypeKey = "univ_list";

    FindSchoolType(String findWordStr) {
        this.findWordStr = findWordStr;
    }

    @Override
    public abstract String getSearchTypeKey();

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

    @Override
    public abstract List<CalibrationWordVO> getCalibrationLocationList();

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
