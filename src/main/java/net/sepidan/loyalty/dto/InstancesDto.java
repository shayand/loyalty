package net.sepidan.loyalty.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import net.sepidan.loyalty.persistent.domain.Instances;

/**
 * DTO for {@link Instances}
 */
public record InstancesDto(@NotNull net.sepidan.common.constant.Instances name,
                           @NotNull String title, String description, ZonedDateTime createdAt,
                           ZonedDateTime updatedAt) implements
    Serializable {

}