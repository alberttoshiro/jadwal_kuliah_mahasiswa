package com.albert.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.albert.model.JadwalKuliahMahasiswa;
import com.albert.model.Mahasiswa;
import com.albert.postgresql.JDBCPostgreSQLConnect;
import com.albert.util.AppUtil;

public class JadwalKuliahMahasiswaDAO extends JDBCPostgreSQLConnect<JadwalKuliahMahasiswa>
    implements BaseDAO<JadwalKuliahMahasiswa> {

  private static final String SELECT_ALL = "SELECT * FROM jadwal_kuliah_mahasiswa";
  private static final String SELECT_BY_ID =
      "SELECT * FROM jadwal_kuliah_mahasiswa WHERE id_jadwal_kuliah_mahasiswa = ?";
  private static final String SELECT_BY_ID_MAHASISWA =
      "SELECT jadwal_kuliah_mahasiswa.id_jadwal_kuliah_mahasiswa, "
          + "mahasiswa.id_mahasiswa, mahasiswa.nim, mahasiswa.nama, "
          + "matakuliah.id_matakuliah, matakuliah.nama_matakuliah, "
          + "jadwal_kuliah.id_jadwal_kuliah, jadwal_kuliah.hari, jadwal_kuliah.ruangan, to_char(jadwal_kuliah.waktu_mulai, 'HH24:MI') as waktu_mulai, to_char(jadwal_kuliah.waktu_selesai, 'HH24:MI') as waktu_selesai "
          + "FROM jadwal_kuliah_mahasiswa "
          + "JOIN mahasiswa ON mahasiswa.id_mahasiswa = jadwal_kuliah_mahasiswa.id_mahasiswa "
          + "JOIN jadwal_kuliah ON jadwal_kuliah.id_jadwal_kuliah = jadwal_kuliah_mahasiswa.id_jadwal_kuliah "
          + "JOIN matakuliah ON matakuliah.id_matakuliah = jadwal_kuliah_mahasiswa.id_matakuliah "
          + "WHERE jadwal_kuliah_mahasiswa.id_mahasiswa = ? ";
  private static final String INSERT_JADWAL_KULIAH_MAHASISWA =
      "INSERT INTO jadwal_kuliah_mahasiswa(id_jadwal_kuliah_mahasiswa, id_mahasiswa, id_matakuliah, id_jadwal_kuliah) VALUES (uuid_generate_v4(), ?, ?, ?)";
  private static final String DELETE_JADWAL_KULIAH_MAHASISWA =
      "DELETE FROM jadwal_kuliah WHERE id_jadwal_kuliah_mahasiswa = ?";
  private static final String UPDATE_JADWAL_KULIAH_MAHASISWA =
      "UPDATE jadwal_kuliah SET id_mahasiswa = ?, id_matakuliah = ?, id_jadwal_kuliah = ? WHERE id_jadwal_kuliah_mahasiswa = ?";


  private static final String COLUMN_ID = "id_jadwal_kuliah_mahasiswa";

  private static final String COLUMN_ID_MAHASISWA = "id_mahasiswa";
  private static final String COLUMN_NIM = "nim";
  private static final String COLUMN_NAMA = "nama";

  private static final String COLUMN_ID_MATAKULIAH = "id_matakuliah";
  private static final String COLUMN_NAMA_MATAKULIAH = "nama_matakuliah";

  private static final String COLUMN_ID_JADWAL_KULIAH = "id_jadwal_kuliah";
  private static final String COLUMN_HARI = "hari";
  private static final String COLUMN_RUANGAN = "ruangan";
  private static final String COLUMN_WAKTU_MULAI = "waktu_mulai";
  private static final String COLUMN_WAKTU_SELESAI = "waktu_selesai";

  private static final List<String> COLUMNS_NAMES =
      Arrays.asList(COLUMN_ID, COLUMN_ID_MAHASISWA, COLUMN_NIM, COLUMN_NAMA, COLUMN_ID_MATAKULIAH,
          COLUMN_NAMA_MATAKULIAH, COLUMN_ID_JADWAL_KULIAH, COLUMN_HARI, COLUMN_RUANGAN,
          COLUMN_WAKTU_MULAI, COLUMN_WAKTU_SELESAI);

  public JadwalKuliahMahasiswaDAO(String url, String username, String password) {
    super(url, username, password);
  }

  @Override
  protected List<JadwalKuliahMahasiswa> convertToList(List<Map<String, Object>> result) {
    List<JadwalKuliahMahasiswa> list = new ArrayList<>();
    for (Map<String, Object> map : result) {
      JadwalKuliahMahasiswa jadwalKuliahMahasiswa = new JadwalKuliahMahasiswa(
          map.get(COLUMN_ID).toString(), map.get(COLUMN_ID_MAHASISWA).toString(),
          map.get(COLUMN_NIM).toString(), map.get(COLUMN_NAMA).toString(),
          map.get(COLUMN_ID_MATAKULIAH).toString(), map.get(COLUMN_NAMA_MATAKULIAH).toString(),
          map.get(COLUMN_ID_JADWAL_KULIAH).toString(), map.get(COLUMN_HARI).toString(),
          map.get(COLUMN_RUANGAN).toString(),
          AppUtil.convertStringToLocalTime(map.get(COLUMN_WAKTU_MULAI).toString()),
          AppUtil.convertStringToLocalTime(map.get(COLUMN_WAKTU_SELESAI).toString()));
      list.add(jadwalKuliahMahasiswa);
    }
    return list;
  }

  @Override
  public void deleteById(String id) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, id, UUID_DATA_TYPE);
    executeUpdate(DELETE_JADWAL_KULIAH_MAHASISWA, parameters);
  }

  @Override
  public JadwalKuliahMahasiswa findById(String id) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, id, UUID_DATA_TYPE);

    List<Map<String, Object>> result = executeQuery(SELECT_BY_ID, parameters, COLUMNS_NAMES);
    List<JadwalKuliahMahasiswa> listJadwalKuliahMahasiswa = convertToList(result);

    if (validateFindById(listJadwalKuliahMahasiswa)) {
      return listJadwalKuliahMahasiswa.get(0);
    }
    return null;
  }

  @Override
  public List<JadwalKuliahMahasiswa> getAll() {
    List<Map<String, Object>> result = executeQuery(SELECT_ALL, emptyParameters, COLUMNS_NAMES);
    List<JadwalKuliahMahasiswa> listJadwalKuliahMahasiswa = convertToList(result);
    return listJadwalKuliahMahasiswa;
  }

  public List<JadwalKuliahMahasiswa> getJadwalKuliahMahasiswaByMahasiswa(Mahasiswa mahasiswa) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, mahasiswa.getId(), UUID_DATA_TYPE);

    List<Map<String, Object>> result =
        executeQuery(SELECT_BY_ID_MAHASISWA, parameters, COLUMNS_NAMES);

    List<JadwalKuliahMahasiswa> listJadwalKuliahMahasiswa = convertToList(result);
    return listJadwalKuliahMahasiswa;
  }

  @Override
  public void insert(JadwalKuliahMahasiswa data) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, data.getIdMahasiswa(), UUID_DATA_TYPE);
    populateParameters(parameters, data.getIdMatakuliah(), UUID_DATA_TYPE);
    populateParameters(parameters, data.getId(), UUID_DATA_TYPE);
    executeUpdate(INSERT_JADWAL_KULIAH_MAHASISWA, parameters);
  }

  @Override
  public void update(JadwalKuliahMahasiswa data) {
    List<Map<String, String>> parameters = new ArrayList<>();
    populateParameters(parameters, data.getIdMahasiswa(), UUID_DATA_TYPE);
    populateParameters(parameters, data.getIdMatakuliah(), UUID_DATA_TYPE);
    populateParameters(parameters, data.getId(), UUID_DATA_TYPE);
    populateParameters(parameters, data.getIdJadwalKuliahMahasiswa(), UUID_DATA_TYPE);
    executeUpdate(UPDATE_JADWAL_KULIAH_MAHASISWA, parameters);
  }

}
