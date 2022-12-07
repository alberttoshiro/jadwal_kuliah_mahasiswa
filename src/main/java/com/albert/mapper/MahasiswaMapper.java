package com.albert.mapper;

import javax.enterprise.context.ApplicationScoped;
import com.albert.dto.MahasiswaDTO;
import com.albert.model.Mahasiswa;

@ApplicationScoped
public class MahasiswaMapper {

  public Mahasiswa toMahasiswa(MahasiswaDTO mahasiswaDTO) {
    return new Mahasiswa(mahasiswaDTO.getId(), mahasiswaDTO.getNim(), mahasiswaDTO.getNama());
  }

  public MahasiswaDTO toMahasiswaDTO(Mahasiswa mahasiswa) {
    return new MahasiswaDTO(mahasiswa.getId(), mahasiswa.getNim(), mahasiswa.getNama());
  }
}
