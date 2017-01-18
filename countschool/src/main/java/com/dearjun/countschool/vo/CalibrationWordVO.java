/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 18. First Draft
 */
package com.dearjun.countschool.vo;

import java.io.Serializable;

/**
 * CalibrationWordVO.java
 * 
 * @author dearj
 */
public class CalibrationWordVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8055918072388283368L;

    private String calibSourceWord = null;
    private String calibDestWord = null;

    /**
     * @return the calibSourceWord
     */
    public String getCalibSourceWord() {
        return calibSourceWord;
    }

    /**
     * @param calibSourceWord
     *            the calibSourceWord to set
     */
    public void setCalibSourceWord(String calibSourceWord) {
        this.calibSourceWord = calibSourceWord;
    }

    /**
     * @return the calibDestWord
     */
    public String getCalibDestWord() {
        return calibDestWord;
    }

    /**
     * @param calibDestWord
     *            the calibDestWord to set
     */
    public void setCalibDestWord(String calibDestWord) {
        this.calibDestWord = calibDestWord;
    }
}
