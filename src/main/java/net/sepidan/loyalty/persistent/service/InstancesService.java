package net.sepidan.loyalty.persistent.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import net.sepidan.common.dto.PagedResponse;
import net.sepidan.loyalty.dto.InstancesDto;
import net.sepidan.loyalty.dto.mapper.InstancesMapper;
import net.sepidan.loyalty.persistent.domain.Instances;
import net.sepidan.loyalty.persistent.repository.InstancesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InstancesService {

  private final InstancesRepository instancesRepository;
  private final InstancesMapper instancesMapper;

  public Instances create(Instances instances) {
    return instancesRepository.save(instances);
  }

  public Optional<Instances> findByName(net.sepidan.common.constant.Instances instances) {
    return instancesRepository.findByName(instances);
  }

  public PagedResponse<InstancesDto> list(int page, int size, String sortBy, boolean ascending) {
    Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);
    return instancesMapper.toPagedResponse(instancesRepository.findAll(pageable));
  }
}
