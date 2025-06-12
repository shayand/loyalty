package net.sepidan.loyalty.dto.mapper;

import net.sepidan.loyalty.dto.InstanceTiersDto;
import net.sepidan.loyalty.persistent.domain.InstanceTiers;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface InstanceTiersMapper {

  InstanceTiers toEntity(InstanceTiersDto instanceTiersDto);

  InstanceTiersDto toDto(InstanceTiers instanceTiers);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  InstanceTiers partialUpdate(
      InstanceTiersDto instanceTiersDto, @MappingTarget InstanceTiers instanceTiers);
}