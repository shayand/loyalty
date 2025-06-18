package net.sepidan.loyalty.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import net.sepidan.common.constant.Instances;

@Getter
public class AffiliateBalanceRequest {

  @NotNull
  String affiliateCode;
}
