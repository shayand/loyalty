package net.sepidan.loyalty.persistent.repository;

import net.sepidan.loyalty.persistent.domain.AffiliateUserPoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffiliateUserPointsRepository extends JpaRepository<AffiliateUserPoints, Long> {

}