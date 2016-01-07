package org.gislers.requestblaster.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by:   jgisle
 * Created date: 1/7/16
 */
@Service
public class HttpClientFactory {

    private PoolingHttpClientConnectionManager cxMgr;

    @Autowired
    public void setCxMgr(PoolingHttpClientConnectionManager cxMgr) {
        this.cxMgr = cxMgr;
    }

    @Autowired
    public CloseableHttpClient createCloseableHttpClient() {
        return HttpClients.custom()
                .setConnectionManager( cxMgr )
                .build();
    }
}
