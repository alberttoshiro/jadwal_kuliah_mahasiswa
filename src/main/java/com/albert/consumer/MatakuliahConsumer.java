package com.albert.consumer;

import java.io.IOException;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dao.MatakuliahDAO;
import com.albert.model.Matakuliah;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class MatakuliahConsumer extends BaseConsumer {

  @Inject
  MatakuliahDAO matakuliahDAO;

  @Override
  public void insert(String[] param) {
    Matakuliah matakuliah = new Matakuliah();
    matakuliah.setId(UUID.fromString(param[0]));
    matakuliah.setNamaMatakuliah(param[1]);
    matakuliahDAO.save(matakuliah);
  }

  @ConsumeEvent(value = "processMatakuliah", blocking = true)
  public void processMatakuliah(String filePath) throws IOException {
    processFile(filePath, "master", "master-done");
  }
}
