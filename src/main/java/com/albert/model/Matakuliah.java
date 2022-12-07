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
public class Matakuliah extends BaseEntity {

  @Column(name = "nama_matakuliah")
  private String namaMatakuliah;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "matakuliah")
  private List<JadwalKuliah> listJadwalKuliah;

  public Matakuliah() {

  }

  public Matakuliah(UUID id, String namaMatakuliah) {
    super(id);
    this.namaMatakuliah = namaMatakuliah;
  }
}
