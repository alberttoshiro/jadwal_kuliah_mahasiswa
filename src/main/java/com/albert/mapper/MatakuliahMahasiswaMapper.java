package com.albert.mapper;

import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dao.MatakuliahDAO;
import com.albert.dto.MatakuliahMahasiswaDTO;
import com.albert.model.Matakuliah;

@ApplicationScoped
public class MatakuliahMahasiswaMapper {

  @Inject
  MahasiswaMapper mahasiswaMapper;

  @Inject
  MatakuliahMapper matakuliahMapper;

  @Inject
  MatakuliahDAO matakuliahDAO;

  public MatakuliahMahasiswaDTO toMatakuliahMahasiswaDTO(Matakuliah matakuliah) {
    return new MatakuliahMahasiswaDTO(matakuliahMapper.toMatakuliahDTO(matakuliah),
        matakuliahDAO.getMahasiswa(matakuliah.getId()).stream()
            .map(t -> mahasiswaMapper.toMahasiswaDTO(t)).collect(Collectors.toList()));
  }
}
