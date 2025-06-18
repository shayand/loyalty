package net.sepidan.loyalty.persistent.repository;

import java.util.List;
import java.util.Optional;
import net.sepidan.common.constant.Instances;
import net.sepidan.loyalty.persistent.domain.AffiliateUserPoints;
import net.sepidan.loyalty.persistent.domain.AffiliateUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliateUserPointsRepository extends JpaRepository<AffiliateUserPoints, Long> {

  Optional<AffiliateUserPoints> findFirstByAffiliateUserAndAction_Instance_NameAndAction_DeletedAtNotNullOrderByAction_UpdatedAtDesc(
      AffiliateUsers affiliateUser, Instances name);

  List<AffiliateUserPoints> findByAffiliateUserAndDeletedAtNullOrderByCreatedAtDesc(
      AffiliateUsers affiliateUser);

  @Query("SELECT SUM(ap.point) as total from AffiliateUserPoints ap WHERE ap.affiliateUser = :affiliateUsers and ap.deletedAt is null")
  Optional<Integer> getPointsTotal(AffiliateUsers affiliateUsers);
}