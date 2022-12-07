package com.albert;

import java.io.File;
import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dao.MahasiswaDAO;
import org.jboss.logging.Logger;
import io.quarkus.scheduler.Scheduled;
import io.vertx.core.eventbus.EventBus;

@ApplicationScoped
public class AppScheduler {

  private static final String ROOT = "data/";
  private static final String FOLDER_MASTER = ROOT + "master";

  private static final String FOLDER_COMPOSE = ROOT + "compose";

  @Inject
  MahasiswaDAO mahasiswaDAO;

  @Inject
  EventBus eventBus;

  @Inject
  Logger log;

  @Scheduled(every = "5S")
  public void checkFolderCompose() throws IOException {
    log.trace("checking folder compose");
    File composeFolder = new File(FOLDER_COMPOSE);
    File[] listOfFilesCompose = composeFolder.listFiles();
    if (listOfFilesCompose.length > 0) {
      for (File file : listOfFilesCompose) {
        if (file.isFile()) {
          String fileName = file.getName();
          String filePath = String.format("%s/%s", FOLDER_COMPOSE, fileName);
          if (fileName.startsWith("jadwal_kuliah")) {
            log.trace("found jadwal kuliah file");
            try {
              eventBus.publish("processJadwalKuliah", filePath);
            } catch (Exception e) {
              log.trace(e);
              log.trace("You have to process master files first");
              throw e;
            }

          }
        }
      }
    }
  }

  @Scheduled(every = "2S")
  public void checkFolderMaster() throws IOException {
    log.trace("checking folder master");
    File masterFolder = new File(FOLDER_MASTER);
    File[] listOfFilesMaster = masterFolder.listFiles();
    if (listOfFilesMaster.length > 0) {
      for (File file : listOfFilesMaster) {
        if (file.isFile()) {
          String fileName = file.getName();
          String filePath = String.format("%s/%s", FOLDER_MASTER, fileName);
          if (fileName.startsWith("mahasiswa")) {
            log.trace("found mahasiswa file");
            eventBus.publish("processMahasiswa", filePath);
          } else if (fileName.startsWith("matakuliah")) {
            log.trace("found matakuliah file");
            eventBus.publish("processMatakuliah", filePath);
          } else if (fileName.startsWith("ruangan_waktu")) {
            log.trace("found ruangan waktu file");
            eventBus.publish("processRuanganWaktu", filePath);
          }
        }
      }
    }
  }
}
