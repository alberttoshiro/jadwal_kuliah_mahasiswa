package com.albert.dao;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import com.albert.model.Mahasiswa;
import com.albert.model.Matakuliah;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MatakuliahDAO extends BaseDAO<Matakuliah> {

  @Inject
  EntityManager entityManager;

  @Inject
  Logger log;

  @Transactional
  public Set<Mahasiswa> getMahasiswa(UUID matakuliahId) {
    Matakuliah matakuliah = entityManager.find(getEntityClass(), matakuliahId);
    Set<Mahasiswa> listMahasiswa = matakuliah.getListJadwalKuliah().stream()
        .map(t -> t.getMahasiswa()).collect(Collectors.toSet());
    return listMahasiswa;
  }

  @PostConstruct
  public void init() {
    setEntityClass(Matakuliah.class);
    setEntityManager(entityManager);
  }

  @Override
  public void updateEntity(Matakuliah t, Matakuliah entity) {
    t.setNamaMatakuliah(entity.getNamaMatakuliah());
  }
}
