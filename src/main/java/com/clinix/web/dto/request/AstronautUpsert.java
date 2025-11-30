package com.clinix.web.dto.request;

import java.util.UUID;

public record AstronautUpsert(
        String nickname,
        UUID shipUuid
) {
}
