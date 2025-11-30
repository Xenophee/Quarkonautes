package com.clinix.mapper;

import com.clinix.persistence.entity.Astronaut;
import com.clinix.persistence.projection.AstronautInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AstronautMapper {
    AstronautInfo toDTO(Astronaut ship);
}
