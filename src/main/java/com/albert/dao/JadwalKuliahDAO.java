package com.albert.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.albert.model.JadwalKuliah;
import com.albert.postgresql.JDBCPostgreSQLConnect;
import com.albert.util.AppUtil;

public class JadwalKuliahDAO extends JDBCPostgreSQLConnect<JadwalKuliah>
    implements BaseDAO<JadwalKuliah> {

  private static final String SELECT_ALL = "SELECT * FROM jadwal_kuliah";
  private static final String SELECT_BY_ID =
      "SELECT * FROM jadwal_kuliah WHERE id_jadwal_kuliah = ?";
  private static final String INSERT_JADWAL_KULIAH =
      "INSERT INTO jadwal_kuliah(id_jadwal_kuliah, hari, ruangan, waktu_mulai, waktu_selesai) VALUES (uuid_generate_v4(), ?, ?, ?, ?)";
  private static final String DELETE_JADWAL_KULIAH =
      "DELETE FROM jadwal_kuliah WHERE id_jadwal_kuliah = ?";
  private static final String UPDATE_JADWAL_KULIAH =
      "UPDATE jadwal_kuliah SET hari = ?, ruangan = ?, waktu_mulai = ?, waktu_selesai = ? WHERE id_jadwal_kuliah = ?";

  private static final String COLUMN_ID = "id_jadwal_kuliah";
  private static final String COLUMN_HARI = "hari";
  private static final String COLUMN_RUANGAN = "ruangan";
  private static final String COLUMN_WAKTU_MULAI = "waktu_mulai";
  private static final String COLUMN_WAKTU_SELESAI = "waktu_selesai";

  private static final List<String> COLUMNS_NAMES = Arrays.asList(COLUMN_ID, COLUMN_HARI,
      COLUMN_RUANGAN, COLUMN_WAKTU_MULAI, COLUMN_WAKTU_SELESAI);

  public JadwalKuliahDAO(String url, String username, String password) {
    super(url, username, password);
  }

  @Override
  protected List<JadwalKuliah> convertToList(List<Map<String, Object>> result) {
    List<JadwalKuliah> list = new ArrayList<>();
    for (Map<String, Object> map : result) {
      JadwalKuliah jadwalKuliah = new JadwalKuliah(map.get(COLUMN_ID).toString(),
          map.get(COLUMN_HARI).toString(), map.get(COLUMN_RUANGAN).toString(),
          AppUtil.convertStringToLocalTime(map.get(COLUMN_WAKTU_MULAI).toString()),
          AppUtil.convertStringToLocalTime(map.get(COLUMN_WAKTU_SELESAI).toString()));
      list.add(jadwalKuliah);
    }
    return list;
  }

  @Override
  public void deleteById(String id) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, id, UUID_DATA_TYPE);
    executeUpdate(DELETE_JADWAL_KULIAH, parameters);
  }

  @Override
  public JadwalKuliah findById(String id) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, id, UUID_DATA_TYPE);

    List<Map<String, Object>> result = executeQuery(SELECT_BY_ID, parameters, COLUMNS_NAMES);
    List<JadwalKuliah> listJadwalKuliah = convertToList(result);
    if (validateFindById(listJadwalKuliah)) {
      return listJadwalKuliah.get(0);
    }
    return null;
  }

  @Override
  public List<JadwalKuliah> getAll() {
    List<Map<String, Object>> result = executeQuery(SELECT_ALL, emptyParameters, COLUMNS_NAMES);
    List<JadwalKuliah> listJadwalKuliah = convertToList(result);
    return listJadwalKuliah;
  }

  @Override
  public void insert(JadwalKuliah data) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, data.getHari(), STRING_DATA_TYPE);
    populateParameters(parameters, data.getRuangan(), STRING_DATA_TYPE);
    populateParameters(parameters, AppUtil.convertLocalTimeToString(data.getWaktuMulai()),
        TIME_DATA_TYPE);
    populateParameters(parameters, AppUtil.convertLocalTimeToString(data.getWaktuSelesai()),
        TIME_DATA_TYPE);
    executeUpdate(INSERT_JADWAL_KULIAH, parameters);
  }

  @Override
  public void update(JadwalKuliah data) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, data.getHari(), STRING_DATA_TYPE);
    populateParameters(parameters, data.getRuangan(), STRING_DATA_TYPE);
    populateParameters(parameters, AppUtil.convertLocalTimeToString(data.getWaktuMulai()),
        TIME_DATA_TYPE);
    populateParameters(parameters, AppUtil.convertLocalTimeToString(data.getWaktuSelesai()),
        TIME_DATA_TYPE);
    populateParameters(parameters, data.getId(), UUID_DATA_TYPE);
    executeUpdate(UPDATE_JADWAL_KULIAH, parameters);
  }

}
