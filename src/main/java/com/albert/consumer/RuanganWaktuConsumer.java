package com.albert.consumer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import com.albert.model.RuanganWaktu;
import com.albert.repository.JadwalKuliahRepository;
import com.albert.repository.RuanganWaktuRepository;
import com.albert.util.AppUtil;
import org.jboss.logging.Logger;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class RuanganWaktuConsumer extends BaseConsumer {

  private static String periodeMulai;
  private static String periodeSelesai;

  @Inject
  Logger log;

  @Inject
  RuanganWaktuRepository ruanganWaktuRepository;

  @Inject
  JadwalKuliahRepository jadwalKuliahRepository;

  @Transactional
  @Override
  public void insert(String[] param) {
    if (param.length == 2) {
      periodeMulai = param[0];
      periodeSelesai = param[1];
      return;
    }
    UUID id = UUID.fromString(param[0]);
    String nomorRuangan = param[1];
    String namaHari = param[2];
    String waktuMulai = param[3];
    String waktuSelesai = param[4];


    List<LocalDateTime> listWaktuMulai =
        AppUtil.getRecurringDate(periodeMulai, periodeSelesai, namaHari, waktuMulai);
    List<LocalDateTime> listWaktuSelesai =
        AppUtil.getRecurringDate(periodeMulai, periodeSelesai, namaHari, waktuSelesai);

    if (listWaktuMulai.size() > 0) {
      LocalDateTime startDate = listWaktuMulai.get(0);
      LocalDateTime endDate = listWaktuSelesai.get(0);
      List<RuanganWaktu> listRuanganWaktu =
          ruanganWaktuRepository.findByDate(nomorRuangan, startDate);
      List<LocalDateTime> listSearchWaktuMulai = new ArrayList<>();
      List<LocalDateTime> listSearchWaktuSelesai = new ArrayList<>();
      for (RuanganWaktu ruanganWaktu : listRuanganWaktu) {
        listSearchWaktuMulai.add(ruanganWaktu.getWaktuMulai());
        listSearchWaktuSelesai.add(ruanganWaktu.getWaktuSelesai());
      }
      if (AppUtil.isAnyOverlap(startDate, endDate, listSearchWaktuMulai, listSearchWaktuSelesai)) {
        log.info("Room is used on that time");
        for (int i = 0; i < listSearchWaktuMulai.size(); i++) {
          log.info(nomorRuangan + " is being used on " + listSearchWaktuMulai.get(i) + " - "
              + listSearchWaktuSelesai.get(i));
        }
        log.info("Ruangan waktu is not inserted");
        return;
      }
    }

    for (int i = 0; i < listWaktuMulai.size(); i++) {
      if (i != 0) {
        id = UUID.randomUUID();
      }
      RuanganWaktu ruanganWaktu =
          new RuanganWaktu(id, nomorRuangan, listWaktuMulai.get(i), listWaktuSelesai.get(i));
      ruanganWaktuRepository.persist(ruanganWaktu);
    }
  }

  @ConsumeEvent(value = "processRuanganWaktu", blocking = true)
  public void processRuanganWaktu(String filePath) throws IOException {
    processFile(filePath, "master", "master-done");
  }

}
