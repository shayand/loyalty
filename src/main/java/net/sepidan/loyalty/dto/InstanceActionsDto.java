package net.sepidan.loyalty.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import net.sepidan.loyalty.constant.AffiliateActionSlug;
import net.sepidan.loyalty.persistent.domain.InstanceActions;

/**
 * DTO for {@link InstanceActions}
 */
public record InstanceActionsDto(Long id, AffiliateActionSlug slug, String description,
                                 int pointAchieved, ZonedDateTime createdAt,
                                 ZonedDateTime updatedAt, ZonedDateTime deletedAt) implements
    Serializable {

}