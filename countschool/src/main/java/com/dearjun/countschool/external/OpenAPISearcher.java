/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 18. First Draft
 */
package com.dearjun.countschool.external;

/**
 * OpenAPISearcher.java
 * 
 * @author dearj
 */
public interface OpenAPISearcher {

    public boolean checkExistSearchKeyword(String searchKeyword, String OpenApiTypeKey) throws Exception;
}
