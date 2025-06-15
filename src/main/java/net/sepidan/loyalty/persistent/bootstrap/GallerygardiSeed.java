package net.sepidan.loyalty.persistent.bootstrap;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sepidan.loyalty.constant.AffiliateActionSlug;
import net.sepidan.loyalty.constant.TierSlug;
import net.sepidan.loyalty.persistent.domain.InstanceActions;
import net.sepidan.loyalty.persistent.domain.Instances;
import net.sepidan.loyalty.persistent.domain.Tiers;
import net.sepidan.loyalty.persistent.service.InstanceActionsService;
import net.sepidan.loyalty.persistent.service.InstancesService;
import net.sepidan.loyalty.persistent.service.TiersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class GallerygardiSeed implements CommandLineRunner {

  private final TiersService instanceTiersService;
  private final InstancesService instancesService;
  private final InstanceActionsService instanceActionsService;

  @Override
  public void run(String... args) throws Exception {
    Optional<Instances> currentInstance = instancesService.findByName(
        net.sepidan.common.constant.Instances.GALLERYGARDI);
    if (currentInstance.isPresent()) {
      Instances currentInstances = currentInstance.get();
      if (!instanceTiersService.existsBySlugAndLevel(TierSlug.BRONZE, 0)) {
        Tiers bronze = instanceTiersService.create(new Tiers(TierSlug.BRONZE, "bronze", 0, 1800));
        instanceActionsService.save(
            new InstanceActions(currentInstances, bronze, AffiliateActionSlug.JOIN, 50));
        instanceActionsService.save(
            new InstanceActions(currentInstances, bronze, AffiliateActionSlug.INTRODUCE, 200));
      }

      if (!instanceTiersService.existsBySlugAndLevel(TierSlug.SILVER, 1)) {
        Tiers silver = instanceTiersService.create(new Tiers(TierSlug.SILVER, "silver", 1, 3600));
        instanceActionsService.save(
            new InstanceActions(currentInstances, silver, AffiliateActionSlug.JOIN, 50));
        instanceActionsService.save(
            new InstanceActions(currentInstances, silver, AffiliateActionSlug.INTRODUCE, 200));
      }

      if (!instanceTiersService.existsBySlugAndLevel(TierSlug.GOLD, 1)) {
        Tiers gold = instanceTiersService.create(new Tiers(TierSlug.GOLD, "gold", 2, 5400));
        instanceActionsService.save(
            new InstanceActions(currentInstances, gold, AffiliateActionSlug.JOIN, 50));
        instanceActionsService.save(
            new InstanceActions(currentInstances, gold, AffiliateActionSlug.INTRODUCE, 200));
      }

      if (!instanceTiersService.existsBySlugAndLevel(TierSlug.VIP, 3)) {
        Tiers vip = instanceTiersService.create(new Tiers(TierSlug.VIP, "vip", 3, 7200));
        instanceActionsService.save(
            new InstanceActions(currentInstances, vip, AffiliateActionSlug.JOIN, 50));
        instanceActionsService.save(
            new InstanceActions(currentInstances, vip, AffiliateActionSlug.INTRODUCE, 200));
      }

    }
  }
}