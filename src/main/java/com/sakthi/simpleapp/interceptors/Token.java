package com.sakthi.simpleapp.interceptors;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Data
public class Token {
  private String username;
  private String password;
}
