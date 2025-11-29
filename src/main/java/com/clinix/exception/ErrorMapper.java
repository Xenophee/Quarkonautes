package com.clinix.exception;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ErrorMapper {
    ApiError toDTO(ApiException exception);
}
