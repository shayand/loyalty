package net.sepidan.loyalty.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;

/**
 * DTO for {@link net.sepidan.loyalty.persistent.domain.InstanceTiers}
 */
public record InstanceTiersDto(@NotNull String title, @Positive int level,
                               @PositiveOrZero int pointsThreshold) implements
    Serializable {

}