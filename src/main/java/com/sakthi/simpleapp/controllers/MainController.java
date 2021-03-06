package com.sakthi.simpleapp.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class MainController {

  @GetMapping("/")
  ResponseEntity<String> hello() {
    return ResponseEntity.ok("Hello world!");
  }
}
