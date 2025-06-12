package net.sepidan.loyalty.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import net.sepidan.loyalty.persistent.domain.Instances;

/**
 * DTO for {@link Instances}
 */
public record InstancesDto(@NotNull net.sepidan.common.constant.Instances name,
                           @NotNull String title, String description) implements
    Serializable {

}