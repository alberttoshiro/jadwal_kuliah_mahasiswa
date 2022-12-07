package com.albert.model;

public class Mahasiswa extends BaseTable {

  private static final long serialVersionUID = -1246319439990949499L;

  private String nim;
  private String nama;

  public Mahasiswa() {
    super();
  }

  public Mahasiswa(String id, String nim, String nama) {
    super(id);
    this.nim = nim;
    this.nama = nama;
  }

  public String getNama() {
    return nama;
  }

  public String getNim() {
    return nim;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public void setNim(String nim) {
    this.nim = nim;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Mahasiswa [nim=").append(nim).append(", nama=").append(nama)
        .append(", toString()=").append(super.toString()).append("]");
    return builder.toString();
  }

}
