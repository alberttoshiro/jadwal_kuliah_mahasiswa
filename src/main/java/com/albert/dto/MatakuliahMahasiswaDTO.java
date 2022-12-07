package com.albert.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatakuliahMahasiswaDTO {

  private MatakuliahDTO matakuliahDTO;
  private List<MahasiswaDTO> listMahasiswaDTO;

  public MatakuliahMahasiswaDTO() {

  }

  public MatakuliahMahasiswaDTO(MatakuliahDTO matakuliahDTO, List<MahasiswaDTO> listMahasiswaDTO) {
    this.matakuliahDTO = matakuliahDTO;
    this.listMahasiswaDTO = listMahasiswaDTO;
  }

}
