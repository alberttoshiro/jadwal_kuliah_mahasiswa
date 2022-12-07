package com.albert.dao;

import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import com.albert.model.JadwalKuliah;
import com.albert.model.Mahasiswa;
import com.albert.model.Matakuliah;
import com.albert.model.RuanganWaktu;
import org.jboss.logging.Logger;

@ApplicationScoped
public class JadwalKuliahDAO extends BaseDAO<JadwalKuliah> {

  @Inject
  Logger log;

  @Inject
  EntityManager entityManager;

  public JadwalKuliah convertToJadwalKuliah(UUID mahasiswaId, UUID matakuliahId,
      UUID ruanganWaktuId) {
    JadwalKuliah jadwalKuliah = new JadwalKuliah();
    jadwalKuliah.setMahasiswa(entityManager.getReference(Mahasiswa.class, mahasiswaId));
    jadwalKuliah.setMatakuliah(entityManager.getReference(Matakuliah.class, matakuliahId));
    jadwalKuliah.setRuanganWaktu(entityManager.getReference(RuanganWaktu.class, ruanganWaktuId));
    return jadwalKuliah;
  }

  @Transactional
  @SuppressWarnings("unchecked")
  public List<JadwalKuliah> findByMahasiswa(Mahasiswa mahasiswa) {
    String stringQuery =
        "SELECT jk from JadwalKuliah jk WHERE jk.mahasiswa.id = :mahasiswaId ORDER BY jk.ruanganWaktu.waktuMulai";
    Query query = entityManager.createQuery(stringQuery, getEntityClass());
    query.setParameter("mahasiswaId", mahasiswa.getId());
    List<JadwalKuliah> list = query.getResultList();
    return list;
  }

  @Transactional
  @SuppressWarnings("unchecked")
  public List<JadwalKuliah> findByRuanganWaktuId(UUID ruanganWaktuId) {
    String stringQuery =
        "SELECT jk from JadwalKuliah jk WHERE jk.ruanganWaktu.id = :ruanganWaktuId";
    Query query = entityManager.createQuery(stringQuery, getEntityClass());
    query.setParameter("ruanganWaktuId", ruanganWaktuId);
    List<JadwalKuliah> list = query.getResultList();
    log.info("JADWAL KULIAH ADA = " + list.size());
    return list;
  }

  @PostConstruct
  public void init() {
    setEntityClass(JadwalKuliah.class);
    setEntityManager(entityManager);
  }

  @Transactional
  @Override
  public void updateEntity(JadwalKuliah t, JadwalKuliah entity) {
    t.setMahasiswa(entity.getMahasiswa());
    t.setMatakuliah(entity.getMatakuliah());
    t.setRuanganWaktu(entity.getRuanganWaktu());
  }
}
