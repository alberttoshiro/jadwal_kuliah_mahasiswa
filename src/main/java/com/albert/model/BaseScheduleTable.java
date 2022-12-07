package com.albert.model;

import java.time.LocalTime;

public abstract class BaseScheduleTable extends BaseTable {

  private static final long serialVersionUID = -6390880593638813799L;

  private LocalTime waktuMulai;
  private LocalTime waktuSelesai;

  public BaseScheduleTable() {
    super();
  }

  public BaseScheduleTable(String id, LocalTime waktuMulai, LocalTime waktuSelesai) {
    super(id);
    this.waktuMulai = waktuMulai;
    this.waktuSelesai = waktuSelesai;
  }

  public LocalTime getWaktuMulai() {
    return waktuMulai;
  }

  public LocalTime getWaktuSelesai() {
    return waktuSelesai;
  }

  public void setWaktuMulai(LocalTime waktuMulai) {
    this.waktuMulai = waktuMulai;
  }

  public void setWaktuSelesai(LocalTime waktuSelesai) {
    this.waktuSelesai = waktuSelesai;
  }



}
