package com.sakthi.simpleapp.resources;

import com.sakthi.simpleapp.dto.Repo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Component
@Log4j2
public class GithubResource {
  private GithubApi githubApi;
  private GithubApiRx githubApiRx;

  @Autowired
  public GithubResource(GithubApi githubApi, GithubApiRx githubApiRx) {
    this.githubApi = githubApi;
    this.githubApiRx = githubApiRx;
  }

  public List<Repo> getRepos(String user) {
    Response<List<Repo>> response;
    try {
      response = githubApi.listRepos(user).execute();
    } catch (IOException e) {
      log.error("Error while getting repo details ", e);
      throw new RuntimeException("Error while getting repo detils ", e);
    }
    return response.body();
  }

  public List<Repo> getReposRx(String user) {
    try {
      return githubApiRx.listRepos(user).execute().body();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
    //    return githubApiRx.listRepos(user).blockingSingle();
  }

  public void getReposAsync(String user) {
    githubApi
        .listRepos(user)
        .enqueue(
            new Callback<List<Repo>>() {
              @Override
              public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                  // success
                } else {
                  // fail
                }
              }

              @Override
              public void onFailure(Call<List<Repo>> call, Throwable t) {
                // fail
              }
            });
  }
}
