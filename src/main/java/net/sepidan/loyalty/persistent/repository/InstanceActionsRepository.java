package net.sepidan.loyalty.persistent.repository;

import java.util.Optional;
import lombok.NonNull;
import net.sepidan.loyalty.constant.AffiliateActionSlug;
import net.sepidan.loyalty.constant.TierSlug;
import net.sepidan.loyalty.persistent.domain.InstanceActions;
import net.sepidan.loyalty.persistent.domain.Instances;
import net.sepidan.loyalty.persistent.domain.Tiers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceActionsRepository extends JpaRepository<InstanceActions, Long> {

  Optional<InstanceActions> findByInstanceAndSlugAndTiers(@NonNull Instances instance,
      @NonNull AffiliateActionSlug slug, @NonNull Tiers tiers);

  Optional<InstanceActions> findFirstByTiersAndSlugAndDeletedAtNotNullOrderByCreatedAtDesc(
      Tiers tiers, AffiliateActionSlug slug);
}