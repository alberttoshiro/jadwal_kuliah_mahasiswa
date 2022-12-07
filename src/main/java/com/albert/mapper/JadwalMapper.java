package com.albert.mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dto.JadwalDTO;
import com.albert.model.JadwalKuliah;
import com.albert.model.RuanganWaktu;
import com.albert.util.AppUtil;
import com.albert.util.HariUtil;

@ApplicationScoped
public class JadwalMapper {

  @Inject
  MatakuliahMapper matakuliahMapper;

  public JadwalDTO toJadwalDTO(JadwalKuliah jadwalKuliah) {
    RuanganWaktu ruanganWaktu = jadwalKuliah.getRuanganWaktu();
    return new JadwalDTO(matakuliahMapper.toMatakuliahDTO(jadwalKuliah.getMatakuliah()),
        HariUtil.getNamaHari(ruanganWaktu.getWaktuMulai().getDayOfWeek().getValue()),
        AppUtil.convertLocalDateTimeToStringDate(ruanganWaktu.getWaktuMulai()),
        ruanganWaktu.getNomorRuangan(),
        AppUtil.convertLocalDateTimeToTimeString(ruanganWaktu.getWaktuMulai()),
        AppUtil.convertLocalDateTimeToTimeString(ruanganWaktu.getWaktuSelesai()));

  }
}
