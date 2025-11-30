package com.clinix.exception;

import com.clinix.config.EnumApiStatus;
import jakarta.ws.rs.core.Response;

public class ActionNotAllowed extends ApiException {

    private static final Response.Status SERVER_STATUS = Response.Status.CONFLICT;

    public ActionNotAllowed(EnumApiStatus apiStatus) {
        super(SERVER_STATUS, apiStatus);
    }

    public ActionNotAllowed(EnumApiStatus apiStatus, String message) {
        super(SERVER_STATUS, apiStatus, message);
    }
}