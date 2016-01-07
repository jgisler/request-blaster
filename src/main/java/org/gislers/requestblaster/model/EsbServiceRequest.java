package org.gislers.requestblaster.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by:   jgisle
 * Created date: 1/7/16
 */
public class EsbServiceRequest {

    private String url;
    private String payload;
    private Map<String, String> headerMap = new HashMap<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }
}
