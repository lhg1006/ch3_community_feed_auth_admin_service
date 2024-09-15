package org.fastcampus.community_feed.common.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthcheckController {


  @GetMapping
  public String healthcheck() {
    return "OK";
  }
}
