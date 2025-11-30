package com.clinix.persistence.repository;


import com.clinix.persistence.entity.Ship;
import com.clinix.persistence.projection.ShipInfo;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ShipRepository extends BaseRepository<Ship> {

    public Optional<ShipInfo> findByUuid(UUID uuid) {
        return find("uuid", uuid)
                .project(ShipInfo.class)
                .firstResultOptional();
    }

    public void update(Long id, String name) {
        update("name = ?1 where id = ?2", name, id);
    }
}
