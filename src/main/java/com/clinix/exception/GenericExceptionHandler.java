package com.clinix.exception;

import com.clinix.config.EnumApiStatus;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class GenericExceptionHandler implements ExceptionMapper<Throwable> {

    private static final Logger LOG = Logger.getLogger(GenericExceptionHandler.class.getName());

    private static final Response.Status SERVER_STATUS = Response.Status.INTERNAL_SERVER_ERROR;
    private static final EnumApiStatus API_STATUS = EnumApiStatus.UNEXPECTED_ERROR;

    private final ErrorMapper errorMapper;


    @Inject
    public GenericExceptionHandler(ErrorMapper errorMapper) {
        this.errorMapper = errorMapper;
    }

    @Override
    public Response toResponse(Throwable throwable) {

        LOG.errorf(throwable, SERVER_STATUS.name());

        ApiException exception = new ApiException(SERVER_STATUS, API_STATUS,
                "Une erreur inattendue est survenue. Veuillez réessayer plus tard.");

        return Response.status(exception.getServerStatus())
                .entity(errorMapper.toDTO(exception))
                .build();
    }
}
