package net.sepidan.loyalty.persistent.repository;

import net.sepidan.loyalty.persistent.domain.InstanceActions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstanceActionsRepository extends JpaRepository<InstanceActions, Long> {

}