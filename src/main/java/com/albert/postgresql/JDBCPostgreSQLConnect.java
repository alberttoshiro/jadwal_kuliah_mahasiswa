package com.albert.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import com.albert.model.BaseTable;
import com.albert.util.AppUtil;



public abstract class JDBCPostgreSQLConnect<T extends BaseTable> {

  // private static final String URL = "jdbc:postgresql://localhost/jadwal_mahasiswa";
  // private static final String USERNAME = "postgres";
  // private static final String PASSWORD = "postgres";

  protected static final String UUID_DATA_TYPE = "uuid";
  protected static final String INTEGER_DATA_TYPE = "integer";
  protected static final String STRING_DATA_TYPE = "string";
  protected static final String TIME_DATA_TYPE = "time";

  protected static final List<Map<String, String>> emptyParameters = new ArrayList<>();

  @SuppressWarnings("unused")
  private final String url;
  @SuppressWarnings("unused")
  private final String username;
  @SuppressWarnings("unused")
  private final String password;

  private Connection connection;

  public JDBCPostgreSQLConnect(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(url, username, password);
    } catch (ClassNotFoundException e) {
      System.out.println("Unable to find driver at classpath");
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println("Something wrong during communication with database");
      e.printStackTrace();
    }
  }

  protected abstract List<T> convertToList(List<Map<String, Object>> result);

  public List<Map<String, Object>> executeQuery(String query, List<Map<String, String>> parameters,
      List<String> columnNames) {
    List<Map<String, Object>> result = new ArrayList<>();
    try (PreparedStatement st = connection.prepareStatement(query)) {
      populatePreparedStatement(parameters, st);
      try (ResultSet resultSet = st.executeQuery()) {
        while (resultSet.next()) {
          Map<String, Object> fieldMap = new HashMap<>();
          for (String columnName : columnNames) {
            fieldMap.put(columnName, resultSet.getObject(columnName));
          }
          result.add(fieldMap);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public void executeUpdate(String query, List<Map<String, String>> parameters) {
    try (PreparedStatement st = connection.prepareStatement(query)) {
      populatePreparedStatement(parameters, st);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void populateParameters(List<Map<String, String>> parameters, String value,
      String dataType) {
    Map<String, String> parameter = new HashMap<>();
    parameter.put(value, dataType);
    parameters.add(parameter);
  }

  private void populatePreparedStatement(List<Map<String, String>> parameters, PreparedStatement st)
      throws SQLException {
    for (int i = 0; i < parameters.size(); i++) {
      Map<String, String> parameter = parameters.get(i);
      for (Entry<String, String> entry : parameter.entrySet()) {
        String entryValue = entry.getValue().toLowerCase();
        String entryKey = entry.getKey();
        if (entryValue.equals(INTEGER_DATA_TYPE)) {
          st.setInt(i + 1, Integer.valueOf(entryKey));
        } else if (entryValue.equals(STRING_DATA_TYPE)) {
          st.setString(i + 1, entryKey);
        } else if (entryValue.equals(TIME_DATA_TYPE)) {
          st.setObject(i + 1, AppUtil.convertStringToLocalTime(entryKey));
        } else if (entryValue.equals(UUID_DATA_TYPE)) {
          st.setObject(i + 1, UUID.fromString(entryKey));
        }
      }
    }
  }

  protected boolean validateFindById(List<T> list) {
    if (list.size() == 0) {
      System.out.println("No object found with such id");
      return false;
    } else if (list.size() > 1) {
      throw new RuntimeException("The object found is more than one.");
    }
    return true;
  }
}
