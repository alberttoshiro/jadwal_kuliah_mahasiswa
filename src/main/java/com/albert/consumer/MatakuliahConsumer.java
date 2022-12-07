package com.albert.consumer;

import java.io.IOException;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import com.albert.model.Matakuliah;
import com.albert.repository.MatakuliahRepository;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class MatakuliahConsumer extends BaseConsumer {

  @Inject
  MatakuliahRepository matakuliahRepository;

  @Transactional
  @Override
  public void insert(String[] param) {
    Matakuliah matakuliah = new Matakuliah();
    matakuliah.setId(UUID.fromString(param[0]));
    matakuliah.setNamaMatakuliah(param[1]);
    matakuliahRepository.persist(matakuliah);
  }

  @ConsumeEvent(value = "processMatakuliah", blocking = true)
  public void processMatakuliah(String filePath) throws IOException {
    processFile(filePath, "master", "master-done");
  }
}
