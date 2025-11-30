package com.clinix.persistence.repository;

import com.clinix.persistence.projection.DatabaseIdentifier;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.Optional;
import java.util.UUID;

public abstract class BaseRepository<T> implements PanacheRepository<T> {

    /**
     * Récupère un résultat projeté d'après UUID.
     *
     * @param uuid l'UUID de l'entité
     * @param projectionClass la classe de projection désirée
     * @param <P> type de projection
     * @return Optional du résultat projeté
     */
    public <P> Optional<P> getSingleResultByUuid(UUID uuid, Class<P> projectionClass) {
        return find("uuid = ?1", uuid)
                .project(projectionClass)
                .firstResultOptional();
    }


    public Optional<Long> getIdByUuid(UUID uuid) {
        return getSingleResultByUuid(uuid, DatabaseIdentifier.class)
                .map(DatabaseIdentifier::id);
    }

    public boolean deleteByUuid(UUID uuid) {
        return delete("uuid", uuid) > 0;
    }
}
