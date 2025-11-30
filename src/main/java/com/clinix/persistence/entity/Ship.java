package com.clinix.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ships")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(unique = true, nullable = false, length = 150)
    private String name;

    @OneToMany(mappedBy = "ship", fetch = FetchType.LAZY)
    private List<Astronaut> astronauts = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Astronaut> getAstronauts() {
        return astronauts;
    }

    public void setAstronauts(List<Astronaut> astronauts) {
        this.astronauts = astronauts;
    }
}
