package com.github.vnesterov.avito.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @RequestMapping(value = "/ping")
  public String ping(String Ss,String qq) {
    return "pong"+Ss+qq;
  }
}
