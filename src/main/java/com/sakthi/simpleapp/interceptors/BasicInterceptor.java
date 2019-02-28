package com.sakthi.simpleapp.interceptors;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Log4j2
public class BasicInterceptor implements HandlerInterceptor {
  private final Token token;

  @Autowired
  public BasicInterceptor(Token token) {
    this.token = token;
  }

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    token.setUsername(request.getHeader("user"));
    token.setPassword(request.getHeader("password"));
    log.info(">>>>>>>>>>>>> Updating the TOKEN=" + token);
    return true;
  }
}
