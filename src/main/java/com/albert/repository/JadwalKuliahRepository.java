package com.albert.repository;

import java.util.List;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import com.albert.model.JadwalKuliah;
import com.albert.model.Mahasiswa;
import com.albert.model.Matakuliah;
import com.albert.model.RuanganWaktu;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class JadwalKuliahRepository implements PanacheRepositoryBase<JadwalKuliah, UUID> {

  public JadwalKuliah convertToJadwalKuliah(UUID mahasiswaId, UUID matakuliahId,
      UUID ruanganWaktuId) {
    JadwalKuliah jadwalKuliah = new JadwalKuliah();
    jadwalKuliah.setMahasiswa(getEntityManager().getReference(Mahasiswa.class, mahasiswaId));
    jadwalKuliah.setMatakuliah(getEntityManager().getReference(Matakuliah.class, matakuliahId));
    jadwalKuliah
        .setRuanganWaktu(getEntityManager().getReference(RuanganWaktu.class, ruanganWaktuId));
    return jadwalKuliah;
  }

  @Transactional
  public List<JadwalKuliah> findByMahasiswa(Mahasiswa mahasiswa) {
    String stringQuery =
        "FROM JadwalKuliah jk WHERE jk.mahasiswa.id = :mahasiswaId ORDER BY jk.ruanganWaktu.waktuMulai";
    return find(stringQuery, Parameters.with("mahasiswaId", mahasiswa.getId())).list();
  }

  @Transactional
  public List<JadwalKuliah> findByRuanganWaktuId(UUID ruanganWaktuId) {
    String stringQuery =
        "SELECT jk from JadwalKuliah jk WHERE jk.ruanganWaktu.id = :ruanganWaktuId";
    return find(stringQuery, Parameters.with("ruanganWaktuId", ruanganWaktuId)).list();
  }

  @Transactional
  public void updateJadwalKuliah(UUID id, JadwalKuliah jadwalKuliahUpdate) {
    JadwalKuliah jadwalKuliah = findById(id);
    jadwalKuliah.setMahasiswa(jadwalKuliahUpdate.getMahasiswa());
    jadwalKuliah.setMatakuliah(jadwalKuliahUpdate.getMatakuliah());
    jadwalKuliah.setRuanganWaktu(jadwalKuliahUpdate.getRuanganWaktu());
  }

}
