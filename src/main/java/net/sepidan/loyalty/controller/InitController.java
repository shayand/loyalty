package net.sepidan.loyalty.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "init")
@Tag(name = "Init", description = "initiate requests")
@AllArgsConstructor
@Slf4j
public class InitController {
  @PostMapping("first")
  public ResponseEntity first() {
    log.info("First initiated");
    return ResponseEntity.ok("");
  }

  @GetMapping("second/{param}")
  public ResponseEntity<String> second() {
    return ResponseEntity.ok("");
  }

}
