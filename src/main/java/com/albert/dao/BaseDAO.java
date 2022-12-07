package com.albert.dao;

import java.util.List;
import com.albert.model.BaseTable;

public interface BaseDAO<T extends BaseTable> {

  public void deleteById(String id);

  public T findById(String id);

  public List<T> getAll();

  public void insert(T data);

  public void update(T data);
}
