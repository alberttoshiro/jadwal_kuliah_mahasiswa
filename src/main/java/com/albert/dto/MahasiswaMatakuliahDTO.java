package com.albert.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MahasiswaMatakuliahDTO {

  private MahasiswaDTO mahasiswaDTO;
  private List<MatakuliahDTO> listMatakuliahDTO;

  public MahasiswaMatakuliahDTO() {

  }

  public MahasiswaMatakuliahDTO(MahasiswaDTO mahasiswaDTO, List<MatakuliahDTO> listMatakuliahDTO) {
    this.mahasiswaDTO = mahasiswaDTO;
    this.listMatakuliahDTO = listMatakuliahDTO;
  }
}
