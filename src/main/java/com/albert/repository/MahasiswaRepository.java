package com.albert.repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import com.albert.model.Mahasiswa;
import com.albert.model.Matakuliah;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class MahasiswaRepository implements PanacheRepositoryBase<Mahasiswa, UUID> {

  public List<Mahasiswa> findByNama(String nama) {
    String namaSearch = ("%" + nama + "%").toLowerCase();
    return find("FROM Mahasiswa m WHERE LOWER(m.nama) LIKE :nama",
        Parameters.with("nama", namaSearch)).list();
  }

  public List<Mahasiswa> findByNim(String nim) {
    String nimSearch = ("%" + nim + "%").toLowerCase();
    return find("FROM Mahasiswa m WHERE LOWER(m.nim) LIKE :nim", Parameters.with("nim", nimSearch))
        .list();
  }

  @Transactional
  public Set<Matakuliah> getMatakuliah(UUID mahasiswaId) {
    Mahasiswa mahasiswa = findById(mahasiswaId);
    Set<Matakuliah> listMatakuliah = mahasiswa.getListJadwalKuliah().stream()
        .map(t -> t.getMatakuliah()).collect(Collectors.toSet());
    return listMatakuliah;
  }

  @Transactional
  public void updateMahasiswa(UUID id, Mahasiswa mahasiswaUpdate) {
    Mahasiswa mahasiswa = findById(id);
    mahasiswa.setNim(mahasiswaUpdate.getNim());
    mahasiswa.setNama(mahasiswaUpdate.getNama());
  }
}
