package com.albert.repository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import com.albert.model.Mahasiswa;
import com.albert.model.Matakuliah;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class MatakuliahRepository implements PanacheRepositoryBase<Matakuliah, UUID> {

  @Transactional
  public Set<Mahasiswa> getMahasiswa(UUID matakuliahId) {
    Matakuliah matakuliah = findById(matakuliahId);
    Set<Mahasiswa> listMahasiswa = matakuliah.getListJadwalKuliah().stream()
        .map(t -> t.getMahasiswa()).collect(Collectors.toSet());
    return listMahasiswa;
  }

  @Transactional
  public void updateMatakuliah(UUID id, Mahasiswa matakuliahUpdate) {
    Matakuliah matakuliah = findById(id);
    matakuliah.setNamaMatakuliah(matakuliahUpdate.getNama());
  }
}
