package net.sepidan.loyalty.persistent.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.sepidan.common.constant.Instances;
import net.sepidan.loyalty.dto.AffiliateUserPointsDto;
import net.sepidan.loyalty.dto.mapper.AffiliateUserPointsMapper;
import net.sepidan.loyalty.persistent.domain.AffiliateUserPoints;
import net.sepidan.loyalty.persistent.domain.AffiliateUsers;
import net.sepidan.loyalty.persistent.domain.InstanceActions;
import net.sepidan.loyalty.persistent.repository.AffiliateUserPointsRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AffiliateUserPointsService {

  private final AffiliateUserPointsRepository affiliateUserPointsRepository;
  private final AffiliateUserPointsMapper mapper;

  public Optional<AffiliateUserPoints> getUserLatestInstance(AffiliateUsers currentAffiliateUser,
      Instances instanceName) {
    return affiliateUserPointsRepository.
        findFirstByAffiliateUserAndAction_Instance_NameAndAction_DeletedAtNotNullOrderByAction_UpdatedAtDesc(
            currentAffiliateUser, instanceName);
  }

  public Optional<AffiliateUserPoints> savePoints(@NonNull AffiliateUsers affiliateUsers, @NonNull
  InstanceActions instanceActions) {
    AffiliateUserPoints affiliateUserPoints = new AffiliateUserPoints(affiliateUsers,
        instanceActions);
    affiliateUserPointsRepository.save(affiliateUserPoints);
    return Optional.of(affiliateUserPoints);
  }

  List<AffiliateUserPointsDto> affiliateUserPointsList(AffiliateUsers affiliateUsers) {
    return affiliateUserPointsRepository.findByAffiliateUserAndDeletedAtNullOrderByCreatedAtDesc(
        affiliateUsers).stream().map(mapper::toDto).collect(Collectors.toList());
  }

  int getUserPointsTotal(AffiliateUsers affiliateUser) {
    return affiliateUserPointsRepository.getPointsTotal(affiliateUser).orElse(0);
  }
}
