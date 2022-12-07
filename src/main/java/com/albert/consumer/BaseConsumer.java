package com.albert.consumer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.inject.Inject;
import org.jboss.logging.Logger;

public abstract class BaseConsumer {

  @Inject
  Logger log;

  public abstract void insert(String[] param);

  public void moveFile(String filePath, String replace, String replaceWith) throws IOException {
    try {
      log.trace("moving file " + filePath);
      Files.move(Paths.get(filePath), Paths.get(filePath.replace(replace, replaceWith)));
      log.info(filePath + " moved");
    } catch (IOException e) {
      log.error(e);
      throw e;
    }
  }

  public void processFile(String filePath, String replace, String replaceWith) throws IOException {
    log.info("starting process file " + filePath);
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line = "";
      while ((line = br.readLine()) != null) {
        String[] param = line.split(";");
        System.out.println(Arrays.toString(param));
        insert(param);
      }
    } catch (IOException e) {
      log.error(e);
      throw e;
    }
    moveFile(filePath, replace, replaceWith);
  }
}
