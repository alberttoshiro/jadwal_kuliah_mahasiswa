package com.albert.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import com.albert.model.Matakuliah;
import com.albert.model.RuanganWaktu;
import com.albert.util.AppUtil;
import org.jboss.logging.Logger;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class RuanganWaktuRepository implements PanacheRepositoryBase<RuanganWaktu, UUID> {

  @Inject
  Logger log;

  @Transactional
  public List<RuanganWaktu> findByDate(String nomorRuangan, LocalDateTime searchDate) {
    LocalDateTime startDate = searchDate.with(AppUtil.convertStringToLocalTime("00:00"));
    LocalDateTime endDate = searchDate.with(AppUtil.convertStringToLocalTime("23:59"));
    String stringQuery =
        "FROM RuanganWaktu rw WHERE rw.nomorRuangan = :nomorRuangan AND rw.waktuMulai >= :startDate AND rw.waktuMulai <= :endDate ORDER BY rw.waktuMulai";
    return find(stringQuery, Parameters.with("nomorRuangan", nomorRuangan)
        .and("startDate", startDate).and("endDate", endDate)).list();
  }

  @Transactional
  public Matakuliah getMatakuliah(UUID ruanganWaktuId) {
    RuanganWaktu ruanganWaktu = findById(ruanganWaktuId);
    Matakuliah matakuliah = ruanganWaktu.getJadwalKuliah().getMatakuliah();
    return matakuliah;
  }

  @Transactional
  public void updateRuanganWaktu(UUID id, RuanganWaktu ruanganWaktuUpdate) {
    RuanganWaktu ruanganWaktu = findById(id);
    ruanganWaktu.setNomorRuangan(ruanganWaktuUpdate.getNomorRuangan());
    ruanganWaktu.setWaktuMulai(ruanganWaktuUpdate.getWaktuMulai());
    ruanganWaktu.setWaktuSelesai(ruanganWaktuUpdate.getWaktuSelesai());
  }

}
