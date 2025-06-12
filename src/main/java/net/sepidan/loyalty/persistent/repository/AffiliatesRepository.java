package net.sepidan.loyalty.persistent.repository;

import net.sepidan.loyalty.persistent.domain.Affiliates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffiliatesRepository extends JpaRepository<Affiliates, Long> {

}