package com.clinix.exception;

import com.clinix.config.EnumApiStatus;
import jakarta.ws.rs.core.Response;

public record ApiError(
        Response.Status serverStatus,
        EnumApiStatus apiStatus,
        String message
) { }
