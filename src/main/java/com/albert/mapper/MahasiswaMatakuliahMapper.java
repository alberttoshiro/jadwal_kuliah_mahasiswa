package com.albert.mapper;

import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dto.MahasiswaMatakuliahDTO;
import com.albert.model.Mahasiswa;
import com.albert.repository.MahasiswaRepository;

@ApplicationScoped
public class MahasiswaMatakuliahMapper {

  @Inject
  MahasiswaMapper mahasiswaMapper;

  @Inject
  MatakuliahMapper matakuliahMapper;

  @Inject
  MahasiswaRepository mahasiswaRepository;

  public MahasiswaMatakuliahDTO toMahasiswaMatakuliahDTO(Mahasiswa mahasiswa) {
    return new MahasiswaMatakuliahDTO(mahasiswaMapper.toMahasiswaDTO(mahasiswa),
        mahasiswaRepository.getMatakuliah(mahasiswa.getId()).stream()
            .map(t -> matakuliahMapper.toMatakuliahDTO(t)).collect(Collectors.toList()));
  }
}
