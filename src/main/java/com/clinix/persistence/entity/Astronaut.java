package com.clinix.persistence.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "astronauts")
public class Astronaut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(unique = true, nullable = false, length = 150)
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_id")
    private Ship ship;



    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
