package com.sakthi.simpleapp.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakthi.simpleapp.interceptors.AuthInterceptor;
import com.sakthi.simpleapp.resources.GithubApi;
import com.sakthi.simpleapp.resources.GithubApiRx;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfig {
  private static final String GITHUB_URL = "https://api.github.com";
  private static HttpLoggingInterceptor logging =
      new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);

  @Bean
  public OkHttpClient okHttpClient() {
    return new OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build();
  }

  @Bean
  public GithubApi gitHubApi(OkHttpClient okHttpClient, ObjectMapper objectMapper) {
    return buildRetrofit(okHttpClient, objectMapper).create(GithubApi.class);
  }

  private Retrofit buildRetrofit(OkHttpClient okHttpClient, ObjectMapper objectMapper) {
    return new Retrofit.Builder()
        .baseUrl(GITHUB_URL)
        .client(okHttpClient)
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .build();
  }

  @Bean(name = "authorized")
  public OkHttpClient okHttpClientAuthorized(AuthInterceptor authInterceptor) {
    return new OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .addInterceptor(authInterceptor)
        .build();
  }

  //  RxJava: Reactive Extensions for the JVM
  @Bean
  public GithubApiRx gitHubApiRx(
      @Qualifier("authorized") OkHttpClient okHttpClient, ObjectMapper objectMapper) {
    return buildRetrofitRx(okHttpClient, objectMapper).create(GithubApiRx.class);
  }

  private Retrofit buildRetrofitRx(OkHttpClient okHttpClient, ObjectMapper objectMapper) {
    return new Retrofit.Builder()
        .baseUrl(GITHUB_URL)
        .client(okHttpClient)
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
  }
}
