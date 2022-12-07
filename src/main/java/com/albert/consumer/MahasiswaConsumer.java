package com.albert.consumer;

import java.io.IOException;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dao.MahasiswaDAO;
import com.albert.model.Mahasiswa;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class MahasiswaConsumer extends BaseConsumer {

  @Inject
  MahasiswaDAO mahasiswaDAO;

  @Override
  public void insert(String[] param) {
    Mahasiswa mahasiswa = new Mahasiswa();
    mahasiswa.setId(UUID.fromString(param[0]));
    mahasiswa.setNim(param[1]);
    mahasiswa.setNama(param[2]);
    mahasiswaDAO.save(mahasiswa);
  }

  @ConsumeEvent(value = "processMahasiswa", blocking = true)
  public void processMahasiswa(String filePath) throws IOException {
    processFile(filePath, "master", "master-done");
  }

}
