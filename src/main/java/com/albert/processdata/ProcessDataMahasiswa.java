package com.albert.processdata;

import com.albert.dao.MahasiswaDAO;
import com.albert.model.Mahasiswa;

public class ProcessDataMahasiswa extends ProcessData<MahasiswaDAO, Mahasiswa> {

  public ProcessDataMahasiswa(String folderName, String filePath, MahasiswaDAO DAO) {
    super(folderName, filePath, DAO);
  }

  @Override
  public Mahasiswa convertToU(String[] param) {
    Mahasiswa mahasiswa = new Mahasiswa();
    mahasiswa.setNim(param[0]);
    mahasiswa.setNama(param[1]);
    return mahasiswa;
  }

}
