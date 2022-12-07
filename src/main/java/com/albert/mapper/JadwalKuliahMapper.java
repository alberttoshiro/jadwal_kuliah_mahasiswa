package com.albert.mapper;

import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dto.JadwalKuliahDTO;
import com.albert.model.JadwalKuliah;
import com.albert.model.Mahasiswa;
import com.albert.repository.JadwalKuliahRepository;

@ApplicationScoped
public class JadwalKuliahMapper {

  @Inject
  MahasiswaMapper mahasiswaMapper;

  @Inject
  JadwalMapper jadwalMapper;

  @Inject
  JadwalKuliahRepository jadwalKuliahRepository;

  public JadwalKuliahDTO toJadwalKuliahDTO(Mahasiswa mahasiswa) {
    JadwalKuliahDTO jadwalKuliahDTO = new JadwalKuliahDTO();
    jadwalKuliahDTO.setMahasiswaDTO(mahasiswaMapper.toMahasiswaDTO(mahasiswa));
    List<JadwalKuliah> listJadwalKuliah = jadwalKuliahRepository.findByMahasiswa(mahasiswa);
    if (listJadwalKuliah == null || listJadwalKuliah.size() == 0) {
      return jadwalKuliahDTO;
    }
    jadwalKuliahDTO.setListJadwalDTO(listJadwalKuliah.stream().map(t -> jadwalMapper.toJadwalDTO(t))
        .collect(Collectors.toList()));
    return jadwalKuliahDTO;
  }

}
