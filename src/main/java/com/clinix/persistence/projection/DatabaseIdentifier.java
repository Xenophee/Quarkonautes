package com.clinix.persistence.projection;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record DatabaseIdentifier(
        Long id
) {
}
