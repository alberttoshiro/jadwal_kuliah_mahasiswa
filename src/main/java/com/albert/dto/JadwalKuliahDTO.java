package com.albert.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JadwalKuliahDTO {

  private MahasiswaDTO mahasiswaDTO;
  private List<JadwalDTO> listJadwalDTO;

  public JadwalKuliahDTO() {

  }

  public JadwalKuliahDTO(MahasiswaDTO mahasiswaDTO, List<JadwalDTO> listJadwalDTO) {
    this.mahasiswaDTO = mahasiswaDTO;
    this.listJadwalDTO = listJadwalDTO;
  }

}
