package com.albert.processdata;

import com.albert.dao.JadwalKuliahDAO;
import com.albert.model.JadwalKuliah;
import com.albert.util.AppUtil;

public class ProcessDataJadwalKuliah extends ProcessData<JadwalKuliahDAO, JadwalKuliah> {

  public ProcessDataJadwalKuliah(String folderName, String filePath, JadwalKuliahDAO DAO) {
    super(folderName, filePath, DAO);
  }

  @Override
  public JadwalKuliah convertToU(String[] param) {
    JadwalKuliah jadwalKuliah = new JadwalKuliah();
    jadwalKuliah.setHari(param[0]);
    jadwalKuliah.setRuangan(param[1]);
    jadwalKuliah.setWaktuMulai(AppUtil.convertStringToLocalTime(param[2]));
    jadwalKuliah.setWaktuSelesai(AppUtil.convertStringToLocalTime(param[3]));
    return jadwalKuliah;
  }

}
