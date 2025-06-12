package net.sepidan.loyalty.persistent.repository;

import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import net.sepidan.loyalty.persistent.domain.Instances;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstancesRepository extends JpaRepository<Instances, UUID> {

  Optional<Instances> findByName(net.sepidan.common.constant.@NonNull Instances name);

  @Override
  Page<Instances> findAll(Pageable pageable);
}