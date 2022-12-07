package com.albert.processdata;

import com.albert.dao.JadwalKuliahMahasiswaDAO;
import com.albert.model.JadwalKuliahMahasiswa;

public class ProcessDataJadwalKuliahMahasiswa
    extends ProcessData<JadwalKuliahMahasiswaDAO, JadwalKuliahMahasiswa> {

  public ProcessDataJadwalKuliahMahasiswa(String folderName, String filePath,
      JadwalKuliahMahasiswaDAO DAO) {
    super(folderName, filePath, DAO);
  }

  @Override
  public JadwalKuliahMahasiswa convertToU(String[] param) {
    JadwalKuliahMahasiswa jadwalKuliahMahasiswa = new JadwalKuliahMahasiswa();
    jadwalKuliahMahasiswa.setIdMahasiswa(param[0]);
    jadwalKuliahMahasiswa.setIdMatakuliah(param[1]);
    jadwalKuliahMahasiswa.setId(param[2]);
    return jadwalKuliahMahasiswa;
  }

}
