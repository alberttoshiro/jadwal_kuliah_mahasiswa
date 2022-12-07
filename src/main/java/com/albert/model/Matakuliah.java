package com.albert.model;

public class Matakuliah extends BaseTable {

  private static final long serialVersionUID = -8287718414081642062L;

  private String namaMatakuliah;

  public Matakuliah() {
    super();
  }

  public Matakuliah(String id, String namaMatakuliah) {
    super(id);
    this.namaMatakuliah = namaMatakuliah;
  }

  public String getNamaMatakuliah() {
    return namaMatakuliah;
  }

  public void setNamaMatakuliah(String namaMatakuliah) {
    this.namaMatakuliah = namaMatakuliah;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Matakuliah [namaMatakuliah=").append(namaMatakuliah).append(", toString()=")
        .append(super.toString()).append("]");
    return builder.toString();
  }

}
