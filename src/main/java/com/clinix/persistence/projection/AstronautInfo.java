package com.clinix.persistence.projection;


import io.quarkus.hibernate.orm.panache.common.NestedProjectedClass;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.UUID;

@RegisterForReflection
public record AstronautInfo(

        UUID uuid,
        String nickname,
        Ship ship

) {

    @NestedProjectedClass
    public record Ship(
            String name
    ) { }
}
