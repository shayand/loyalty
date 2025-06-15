
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
  private final AffiliateUserPointsService affiliateUserPointsService;

  public InstanceActions save(InstanceActions instanceActions) {
    return instanceActionsRepository.save(instanceActions);
  }

  public InstanceActions joinAffiliate(Affiliates selectedAffiliate, Instances selectedInstance) {
    Tiers lowestTiers = tiersService.getSingleBySlug(TierSlug.BRONZE)
        .orElseThrow(ResourceNotFoundException::new);

    AffiliateUsers selectedAffiliateUser = affiliateUsersService.saveAffiliateUsers(
        selectedAffiliate, lowestTiers, selectedInstance);

    InstanceActions joinAction = instanceActionsRepository.findByInstanceAndSlugAndTiers(
            selectedInstance, AffiliateActionSlug.JOIN, lowestTiers)
        .orElseThrow(ResourceNotFoundException::new);
    affiliateUserPointsService.savePoints(selectedAffiliateUser, joinAction);
    return joinAction;
  }
}
