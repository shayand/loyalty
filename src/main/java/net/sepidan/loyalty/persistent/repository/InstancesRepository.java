package net.sepidan.loyalty.persistent.repository;

import java.util.UUID;
import net.sepidan.loyalty.persistent.domain.Instances;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstancesRepository extends JpaRepository<Instances, UUID> {

}