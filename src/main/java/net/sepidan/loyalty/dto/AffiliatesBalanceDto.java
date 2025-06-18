package net.sepidan.loyalty.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import net.sepidan.loyalty.constant.AffiliationIndicator;

/**
 * DTO for {@link net.sepidan.loyalty.persistent.domain.Affiliates}
 */
public record AffiliatesBalanceDto(@NotNull AffiliationIndicator indicatorType,
                                   @NotNull String indicatorValue,
                                   @NotNull String affiliationCode,
                                   @NotNull List<AffiliateUserPointsDto> pointsDtos,
                                   @NotNull int totalPoints,
                                   @NotNull InstanceTiersDto tiersDto) implements
    Serializable {

}