package org.gislers.requestblaster.service.impl;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.gislers.requestblaster.config.HttpClientFactory;
import org.gislers.requestblaster.model.EsbServiceRequest;
import org.gislers.requestblaster.model.EsbServiceResponse;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by:   jgisle
 * Created date: 1/7/16
 */
public class RequestDispatchServiceImplTest {

    @Test
    public void testDispatchRequest() throws Exception {

        StatusLine mockStatusLine = mock(StatusLine.class);
        when( mockStatusLine.getStatusCode() ).thenReturn( 202 );

        CloseableHttpResponse mockHttpResponse = mock( CloseableHttpResponse.class );
        when( mockHttpResponse.getStatusLine() ).thenReturn( mockStatusLine );

        CloseableHttpClient mockHttpClient = mock( CloseableHttpClient.class );
        when( mockHttpClient.execute(any(HttpUriRequest.class), any(HttpContext.class)) )
                .thenReturn( mockHttpResponse );

        HttpClientFactory mockHttpClientFactory = mock( HttpClientFactory.class );
        when( mockHttpClientFactory.createCloseableHttpClient() ).thenReturn( mockHttpClient );

        EsbServiceRequest testServiceReq = new EsbServiceRequest();
        testServiceReq.setUrl("http://esbdevv-esb-digital.nikedev.com/esb-sim/echo/200");
        testServiceReq.setPayload( "test payload" );

        RequestDispatchServiceImpl requestDispatchService = new RequestDispatchServiceImpl();
        requestDispatchService.setHttpClientFactory( mockHttpClientFactory );

        EsbServiceResponse serviceResponse = requestDispatchService.dispatchRequest( testServiceReq );
        verify( mockHttpClient ).execute( any(HttpUriRequest.class), any(HttpContext.class) );
        verify( mockHttpResponse ).close();
    }

    @Test
    public void testBuildServiceResponse() throws Exception {
        int httpStatus = 202;

        HttpEntity mockHttpEntity = mock( HttpEntity.class );

        StatusLine mockStatusLine = mock( StatusLine.class );
        when( mockStatusLine.getStatusCode() ).thenReturn( httpStatus );

        CloseableHttpResponse mockHttpResponse = mock( CloseableHttpResponse.class );
        when( mockHttpResponse.getStatusLine() ).thenReturn( mockStatusLine );
        when( mockHttpResponse.getEntity() ).thenReturn( mockHttpEntity );

        RequestDispatchServiceImpl requestDispatchService = new RequestDispatchServiceImpl();
        EsbServiceResponse testEsbServiceResponse = requestDispatchService.buildServiceResponse( mockHttpResponse );

        verify( mockStatusLine ).getStatusCode();
        verify( mockHttpResponse ).getStatusLine();
        verify( mockHttpResponse ).getEntity();

        assertEquals( httpStatus, testEsbServiceResponse.getHttpStatus() );
    }

    @Test
    public void testBuildHttpRequest() throws Exception {

        String url = "http://esbdevv-esb-digital.nikedev.com/esb-sim/echo/200";
        String payload = "test payload";

        EsbServiceRequest testServiceReq = new EsbServiceRequest();
        testServiceReq.setUrl( url );
        testServiceReq.setPayload( payload );

        RequestDispatchServiceImpl requestDispatchService = new RequestDispatchServiceImpl();
        HttpPost httpUriRequest = (HttpPost) requestDispatchService.buildHttpRequest( testServiceReq );
        assertEquals( url, httpUriRequest.getURI().toASCIIString() );
        assertEquals( payload, EntityUtils.toString(httpUriRequest.getEntity()) );
    }

    @Test
    public void testBuildHttpHeaders() throws Exception {

        EsbServiceRequest testServiceReq = new EsbServiceRequest();
        testServiceReq.getHeaderMap().put( "header-0", "value-0" );
        testServiceReq.getHeaderMap().put( "header-1", "value-1" );

        RequestDispatchServiceImpl requestDispatchService = new RequestDispatchServiceImpl();
        Header[] headers = requestDispatchService.buildHttpHeaders( testServiceReq );

        for( int i=0; i<headers.length; i++ ) {
            assertEquals( "header-"+i, headers[i].getName() );
            assertEquals( "value-"+i, headers[i].getValue() );
        }
    }

    @Test
    public void testBuildHttpEntity() throws Exception {

        String testPayload = "test payload";

        EsbServiceRequest testServiceReq = new EsbServiceRequest();
        testServiceReq.setPayload( testPayload );

        RequestDispatchServiceImpl requestDispatchService = new RequestDispatchServiceImpl();

        HttpEntity httpEntity = requestDispatchService.buildHttpEntity( testServiceReq );
        assertEquals( testPayload, EntityUtils.toString(httpEntity) );
    }
}