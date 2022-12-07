package com.albert.dao;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import com.albert.model.Mahasiswa;
import com.albert.model.Matakuliah;

@ApplicationScoped
public class MahasiswaDAO extends BaseDAO<Mahasiswa> {

  @Inject
  EntityManager entityManager;

  @Transactional
  @SuppressWarnings("unchecked")
  public List<Mahasiswa> findByNama(String nama) {
    nama = nama.toLowerCase();
    String stringQuery = "SELECT m from Mahasiswa m WHERE LOWER(m.nama) LIKE :nama";
    Query query = entityManager.createQuery(stringQuery, getEntityClass());
    query.setParameter("nama", "%" + nama + "%");
    return query.getResultList();
  }

  @Transactional
  @SuppressWarnings("unchecked")
  public List<Mahasiswa> findByNim(String nim) {
    nim = nim.toLowerCase();
    String stringQuery = "SELECT m from Mahasiswa m WHERE LOWER(m.nim) LIKE :nim";
    Query query = entityManager.createQuery(stringQuery, getEntityClass());
    query.setParameter("nim", "%" + nim + "%");
    return query.getResultList();
  }

  @Transactional
  public Set<Matakuliah> getMatakuliah(UUID mahasiswaId) {
    Mahasiswa mahasiswa = entityManager.find(getEntityClass(), mahasiswaId);
    Set<Matakuliah> listMatakuliah = mahasiswa.getListJadwalKuliah().stream()
        .map(t -> t.getMatakuliah()).collect(Collectors.toSet());
    return listMatakuliah;
  }

  @PostConstruct
  public void init() {
    setEntityClass(Mahasiswa.class);
    setEntityManager(entityManager);
  }

  @Override
  public void updateEntity(Mahasiswa t, Mahasiswa entity) {
    t.setNim(entity.getNim());
    t.setNama(entity.getNama());
  }

}
