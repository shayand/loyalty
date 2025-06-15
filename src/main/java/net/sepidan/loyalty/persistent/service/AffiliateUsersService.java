package net.sepidan.loyalty.persistent.service;

import lombok.AllArgsConstructor;
import net.sepidan.loyalty.persistent.domain.AffiliateUsers;
import net.sepidan.loyalty.persistent.domain.Affiliates;
import net.sepidan.loyalty.persistent.domain.Instances;
import net.sepidan.loyalty.persistent.domain.Tiers;
import net.sepidan.loyalty.persistent.repository.AffiliateUsersRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AffiliateUsersService {

  private final AffiliateUsersRepository affiliateUsersRepository;

  AffiliateUsers saveAffiliateUsers(Affiliates currentAffiliate, Tiers tiers,Instances instances) {
    AffiliateUsers affiliateUsers = new AffiliateUsers(currentAffiliate, tiers , instances);
    return affiliateUsersRepository.save(affiliateUsers);
  }
}
