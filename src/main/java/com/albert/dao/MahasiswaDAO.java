package com.albert.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.albert.model.Mahasiswa;
import com.albert.postgresql.JDBCPostgreSQLConnect;

public class MahasiswaDAO extends JDBCPostgreSQLConnect<Mahasiswa> implements BaseDAO<Mahasiswa> {

  private static final String SELECT_ALL = "SELECT * FROM mahasiswa";
  private static final String SELECT_BY_ID = "SELECT * FROM mahasiswa WHERE id_mahasiswa = ?";
  private static final String SELECT_BY_NAMA = "SELECT * FROM mahasiswa WHERE nama ILIKE ?";
  private static final String SELECT_BY_NIM = "SELECT * FROM mahasiswa WHERE nim ILIKE ?";
  private static final String INSERT_MAHASISWA =
      "INSERT INTO mahasiswa(id_mahasiswa, nim, nama) VALUES (uuid_generate_v4(), ?, ?)";
  private static final String DELETE_MAHASISWA = "DELETE FROM mahasiswa WHERE id_mahasiswa = ?";
  private static final String UPDATE_MAHASISWA = "UPDATE mahasiswa SET nama = ? WHERE nim = ?";

  private static final String COLUMN_ID = "id_mahasiswa";
  private static final String COLUMN_NIM = "nim";
  private static final String COLUMN_NAMA = "nama";

  private static final List<String> COLUMNS_NAMES =
      Arrays.asList(COLUMN_ID, COLUMN_NIM, COLUMN_NAMA);

  public MahasiswaDAO(String url, String username, String password) {
    super(url, username, password);
  }

  @Override
  protected List<Mahasiswa> convertToList(List<Map<String, Object>> result) {
    List<Mahasiswa> list = new ArrayList<>();
    for (Map<String, Object> map : result) {
      Mahasiswa mahasiswa = new Mahasiswa(map.get(COLUMN_ID).toString(),
          map.get(COLUMN_NIM).toString(), map.get(COLUMN_NAMA).toString());
      list.add(mahasiswa);
    }
    return list;
  }

  @Override
  public void deleteById(String id) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, id, UUID_DATA_TYPE);
    executeUpdate(DELETE_MAHASISWA, parameters);
  }

  @Override
  public Mahasiswa findById(String id) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, id, UUID_DATA_TYPE);

    List<Map<String, Object>> result = executeQuery(SELECT_BY_ID, parameters, COLUMNS_NAMES);
    List<Mahasiswa> listMahasiswa = convertToList(result);

    if (validateFindById(listMahasiswa)) {
      return listMahasiswa.get(0);
    }
    return null;
  }

  @Override
  public List<Mahasiswa> getAll() {
    List<Map<String, Object>> result = executeQuery(SELECT_ALL, emptyParameters, COLUMNS_NAMES);
    List<Mahasiswa> listMahasiswa = convertToList(result);
    return listMahasiswa;
  }

  private List<Mahasiswa> getMahasiswa(String searchParam, String query) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, "%" + searchParam + "%", STRING_DATA_TYPE);

    List<Map<String, Object>> result = executeQuery(query, parameters, COLUMNS_NAMES);

    List<Mahasiswa> listMahasiswa = convertToList(result);
    return listMahasiswa;
  }

  public List<Mahasiswa> getMahasiswaByNama(String name) {
    return getMahasiswa(name, SELECT_BY_NAMA);
  }

  public List<Mahasiswa> getMahasiswaByNim(String nim) {
    return getMahasiswa(nim, SELECT_BY_NIM);
  }

  @Override
  public void insert(Mahasiswa data) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, data.getNim(), STRING_DATA_TYPE);
    populateParameters(parameters, data.getNama(), STRING_DATA_TYPE);
    executeUpdate(INSERT_MAHASISWA, parameters);
  }

  @Override
  public void update(Mahasiswa data) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, data.getNama(), STRING_DATA_TYPE);
    populateParameters(parameters, data.getNim(), STRING_DATA_TYPE);
    executeUpdate(UPDATE_MAHASISWA, parameters);
  }

}
