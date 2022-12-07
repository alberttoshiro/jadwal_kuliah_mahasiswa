package com.albert.processdata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.albert.dao.BaseDAO;
import com.albert.model.BaseTable;

public abstract class ProcessData<T extends BaseDAO<U>, U extends BaseTable> implements Runnable {
  private String folderName;
  private String filePath;
  private T DAO;

  public ProcessData(String folderName, String filePath, T DAO) {
    super();
    this.folderName = folderName;
    this.filePath = filePath;
    this.DAO = DAO;
  }

  public abstract U convertToU(String[] param);

  public String getFilePath() {
    return filePath;
  }

  public String getFolderName() {
    return folderName;
  }

  public void moveFile() {
    try {
      Files.move(Paths.get(filePath),
          Paths.get(filePath.replace(folderName, folderName + "-done")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean processFile() {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line = "";
      while ((line = br.readLine()) != null) {
        String[] param = line.split(";");
        U u = convertToU(param);
        DAO.insert(u);
      }
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public void run() {
    if (processFile()) {
      moveFile();
    }
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public void setFolderName(String folderName) {
    this.folderName = folderName;
  }

}
