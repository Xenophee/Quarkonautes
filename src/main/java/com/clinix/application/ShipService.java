package com.clinix.application;


import com.clinix.config.EnumApiStatus;
import com.clinix.exception.ActionNotAllowed;
import com.clinix.exception.NotFoundException;
import com.clinix.mapper.ShipMapper;
import com.clinix.persistence.entity.Ship;
import com.clinix.persistence.projection.ShipInfo;
import com.clinix.persistence.repository.AstronautRepository;
import com.clinix.persistence.repository.ShipRepository;
import com.clinix.web.dto.request.ShipUpsert;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.UUID;

@ApplicationScoped
public class ShipService {

    private static final Logger LOG = Logger.getLogger(ShipService.class);

    private final ShipRepository shipRepository;
    private  final ShipMapper shipMapper;

    @Inject
    public ShipService(ShipRepository shipRepository, ShipMapper shipMapper) {
        this.shipRepository = shipRepository;
        this.shipMapper = shipMapper;
    }


    public ShipInfo findByUuid(UUID uuid) {
        LOG.infof("Searching for astronaut with UUID: %s", uuid);
        return shipRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(EnumApiStatus.NOT_FOUND));
    }

    @Transactional
    public ShipInfo create(ShipUpsert shipInsert) {
        Ship ship = new Ship();
        ship.setName(shipInsert.name());
        shipRepository.persist(ship);

        return shipMapper.toDTO(ship);
    }

    @Transactional
    public ShipInfo update(UUID uuid, ShipUpsert ship) {

        Long shipId = shipRepository.getIdByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(EnumApiStatus.NOT_FOUND));

        shipRepository.update(shipId, ship.name());

        return findByUuid(uuid);
    }

    @Transactional
    public void delete(UUID uuid) {
        boolean deleted = shipRepository.deleteByUuid(uuid);
        if (!deleted) throw new NotFoundException(EnumApiStatus.NOT_FOUND);
    }
}
