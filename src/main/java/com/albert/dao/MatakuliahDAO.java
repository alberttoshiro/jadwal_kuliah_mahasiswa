package com.albert.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.albert.model.Matakuliah;
import com.albert.postgresql.JDBCPostgreSQLConnect;

public class MatakuliahDAO extends JDBCPostgreSQLConnect<Matakuliah>
    implements BaseDAO<Matakuliah> {

  private static final String SELECT_ALL = "SELECT * FROM matakuliah";
  private static final String SELECT_BY_ID = "SELECT * FROM mahasiswa WHERE id_matakuliah = ?";
  private static final String INSERT_MATAKULIAH =
      "INSERT INTO matakuliah(id_matakuliah, nama_matakuliah) VALUES (uuid_generate_v4(), ?)";
  private static final String DELETE_MATAKULIAH = "DELETE FROM matakuliah WHERE id_matakuliah = ?";
  private static final String UPDATE_MATAKULIAH =
      "UPDATE matakuliah SET nama = ? WHERE id_matakuliah = ?";

  private static final String COLUMN_ID = "id_matakuliah";
  private static final String COLUMN_NAMA = "nama_matakuliah";

  private static final List<String> COLUMNS_NAMES = Arrays.asList(COLUMN_ID, COLUMN_NAMA);

  public MatakuliahDAO(String url, String username, String password) {
    super(url, username, password);
  }

  @Override
  protected List<Matakuliah> convertToList(List<Map<String, Object>> result) {
    List<Matakuliah> list = new ArrayList<>();
    for (Map<String, Object> map : result) {
      Matakuliah matakuliah =
          new Matakuliah(map.get(COLUMN_ID).toString(), map.get(COLUMN_NAMA).toString());
      list.add(matakuliah);
    }
    return list;
  }

  @Override
  public void deleteById(String id) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, id, UUID_DATA_TYPE);
    executeUpdate(DELETE_MATAKULIAH, parameters);
  }

  @Override
  public Matakuliah findById(String id) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, id, UUID_DATA_TYPE);

    List<Map<String, Object>> result = executeQuery(SELECT_BY_ID, parameters, COLUMNS_NAMES);
    List<Matakuliah> listMatakuliah = convertToList(result);

    if (validateFindById(listMatakuliah)) {
      return listMatakuliah.get(0);
    }
    return null;
  }

  @Override
  public List<Matakuliah> getAll() {
    List<Map<String, Object>> result = executeQuery(SELECT_ALL, emptyParameters, COLUMNS_NAMES);
    List<Matakuliah> listMatakuliah = convertToList(result);
    return listMatakuliah;
  }

  @Override
  public void insert(Matakuliah data) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, data.getNamaMatakuliah(), STRING_DATA_TYPE);
    executeUpdate(INSERT_MATAKULIAH, parameters);
  }

  @Override
  public void update(Matakuliah data) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, data.getNamaMatakuliah(), STRING_DATA_TYPE);
    populateParameters(parameters, data.getId(), UUID_DATA_TYPE);
    executeUpdate(UPDATE_MATAKULIAH, parameters);
  }

}
