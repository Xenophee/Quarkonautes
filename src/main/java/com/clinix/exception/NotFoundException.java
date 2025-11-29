package com.clinix.exception;

import com.clinix.config.EnumApiStatus;
import jakarta.ws.rs.core.Response;

public class NotFoundException extends ApiException {

    private static final Response.Status SERVER_STATUS = Response.Status.NOT_FOUND;

    public NotFoundException(EnumApiStatus apiStatus) {
        super(SERVER_STATUS, apiStatus);
    }

    public NotFoundException(EnumApiStatus apiStatus, String message) {
        super(SERVER_STATUS, apiStatus, message);
    }
}
