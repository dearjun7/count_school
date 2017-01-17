package com.dearjun.countschool.type;

public enum FindSchoolType {
    UNIVERSIRY("대학교"),
    HIGH_SCHOOL("고등학교"),
    MIDDLE_SCHOOL("중학교"),
    ELEMENTARY_SCHOOL("초등학교");

    private String schoolTypeStr = null;

    FindSchoolType(String schoolTypeStr) {
        this.schoolTypeStr = schoolTypeStr;
    }

    public String getSchoolTypeStr() {
        return this.schoolTypeStr;
    }
}
