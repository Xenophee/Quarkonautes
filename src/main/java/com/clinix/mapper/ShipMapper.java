package com.clinix.mapper;


import com.clinix.persistence.entity.Ship;
import com.clinix.persistence.projection.ShipInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ShipMapper {
    ShipInfo toDTO(Ship ship);
}
