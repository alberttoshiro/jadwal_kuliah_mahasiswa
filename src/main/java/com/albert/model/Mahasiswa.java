package com.albert.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class Mahasiswa extends BaseEntity {

  @Column(name = "nim", unique = true)
  private String nim;

  @Column(name = "nama")
  private String nama;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "mahasiswa")
  private List<JadwalKuliah> listJadwalKuliah;

  public Mahasiswa() {

  }

  public Mahasiswa(UUID id, String nim, String nama) {
    super(id);
    this.nim = nim;
    this.nama = nama;
  }
}
