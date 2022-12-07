package com.albert.model;

import java.time.LocalTime;

public class JadwalKuliahMahasiswa extends JadwalKuliah {

  private static final long serialVersionUID = -6358044350661276227L;

  private String idJadwalKuliahMahasiswa;
  private String idMahasiswa;
  private String nim;
  private String nama;
  private String idMatakuliah;
  private String namaMatakuliah;

  public JadwalKuliahMahasiswa() {
    super();
  }

  public JadwalKuliahMahasiswa(String idJadwalKuliahMahasiswa, String idMahasiswa, String nim,
      String nama, String idMatakuliah, String namaMatakuliah, String id, String hari,
      String ruangan, LocalTime waktuMulai, LocalTime waktuSelesai) {
    super(id, hari, ruangan, waktuMulai, waktuSelesai);
    this.idJadwalKuliahMahasiswa = idJadwalKuliahMahasiswa;
    this.idMahasiswa = idMahasiswa;
    this.nim = nim;
    this.nama = nama;
    this.idMatakuliah = idMatakuliah;
    this.namaMatakuliah = namaMatakuliah;
  }

  public String getIdJadwalKuliahMahasiswa() {
    return idJadwalKuliahMahasiswa;
  }

  public String getIdMahasiswa() {
    return idMahasiswa;
  }

  public String getIdMatakuliah() {
    return idMatakuliah;
  }

  public String getNama() {
    return nama;
  }

  public String getNamaMatakuliah() {
    return namaMatakuliah;
  }

  public String getNim() {
    return nim;
  }

  public void setIdJadwalKuliahMahasiswa(String idJadwalKuliahMahasiswa) {
    this.idJadwalKuliahMahasiswa = idJadwalKuliahMahasiswa;
  }

  public void setIdMahasiswa(String idMahasiswa) {
    this.idMahasiswa = idMahasiswa;
  }

  public void setIdMatakuliah(String idMatakuliah) {
    this.idMatakuliah = idMatakuliah;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public void setNamaMatakuliah(String namaMatakuliah) {
    this.namaMatakuliah = namaMatakuliah;
  }

  public void setNim(String nim) {
    this.nim = nim;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("JadwalKuliahMahasiswa [idMahasiswa=").append(idMahasiswa).append(", nim=")
        .append(nim).append(", nama=").append(nama).append(", idMatakuliah=").append(idMatakuliah)
        .append(", namaMatakuliah=").append(namaMatakuliah).append("]");
    return builder.toString();
  }

}
