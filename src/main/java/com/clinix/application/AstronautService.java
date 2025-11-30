package com.clinix.application;


import com.clinix.config.EnumApiStatus;
import com.clinix.exception.NotFoundException;
import com.clinix.mapper.AstronautMapper;
import com.clinix.persistence.entity.Astronaut;
import com.clinix.persistence.entity.Ship;
import com.clinix.persistence.projection.AstronautInfo;
import com.clinix.persistence.repository.AstronautRepository;
import com.clinix.persistence.repository.ShipRepository;
import com.clinix.web.dto.request.AstronautUpsert;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.UUID;

@ApplicationScoped
public class AstronautService {

    private static final Logger LOG = Logger.getLogger(AstronautService.class);

    private final AstronautRepository astronautRepository;
    private final ShipRepository shipRepository;
    private final AstronautMapper astronautMapper;

    @Inject
    public AstronautService(AstronautRepository astronautRepository, ShipRepository shipRepository, AstronautMapper astronautMapper) {
        this.astronautRepository = astronautRepository;
        this.shipRepository = shipRepository;
        this.astronautMapper = astronautMapper;
    }

    public AstronautInfo findByUuid(UUID uuid) {
        return astronautRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(EnumApiStatus.NOT_FOUND));
    }

    @Transactional
    public AstronautInfo create(AstronautUpsert astronautInsert) {

        Long shipId = shipRepository.getIdByUuid(astronautInsert.shipUuid())
                .orElseThrow(() -> new NotFoundException(EnumApiStatus.NOT_FOUND));

        Ship ship = shipRepository.getEntityManager().getReference(Ship.class, shipId);

        Astronaut astronaut = new Astronaut();
        astronaut.setNickname(astronautInsert.nickname());
        astronaut.setShip(ship);
        astronautRepository.persist(astronaut);

        return astronautMapper.toDTO(astronaut);
    }

    @Transactional
    public AstronautInfo update(UUID uuid, AstronautUpsert astronautUpdate) {

        Long astronautId = astronautRepository.getIdByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(EnumApiStatus.NOT_FOUND));

        Long shipId = shipRepository.getIdByUuid(astronautUpdate.shipUuid())
                .orElseThrow(() -> new NotFoundException(EnumApiStatus.NOT_FOUND));

        Ship ship = shipRepository.getEntityManager().getReference(Ship.class, shipId);

        astronautRepository.update(astronautId, astronautUpdate.nickname(), ship);

        return findByUuid(uuid);
    }


    @Transactional
    public void delete(UUID uuid) {
        boolean deleted = astronautRepository.deleteByUuid(uuid);
        if (!deleted) throw new NotFoundException(EnumApiStatus.NOT_FOUND);
    }

}
