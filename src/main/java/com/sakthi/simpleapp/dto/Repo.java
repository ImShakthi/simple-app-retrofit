package com.sakthi.simpleapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Repo {
  @JsonProperty private Long id;
  @JsonProperty private String name;
}
