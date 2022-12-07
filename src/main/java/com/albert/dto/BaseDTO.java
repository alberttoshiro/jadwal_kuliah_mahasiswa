package com.albert.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDTO {
  private UUID id;

  public BaseDTO() {

  }

  public BaseDTO(UUID id) {
    this.id = id;
  }


}
