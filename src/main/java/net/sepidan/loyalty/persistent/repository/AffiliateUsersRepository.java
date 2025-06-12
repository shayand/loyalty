package net.sepidan.loyalty.persistent.repository;

import net.sepidan.loyalty.persistent.domain.AffiliateUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffiliateUsersRepository extends JpaRepository<AffiliateUsers, Long> {

}