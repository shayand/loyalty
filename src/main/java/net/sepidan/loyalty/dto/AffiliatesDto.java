package net.sepidan.loyalty.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import net.sepidan.loyalty.constant.AffiliationIndicator;

/**
 * DTO for {@link net.sepidan.loyalty.persistent.domain.Affiliates}
 */
public record AffiliatesDto(@NotNull AffiliationIndicator indicatorType,
                            @NotNull String indicatorValue) implements
    Serializable {

}