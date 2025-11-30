package com.clinix.persistence.repository;

import com.clinix.persistence.entity.Astronaut;
import com.clinix.persistence.entity.Ship;
import com.clinix.persistence.projection.AstronautInfo;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class AstronautRepository extends BaseRepository<Astronaut> {

    public Optional<AstronautInfo> findByUuid(UUID uuid) {
        return find("uuid", uuid)
                .project(AstronautInfo.class)
                .firstResultOptional();
    }

    public void update(Long astronautId, String nickname, Ship ship) {
        update("nickname = ?1, ship = ?2 where id = ?3",
                nickname, ship, astronautId);
    }
}
