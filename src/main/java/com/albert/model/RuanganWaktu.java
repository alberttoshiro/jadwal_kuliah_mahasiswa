package com.albert.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "ruangan_waktu")
public class RuanganWaktu extends BaseEntity {

  @Column(name = "nomor_ruangan")
  private String nomorRuangan;

  @Column(name = "waktu_mulai")
  private LocalDateTime waktuMulai;

  @Column(name = "waktu_selesai")
  private LocalDateTime waktuSelesai;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "ruanganWaktu")
  private JadwalKuliah jadwalKuliah;

  public RuanganWaktu() {

  }

  public RuanganWaktu(UUID id, String nomorRuangan, LocalDateTime waktuMulai,
      LocalDateTime waktuSelesai) {
    super(id);
    this.nomorRuangan = nomorRuangan;
    this.waktuMulai = waktuMulai;
    this.waktuSelesai = waktuSelesai;
  }

}
