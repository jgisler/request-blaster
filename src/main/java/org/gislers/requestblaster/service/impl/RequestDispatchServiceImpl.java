package org.gislers.requestblaster.service.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.gislers.requestblaster.config.HttpClientFactory;
import org.gislers.requestblaster.exception.RequestBlasterException;
import org.gislers.requestblaster.model.EsbServiceRequest;
import org.gislers.requestblaster.model.EsbServiceResponse;
import org.gislers.requestblaster.service.RequestDispatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by:   jgisle
 * Created date: 1/6/16
 */
@Service
public class RequestDispatchServiceImpl implements RequestDispatchService {

    private static final Logger logger = LoggerFactory.getLogger( RequestDispatchServiceImpl.class );

    private HttpClientFactory httpClientFactory;

    @Autowired
    public void setHttpClientFactory(HttpClientFactory httpClientFactory) {
        this.httpClientFactory = httpClientFactory;
    }

    public EsbServiceResponse dispatchRequest( EsbServiceRequest esbServiceRequest ) {

        CloseableHttpClient httpClient = httpClientFactory.createCloseableHttpClient();
        HttpContext httpContext = HttpClientContext.create();

        CloseableHttpResponse httpResponse = null;
        try {
            HttpUriRequest httpRequest = buildHttpRequest( esbServiceRequest );
            httpResponse = httpClient.execute( httpRequest, httpContext );
            return buildServiceResponse( httpResponse );
        }
        catch( IOException e ) {
            logger.error( ExceptionUtils.getRootCauseMessage(e) );
            throw new RequestBlasterException(e);
        }
        finally {
            if( httpResponse != null ) {
                try {
                    httpResponse.close();
                }
                catch (IOException e) {
                    logger.error(ExceptionUtils.getRootCauseMessage(e));
                }
            }

            if( httpClient != null ) {
                try {
                    httpClient.close();
                }
                catch (IOException e) {
                    logger.error(ExceptionUtils.getRootCauseMessage(e));
                }
            }
        }
    }

    EsbServiceResponse buildServiceResponse( CloseableHttpResponse httpResponse ) throws IOException {
        EsbServiceResponse serviceResponse = new EsbServiceResponse();
        if( httpResponse != null ) {
            serviceResponse.setHttpStatus( httpResponse.getStatusLine().getStatusCode() );

            HttpEntity httpEntity = httpResponse.getEntity();
            if( httpEntity != null ) {
                serviceResponse.setResponsePayload( EntityUtils.toString(httpEntity) );
            }
        }
        return serviceResponse;
    }

    HttpUriRequest buildHttpRequest( EsbServiceRequest esbServiceRequest ) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost( esbServiceRequest.getUrl() );
        httpPost.setHeaders( buildHttpHeaders(esbServiceRequest) );
        httpPost.setEntity( buildHttpEntity(esbServiceRequest) );
        return httpPost;
    }

    Header[] buildHttpHeaders( EsbServiceRequest esbServiceRequest ) {
        Map<String, String> headerMap = esbServiceRequest.getHeaderMap();

        List<Header> headers = new ArrayList<>(headerMap.size());
        for( String key : headerMap.keySet() ) {
            headers.add( new BasicHeader(key, headerMap.get(key)) );
        }

        Header[] headerArray = new Header[ headers.size() ];
        return headers.toArray( headerArray );
    }

    HttpEntity buildHttpEntity( EsbServiceRequest esbServiceRequest ) throws UnsupportedEncodingException {
        return new ByteArrayEntity(esbServiceRequest.getPayload().getBytes("UTF-8"));
    }
}
