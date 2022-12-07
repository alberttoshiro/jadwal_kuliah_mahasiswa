package com.albert.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "jadwal_kuliah", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"mahasiswa_id", "matakuliah_id", "ruangan_waktu_id"})})
public class JadwalKuliah extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "mahasiswa_id")
  private Mahasiswa mahasiswa;

  @ManyToOne
  @JoinColumn(name = "matakuliah_id")
  private Matakuliah matakuliah;

  @OneToOne
  @JoinColumn(name = "ruangan_waktu_id")
  private RuanganWaktu ruanganWaktu;

  public JadwalKuliah() {
    super();
  }

  public JadwalKuliah(UUID id, Mahasiswa mahasiswa, Matakuliah matakuliah,
      RuanganWaktu ruanganWaktu) {
    super(id);
    this.mahasiswa = mahasiswa;
    this.matakuliah = matakuliah;
    this.ruanganWaktu = ruanganWaktu;
  }
}
