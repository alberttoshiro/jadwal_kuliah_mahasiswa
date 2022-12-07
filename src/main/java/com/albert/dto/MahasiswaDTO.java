package com.albert.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MahasiswaDTO extends BaseDTO {

  private String nim;
  private String nama;

  public MahasiswaDTO() {

  }

  public MahasiswaDTO(UUID id, String nim, String nama) {
    super(id);
    this.nim = nim;
    this.nama = nama;
  }

}
