package net.sepidan.loyalty.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import net.sepidan.common.constant.Instances;
import net.sepidan.loyalty.constant.AffiliationIndicator;

@Getter
public class AffiliateCreateRequest {

  @NotNull
  Instances instance;

  @NotNull
  AffiliationIndicator indicator;

  @NotNull
  String value;
}
