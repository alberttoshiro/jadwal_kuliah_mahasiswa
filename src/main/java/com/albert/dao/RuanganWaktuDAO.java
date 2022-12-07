package com.albert.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import com.albert.model.Matakuliah;
import com.albert.model.RuanganWaktu;
import com.albert.util.AppUtil;
import org.jboss.logging.Logger;

@ApplicationScoped
public class RuanganWaktuDAO extends BaseDAO<RuanganWaktu> {

  @Inject
  Logger log;

  @Inject
  EntityManager entityManager;

  @Transactional
  @SuppressWarnings("unchecked")
  public List<RuanganWaktu> findByDate(String nomorRuangan, LocalDateTime searchDate) {
    LocalDateTime startDate = searchDate.with(AppUtil.convertStringToLocalTime("00:00"));
    LocalDateTime endDate = searchDate.with(AppUtil.convertStringToLocalTime("23:59"));
    log.info("START DATE = " + startDate);
    log.info("END DATE = " + endDate);
    String stringQuery =
        "SELECT rw from RuanganWaktu rw WHERE rw.nomorRuangan = :nomorRuangan AND rw.waktuMulai >= :startDate AND rw.waktuMulai <= :endDate ORDER BY rw.waktuMulai";
    Query query = entityManager.createQuery(stringQuery, getEntityClass());
    query.setParameter("nomorRuangan", nomorRuangan);
    query.setParameter("startDate", startDate);
    query.setParameter("endDate", endDate);
    List<RuanganWaktu> list = query.getResultList();
    log.info("RUANGAN WAKTU ADA = " + list.size());
    return list;
  }

  @Transactional
  public Matakuliah getMatakuliah(UUID ruanganWaktuId) {
    RuanganWaktu ruanganWaktu = entityManager.find(getEntityClass(), ruanganWaktuId);
    Matakuliah matakuliah = ruanganWaktu.getJadwalKuliah().getMatakuliah();
    return matakuliah;
  }

  @PostConstruct
  public void init() {
    setEntityClass(RuanganWaktu.class);
    setEntityManager(entityManager);
  }

  @Override
  public void updateEntity(RuanganWaktu t, RuanganWaktu entity) {
    t.setId(entity.getId());
    t.setNomorRuangan(entity.getNomorRuangan());
    t.setWaktuMulai(entity.getWaktuMulai());
    t.setWaktuSelesai(entity.getWaktuSelesai());
  }
}
