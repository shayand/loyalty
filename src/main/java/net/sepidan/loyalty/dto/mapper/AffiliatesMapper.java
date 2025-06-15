package net.sepidan.loyalty.dto.mapper;

import net.sepidan.loyalty.dto.AffiliatesDto;
import net.sepidan.loyalty.persistent.domain.Affiliates;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface AffiliatesMapper {

  Affiliates toEntity(AffiliatesDto affiliatesDto);

  AffiliatesDto toDto(Affiliates affiliates);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Affiliates partialUpdate(
      AffiliatesDto affiliatesDto, @MappingTarget Affiliates affiliates);
}