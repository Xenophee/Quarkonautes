package com.clinix.exception;

import com.clinix.config.EnumApiStatus;
import jakarta.ws.rs.core.Response;

public class ApiException extends RuntimeException {

    private final Response.Status serverStatus;
    private final EnumApiStatus apiStatus;

    public ApiException(Response.Status serverStatus, EnumApiStatus apiStatus) {
        this.serverStatus = serverStatus;
        this.apiStatus = apiStatus;
    }

    public ApiException(Response.Status serverStatus, EnumApiStatus apiStatus, String message) {
        super(message);
        this.serverStatus = serverStatus;
        this.apiStatus = apiStatus;
    }

    public Response.Status getServerStatus() {
        return serverStatus;
    }

    public EnumApiStatus getApiStatus() {
        return apiStatus;
    }

}
