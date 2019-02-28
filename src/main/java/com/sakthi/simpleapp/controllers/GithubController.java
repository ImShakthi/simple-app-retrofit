package com.sakthi.simpleapp.controllers;

import com.sakthi.simpleapp.dto.Repo;
import com.sakthi.simpleapp.resources.GithubResource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/github")
@RestController
@Log4j2
public class GithubController {
  private GithubResource githubResource;

  @Autowired
  public GithubController(GithubResource githubResource) {
    this.githubResource = githubResource;
  }

  @GetMapping("/{userName}")
  ResponseEntity<List<Repo>> getRepos(@PathVariable("userName") final String userName) {
    log.info("Reached endpoint.");
    List<Repo> repos = githubResource.getRepos(userName);
    return ResponseEntity.ok(repos);
  }

  @GetMapping("auth/{userName}")
  ResponseEntity<List<Repo>> getReposAuth(@PathVariable("userName") final String userName) {
    log.info("Reached endpoint.");
    List<Repo> repos = githubResource.getReposRx(userName);
    return ResponseEntity.ok(repos);
  }
}
