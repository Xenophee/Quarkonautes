package com.clinix.exception;

import com.clinix.config.EnumApiStatus;
import jakarta.ws.rs.core.Response;

public class AlreadyExistException extends ApiException {

    private static final Response.Status SERVER_STATUS = Response.Status.CONFLICT;

    public AlreadyExistException(EnumApiStatus apiStatus) {
        super(SERVER_STATUS, apiStatus);
    }

    public AlreadyExistException(EnumApiStatus apiStatus, String message) {
        super(SERVER_STATUS, apiStatus, message);
    }
}