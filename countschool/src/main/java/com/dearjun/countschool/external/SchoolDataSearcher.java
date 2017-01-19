/**
 * Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * dearj 2017. 1. 18. First Draft
 */
package com.dearjun.countschool.external;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * SchoolDataSearcher.java
 * 
 * @author dearj
 */
public class SchoolDataSearcher implements OpenAPISearcher {

    private static final String OPEN_API_KEY = "d2c5d204c5b009d0680fbe26d61e03d3";
    private static final String OPEN_API_CONN_URL = "http://www.career.go.kr/cnet/openapi/getOpenApi?contentType=json&svcType=api&svcCode=SCHOOL";

    @Override
    public boolean checkExistSearchKeyword(String searchSchoolName, String openApiTypeKey) throws Exception {
        URL openApiURL = new URL(this.getOpenAPIURL(searchSchoolName, openApiTypeKey));
        URLConnection connnection = openApiURL.openConnection();
        BufferedReader resReader = null;
        boolean result = false;

        try {
            resReader = new BufferedReader(new InputStreamReader(connnection.getInputStream()));

            String line = null;
            StringBuffer response = new StringBuffer();

            while((line = resReader.readLine()) != null) {
                response.append(line);
            }

            JSONObject resJson = JSONObject.fromObject(response.toString()).getJSONObject("dataSearch");
            JSONArray resultArr = JSONArray.fromObject(resJson.getString("content"));

            if(!resultArr.isEmpty()) {
                result = true;
            }
        } catch(FileNotFoundException fne) {
            //            System.out.println("======= 학교 정보 검색 오픈API 서버 에러로 학교 이름 정합성 검사 없이 진행합니다. ========");
            result = true;
        } catch(Exception e) {
            throw new Exception(e);
        } finally {
            if(resReader != null) {
                resReader.close();
            }
        }

        return result;
    }

    private String getOpenAPIURL(String searchSchoolName, String openApiTypeKey) {
        StringBuffer urlBuffer = new StringBuffer();

        urlBuffer.append(OPEN_API_CONN_URL);
        urlBuffer.append("&apiKey=" + OPEN_API_KEY);
        urlBuffer.append("&gubun=" + openApiTypeKey);
        urlBuffer.append("&searchSchulNm=" + searchSchoolName);

        return urlBuffer.toString();
    }
}
