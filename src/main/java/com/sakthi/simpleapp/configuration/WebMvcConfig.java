package com.sakthi.simpleapp.configuration;

import com.sakthi.simpleapp.interceptors.BasicInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  private final BasicInterceptor basicInterceptor;

  @Autowired
  public WebMvcConfig(BasicInterceptor basicInterceptor) {
    this.basicInterceptor = basicInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(basicInterceptor);
  }
}
