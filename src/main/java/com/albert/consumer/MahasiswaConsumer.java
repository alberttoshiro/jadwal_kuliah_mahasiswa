package com.albert.consumer;

import java.io.IOException;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import com.albert.model.Mahasiswa;
import com.albert.repository.MahasiswaRepository;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class MahasiswaConsumer extends BaseConsumer {

  @Inject
  MahasiswaRepository mahasiswaRepository;

  @Transactional
  @Override
  public void insert(String[] param) {
    Mahasiswa mahasiswa = new Mahasiswa();
    mahasiswa.setId(UUID.fromString(param[0]));
    mahasiswa.setNim(param[1]);
    mahasiswa.setNama(param[2]);
    mahasiswaRepository.persist(mahasiswa);
  }

  @ConsumeEvent(value = "processMahasiswa", blocking = true)
  public void processMahasiswa(String filePath) throws IOException {
    processFile(filePath, "master", "master-done");
  }

}
