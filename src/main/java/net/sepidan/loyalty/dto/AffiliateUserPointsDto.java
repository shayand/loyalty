package net.sepidan.loyalty.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * DTO for {@link net.sepidan.loyalty.persistent.domain.AffiliateUserPoints}
 */
public record AffiliateUserPointsDto(InstanceActionsDto action, int point, ZonedDateTime createdAt,
                                     ZonedDateTime updatedAt) implements
    Serializable {

}