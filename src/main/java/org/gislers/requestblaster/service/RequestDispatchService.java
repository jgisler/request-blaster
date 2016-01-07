package org.gislers.requestblaster.service;

import org.gislers.requestblaster.model.EsbServiceRequest;
import org.gislers.requestblaster.model.EsbServiceResponse;

/**
 * Created by:   jgisle
 * Created date: 1/6/16
 */
public interface RequestDispatchService {

    EsbServiceResponse dispatchRequest( EsbServiceRequest esbServiceRequest );
}
