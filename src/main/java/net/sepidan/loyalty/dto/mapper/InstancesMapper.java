package net.sepidan.loyalty.dto.mapper;

import java.util.List;
import net.sepidan.common.dto.PagedResponse;
import net.sepidan.loyalty.dto.InstancesDto;
import net.sepidan.loyalty.persistent.domain.Instances;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface InstancesMapper {

  Instances toEntity(InstancesDto instancesDto);

  InstancesDto toDto(Instances instances);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Instances partialUpdate(
      InstancesDto instancesDto, @MappingTarget Instances instances);

  List<InstancesDto> toDtoList(List<Instances> instances);

  default PagedResponse<InstancesDto> toPagedResponse(Page<Instances> page) {
    return new PagedResponse<>() {{
      setContent(toDtoList(page.getContent()));
      setPage(page.getNumber());
      setSize(page.getSize());
      setTotalElements(page.getTotalElements());
      setTotalPages(page.getTotalPages());
      setLast(page.isLast());
    }};
  }
}