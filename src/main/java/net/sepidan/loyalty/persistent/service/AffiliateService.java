package net.sepidan.loyalty.persistent.service;

import static net.sepidan.common.constant.DateTimeConstants.TEHRAN_ZONE_ID;

import com.github.eloyzone.jalalicalendar.DateConverter;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import com.github.eloyzone.jalalicalendar.JalaliDateFormatter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import net.sepidan.loyalty.constant.AffiliationIndicator;
import net.sepidan.loyalty.dto.AffiliatesDto;
import net.sepidan.loyalty.dto.mapper.AffiliatesMapper;
import net.sepidan.loyalty.exception.ResourceNotFoundException;
import net.sepidan.loyalty.payload.request.AffiliateCreateRequest;
import net.sepidan.loyalty.persistent.domain.Affiliates;
import net.sepidan.loyalty.persistent.domain.Instances;
import net.sepidan.loyalty.persistent.repository.AffiliatesRepository;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AffiliateService {

  private final AffiliatesRepository affiliatesRepository;
  private final AffiliatesMapper affiliatesMapper;
  private final InstancesService instancesService;
  private final InstanceActionsService instanceActionsService;

  public AffiliatesDto createAffiliate(AffiliateCreateRequest affiliateCreateRequest) {
    return createAffiliate(affiliateCreateRequest.getInstance(),
        affiliateCreateRequest.getIndicator(), affiliateCreateRequest.getValue());
  }

  public AffiliatesDto createAffiliate(net.sepidan.common.constant.Instances currentInstance,
      AffiliationIndicator affiliationIndicator, String value) {
    Affiliates currentAffiliate = affiliatesRepository.findByAffiliateUsers_Instance_NameAndIndicatorType(
        currentInstance, affiliationIndicator).orElseGet(
        () -> {
          Instances instances = instancesService.findByName(
              currentInstance).orElseThrow(ResourceNotFoundException::new);
          Affiliates newAffiliate = new Affiliates();
          newAffiliate.setAffiliationCode(generateAffiliateCode());
          newAffiliate.setIndicatorType(affiliationIndicator);
          newAffiliate.setIndicatorValue(value);
          affiliatesRepository.save(newAffiliate);

          instanceActionsService.joinAffiliate(newAffiliate, instances);

          return newAffiliate;
        }
    );
    return affiliatesMapper.toDto(currentAffiliate);
  }

  private String generateAffiliateCode() {
    ZonedDateTime nowDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of(TEHRAN_ZONE_ID));

    DateConverter dateConverter = new DateConverter();
    JalaliDate jalaliDate = dateConverter.gregorianToJalali(nowDateTime.getYear(),
        nowDateTime.getMonthValue(), nowDateTime.getDayOfMonth());

    StringBuilder sb = new StringBuilder(
        jalaliDate.format(
            new JalaliDateFormatter("yyyymmdd", JalaliDateFormatter.FORMAT_IN_ENGLISH))
    );
    int min = 11111;
    int max = 99999;
    int randomNumber = (int) (Math.random() * (max - min + 1)) + min;
    sb.append(randomNumber);
    return sb.toString();
  }
}
