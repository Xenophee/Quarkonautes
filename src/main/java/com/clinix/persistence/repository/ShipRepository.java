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

    public Optional<ShipInfo> findSomeShipByUuid(UUID uuid) {
        List<Tuple> rows = getEntityManager().createQuery("SELECT s.name as name, a.uuid as astro_uuid, a.nickname as nickname FROM Ship s JOIN s.astronauts a WHERE s.uuid = :uuid", Tuple.class)
                .setParameter("uuid", uuid)
                .getResultList();

        if (rows.isEmpty()) return Optional.empty();

        Tuple first = rows.getFirst();

        String name = first.get("name", String.class);

        List<AstronautBasicInfo> crew = rows.stream()
                .map(astro -> new AstronautBasicInfo(
                        astro.get("astro_uuid", UUID.class),
                        astro.get("nickname", String.class)
                ))
                .toList();

        ShipInfo result = new ShipInfo(
                uuid,
                name,
                crew
        );

        return Optional.of(result);
    }

    public List<ShipInfo> findAllShips() {
        // On récupère tous les ships avec leurs astronautes
        List<Tuple> rows = getEntityManager()
                .createQuery("""
                SELECT 
                    s.uuid AS ship_uuid, 
                    s.name AS ship_name, 
                    a.uuid AS astro_uuid, 
                    a.nickname AS nickname
                FROM Ship s 
                LEFT JOIN s.astronauts a
            """, Tuple.class)
                .getResultList();

        // On regroupe les tuples par ship UUID
        Map<UUID, List<Tuple>> groupedByShip = rows.stream()
                .collect(Collectors.groupingBy(t -> t.get("ship_uuid", UUID.class)));

        // On construit la liste finale
        return groupedByShip.entrySet().stream()
                .map(entry -> {
                    UUID shipUuid = entry.getKey();
                    List<Tuple> shipRows = entry.getValue();

                    String name = shipRows.getFirst().get("ship_name", String.class);

                    List<AstronautBasicInfo> crew = shipRows.stream()
                            .map(t -> new AstronautBasicInfo(
                                    t.get("astro_uuid", UUID.class),
                                    t.get("nickname", String.class)
                            ))
                            .toList();

                    return new ShipInfo(shipUuid, name, crew);
                })
                .toList();
    }

}
