package com.clinix.persistence.projection;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.UUID;

@RegisterForReflection
public record ShipInfo(
        UUID uuid,
        String name
) {
}
