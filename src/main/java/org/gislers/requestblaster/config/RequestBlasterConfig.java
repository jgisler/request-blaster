package org.gislers.requestblaster.config;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Created by:   jgisle
 * Created date: 1/6/16
 */
@Configuration
@ComponentScan(basePackages="com.nike.digital.foundation.esb.tester")
@PropertySource("classpath:META-INF/request-blaster.properties")
public class RequestBlasterConfig {

    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyJmsProperties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PoolingHttpClientConnectionManager connectionManager() {
        PoolingHttpClientConnectionManager cxMgr = new PoolingHttpClientConnectionManager();
        cxMgr.setMaxTotal( Integer.parseInt(environment.getProperty("cxManager.max.total")) );
        cxMgr.setDefaultMaxPerRoute( Integer.parseInt(environment.getProperty("cxManager.default.max.per.route")) );
        return cxMgr;
    }
}
