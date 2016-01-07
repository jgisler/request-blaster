package org.gislers.requestblaster.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by:   jgisle
 * Created date: 1/7/16
 */
public class EsbServiceResponse {

    private int httpStatus;
    private String responsePayload;
    private Map<String, String> headerMap = new HashMap<>();

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getResponsePayload() {
        return responsePayload;
    }

    public void setResponsePayload(String responsePayload) {
        this.responsePayload = responsePayload;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }
}
