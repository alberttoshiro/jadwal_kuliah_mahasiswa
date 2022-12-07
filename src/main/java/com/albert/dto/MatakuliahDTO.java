package com.albert.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatakuliahDTO extends BaseDTO {

  private String namaMatakuliah;

  public MatakuliahDTO() {

  }

  public MatakuliahDTO(UUID id, String namaMatakuliah) {
    super(id);
    this.namaMatakuliah = namaMatakuliah;
  }

}
