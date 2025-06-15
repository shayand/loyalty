
package net.sepidan.loyalty.persistent.service;

import lombok.AllArgsConstructor;
import net.sepidan.loyalty.constant.AffiliateActionSlug;
import net.sepidan.loyalty.constant.TierSlug;
import net.sepidan.loyalty.exception.ResourceNotFoundException;
import net.sepidan.loyalty.persistent.domain.AffiliateUsers;
import net.sepidan.loyalty.persistent.domain.Affiliates;
import net.sepidan.loyalty.persistent.domain.InstanceActions;
import net.sepidan.loyalty.persistent.domain.Instances;
import net.sepidan.loyalty.persistent.domain.Tiers;
import net.sepidan.loyalty.persistent.repository.InstanceActionsRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InstanceActionsService {

  private final InstanceActionsRepository instanceActionsRepository;
  private final TiersService tiersService;
  private final AffiliateUsersService affiliateUsersService;

  public InstanceActions save(InstanceActions instanceActions) {
    return instanceActionsRepository.save(instanceActions);
  }

  public InstanceActions joinAffiliate(Affiliates affiliates, Instances instances) {
    Tiers lowestTiers = tiersService.getSingleBySlug(TierSlug.BRONZE)
        .orElseThrow(ResourceNotFoundException::new);

    InstanceActions currentAction = new InstanceActions();
    currentAction.setInstance(instances);
    currentAction.setTiers(lowestTiers);
    currentAction.setSlug(AffiliateActionSlug.JOIN);
    currentAction.setPointAchieved(20);

    affiliateUsersService.saveAffiliateUsers(affiliates, lowestTiers, instances);

    instanceActionsRepository.save(currentAction);

    return currentAction;
  }
}
