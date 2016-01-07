package org.gislers.requestblaster;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.gislers.requestblaster.config.RequestBlasterConfig;
import org.gislers.requestblaster.model.EsbServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by:   jgisle
 * Created date: 1/6/16
 */
@Component
public class Main {

    private static final Logger logger = LoggerFactory.getLogger( Main.class );



    public static void main( String[] args ) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RequestBlasterConfig.class);
        Main main = applicationContext.getBean( Main.class );
        main.run();
    }

    public void run() {

        EsbServiceRequest esbServiceRequest = new EsbServiceRequest();

        esbServiceRequest.setUrl( "http://localhost:7012/esb-digital-communication-push/http/send/" );

        esbServiceRequest.getHeaderMap().put( "envName", "any-ecn" );
        esbServiceRequest.getHeaderMap().put( "appName", "request-blaster" );
        esbServiceRequest.getHeaderMap().put( "Authorization", "Basic YnJhbmRVc2VyOjhuejNxZG5k" );

        esbServiceRequest.setPayload( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<ICPCommunicationRequest xmlns=\"urn:nike:integratedcommunications:communication\">" +
                "<Header><CommunicationMode>EMAIL</CommunicationMode><Category>Profile</Category>" +
                "<SubCategory>Welcome</SubCategory><EventType>NikeWelcome</EventType><Country>GB</Country>" +
                "<Language>en</Language><Priority>2</Priority><ErrorReportLevel>1</ErrorReportLevel>" +
                "<RetryMode>0</RetryMode><RequestDeliveryDate>2015-12-09T11:41:28.401Z</RequestDeliveryDate>" +
                "<Recipients><Recipient><DisplayName>Iryna1449661287066</DisplayName>" +
                "<EmailAddress>james.gisler@nike.com</EmailAddress></Recipient></Recipients></Header>" +
                "<Body><MessageContent><AdditionalMessageProperties><Property><Key>Category</Key><Value>Profile</Value>" +
                "</Property><Property><Key>FirstName</Key><Value>Iryna1449661287066</Value></Property>" +
                "<Property><Key>LastName</Key><Value>Surname1449661287066</Value></Property>" +
                "<Property><Key>ScreenName</Key><Value>Iryna1449661287066S215802510</Value></Property>" +
                "<Property><Key>Gender</Key><Value>1</Value></Property><Property><Key>ProfileId</Key>" +
                "<Value>14227163414</Value></Property><Property><Key>IsSwoosh</Key><Value>n</Value></Property>" +
                "<Property><Key>PostalCode</Key><Value>EC1</Value></Property></AdditionalMessageProperties>" +
                "</MessageContent></Body></ICPCommunicationRequest>" );


    }


}
