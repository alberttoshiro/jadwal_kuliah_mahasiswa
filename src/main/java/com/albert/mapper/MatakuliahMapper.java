package com.albert.mapper;

import javax.enterprise.context.ApplicationScoped;
import com.albert.dto.MatakuliahDTO;
import com.albert.model.Matakuliah;

@ApplicationScoped
public class MatakuliahMapper {

  public Matakuliah toMatakuliah(MatakuliahDTO matakuliahDTO) {
    return new Matakuliah(matakuliahDTO.getId(), matakuliahDTO.getNamaMatakuliah());
  }

  public MatakuliahDTO toMatakuliahDTO(Matakuliah matakuliah) {
    return new MatakuliahDTO(matakuliah.getId(), matakuliah.getNamaMatakuliah());
  }
}
