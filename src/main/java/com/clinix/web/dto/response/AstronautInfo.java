package com.clinix.web.dto.response;

import java.util.UUID;

public record AstronautInfo(
        UUID uuid,
        String nickname,
        String shipName
) {
}
