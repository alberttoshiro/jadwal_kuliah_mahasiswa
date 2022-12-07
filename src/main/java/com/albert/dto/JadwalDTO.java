package com.albert.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JadwalDTO {

  private MatakuliahDTO matakuliahDTO;
  private String hari;
  private String tanggal;
  private String nomorRuangan;
  private String waktuMulai;
  private String waktuSelesai;

  public JadwalDTO() {

  }

  public JadwalDTO(MatakuliahDTO matakuliahDTO, String hari, String tanggal, String nomorRuangan,
      String waktuMulai, String waktuSelesai) {
    this.matakuliahDTO = matakuliahDTO;
    this.hari = hari;
    this.tanggal = tanggal;
    this.nomorRuangan = nomorRuangan;
    this.waktuMulai = waktuMulai;
    this.waktuSelesai = waktuSelesai;
  }

}
