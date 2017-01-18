/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 17. First Draft
 */
package com.dearjun.countschool.type;

import java.util.List;
import java.util.regex.Pattern;

import com.dearjun.countschool.vo.CalibrationWordVO;

/**
 * FindWordType.java
 * 
 * @author dearj
 */
public interface FindWordType {

    public String getFindWordStr();

    public String getPatternStr();

    public String getPatternSeperateStr();

    public String[] getDelCalibrationWordArr();

    public String[] getWordForFoundedWordCalib();

    public List<CalibrationWordVO> getCalibrationWordList();

    public default Pattern getFindPattern() {
        return Pattern.compile("^.{0,}(" + this.getPatternStr() + ")$", Pattern.CASE_INSENSITIVE);
    }
}
