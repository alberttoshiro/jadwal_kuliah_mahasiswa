package com.albert.model;

import java.io.Serializable;

public abstract class BaseTable implements Serializable {

  private static final long serialVersionUID = 54420070238017549L;

  private String id;

  public BaseTable() {
    super();
  }

  public BaseTable(String id) {
    super();
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("BaseTable [id=").append(id).append("]");
    return builder.toString();
  }

}
