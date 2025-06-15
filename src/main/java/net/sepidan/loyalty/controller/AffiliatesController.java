package net.sepidan.loyalty.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.sepidan.loyalty.dto.AffiliatesDto;
import net.sepidan.loyalty.payload.request.AffiliateCreateRequest;
import net.sepidan.loyalty.persistent.service.AffiliateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "affiliates")
@Tag(name = "/affiliate", description = "affiliate Controller")
@AllArgsConstructor
public class AffiliatesController extends BaseController {

  private final AffiliateService affiliateService;

  @PostMapping(name = "/register")
  private ResponseEntity<AffiliatesDto> registerAffiliate(
      @Valid @RequestParam AffiliateCreateRequest affiliateCreateRequest) {
    return ResponseEntity.ok(affiliateService.createAffiliate(affiliateCreateRequest));
  }
}
