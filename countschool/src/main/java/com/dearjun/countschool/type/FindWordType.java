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

    public Pattern getFindPattern();

    public String getPatternStr();

    public String[] getDelCalibrationWordArr();

    public List<CalibrationWordVO> getCalibrationWordList();
}
