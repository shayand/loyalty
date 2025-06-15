package net.sepidan.loyalty.persistent.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import net.sepidan.common.constant.Instances;
import net.sepidan.loyalty.persistent.domain.AffiliateUserPoints;
import net.sepidan.loyalty.persistent.domain.AffiliateUsers;
import net.sepidan.loyalty.persistent.repository.AffiliateUserPointsRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AffiliateUserPointsService {

  private final AffiliateUserPointsRepository affiliateUserPointsRepository;

  public Optional<AffiliateUserPoints> getUserLatestInstance(AffiliateUsers currentAffiliateUser,
      Instances instanceName) {
    return affiliateUserPointsRepository.
        findFirstByAffiliateUserAndAction_Instance_NameAndAction_DeletedAtNotNullOrderByAction_UpdatedAtDesc(
            currentAffiliateUser, instanceName);
  }
}
