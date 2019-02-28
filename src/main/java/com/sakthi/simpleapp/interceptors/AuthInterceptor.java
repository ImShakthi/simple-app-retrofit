package com.sakthi.simpleapp.interceptors;

import lombok.extern.log4j.Log4j2;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Log4j2
public class AuthInterceptor implements Interceptor {
  private final Token token;

  @Autowired
  public AuthInterceptor(Token token) {
    this.token = token;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    final Request request = chain.request();

    String authToken = Credentials.basic(token.getUsername(), token.getPassword());
    log.info(">>>authToken created=" + authToken);

    Headers authorizationHeader =
        request.headers().newBuilder().add("Authorization", authToken).build();

    final Request authzRequest = request.newBuilder().headers(authorizationHeader).build();

    return chain.proceed(authzRequest);
  }
}
