package org.gislers.requestblaster.exception;

/**
 * Created by:   jgisle
 * Created date: 1/7/16
 */
public class RequestBlasterException extends RuntimeException {

    public RequestBlasterException() {
        super();
    }

    public RequestBlasterException(String message) {
        super(message);
    }

    public RequestBlasterException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestBlasterException(Throwable cause) {
        super(cause);
    }
}
