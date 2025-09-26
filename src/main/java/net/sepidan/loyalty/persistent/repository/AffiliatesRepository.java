package net.sepidan.loyalty.persistent.repository;

import java.util.Optional;
import net.sepidan.common.constant.Instances;
import net.sepidan.loyalty.constant.AffiliationIndicator;
import net.sepidan.loyalty.persistent.domain.Affiliates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliatesRepository extends JpaRepository<Affiliates, Long> {

  Optional<Affiliates> findByAffiliateUsers_Instance_NameAndIndicatorType(Instances name,
      AffiliationIndicator indicatorType);

  Optional<Affiliates> getByAffiliationCode(String affiliationCode);

  @Nullable
  Optional<Affiliates> findFirstByAffiliateUsers_Instance_NameAndIndicatorTypeAndIndicatorValue(
      Instances name, AffiliationIndicator indicatorType, String indicatorValue);


}