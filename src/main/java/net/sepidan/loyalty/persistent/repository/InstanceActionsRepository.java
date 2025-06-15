package net.sepidan.loyalty.persistent.repository;

import net.sepidan.loyalty.persistent.domain.InstanceActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceActionsRepository extends JpaRepository<InstanceActions, Long> {

}