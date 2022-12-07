package com.albert.consumer;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import com.albert.model.JadwalKuliah;
import com.albert.repository.JadwalKuliahRepository;
import com.albert.repository.MatakuliahRepository;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class JadwalKuliahConsumer extends BaseConsumer {

  @Inject
  JadwalKuliahRepository jadwalKuliahRepository;

  @Inject
  MatakuliahRepository matakuliahRepository;

  @Transactional
  @Override
  public void insert(String[] param) {
    UUID id = UUID.fromString(param[0]);
    UUID mahasiswaId = UUID.fromString(param[1]);
    UUID matakuliahId = UUID.fromString(param[2]);
    UUID ruanganWaktuId = UUID.fromString(param[3]);
    JadwalKuliah jadwalKuliah =
        jadwalKuliahRepository.convertToJadwalKuliah(mahasiswaId, matakuliahId, ruanganWaktuId);
    jadwalKuliah.setId(id);
    List<JadwalKuliah> listJadwalKuliah =
        jadwalKuliahRepository.findByRuanganWaktuId(ruanganWaktuId);
    if (listJadwalKuliah != null) {
      if (listJadwalKuliah.size() > 0) {
        JadwalKuliah jadwalKuliahDB = listJadwalKuliah.get(0);
        if (jadwalKuliahDB.getMatakuliah().getId().compareTo(matakuliahId) != 0) {
          log.info("This ruangan waktu are booked for matakuliah "
              + jadwalKuliahDB.getMatakuliah().getNamaMatakuliah());
          log.info("Jadwal kuliah not inserted");
          return;
        }
      }
    }
    jadwalKuliahRepository.persist(jadwalKuliah);
  }

  @ConsumeEvent(value = "processJadwalKuliah", blocking = true)
  public void processJadwalKuliah(String filePath) throws IOException {
    processFile(filePath, "compose", "compose-done");
  }

}
