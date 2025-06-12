package net.sepidan.loyalty.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sepidan.common.dto.PagedResponse;
import net.sepidan.loyalty.dto.InstancesDto;
import net.sepidan.loyalty.persistent.service.InstancesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "instances")
@Tag(name = "Init", description = "instances Controller")
@AllArgsConstructor
@Slf4j
public class InstancesController extends BaseController {

  private final InstancesService instancesService;

  @GetMapping()
  public ResponseEntity<PagedResponse<InstancesDto>> getInstances(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "id") String sortBy,
      @RequestParam(defaultValue = "true") boolean ascending) {
    return ResponseEntity.ok(instancesService.list(page, size, sortBy, ascending));
  }
}
