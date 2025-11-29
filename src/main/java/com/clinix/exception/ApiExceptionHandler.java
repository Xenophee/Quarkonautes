package com.clinix.exception;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ApiExceptionHandler implements ExceptionMapper<ApiException> {

    private final ErrorMapper errorMapper;

    @Inject
    public ApiExceptionHandler(ErrorMapper errorMapper) {
        this.errorMapper = errorMapper;
    }

    @Override
    public Response toResponse(ApiException exception) {

        return Response.status(exception.getServerStatus())
                .entity(errorMapper.toDTO(exception))
                .build();
    }
}
