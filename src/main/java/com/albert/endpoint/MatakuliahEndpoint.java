package com.albert.endpoint;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.albert.dto.MatakuliahDTO;
import com.albert.dto.MatakuliahMahasiswaDTO;
import com.albert.mapper.MatakuliahMahasiswaMapper;
import com.albert.mapper.MatakuliahMapper;
import com.albert.repository.MatakuliahRepository;
import org.jboss.logging.Logger;

@Path("matakuliah")
@Produces(MediaType.APPLICATION_JSON)
public class MatakuliahEndpoint {

  @Inject
  Logger log;

  @Inject
  MatakuliahRepository matakuliahRepository;

  @Inject
  MatakuliahMapper matakuliahMapper;

  @Inject
  MatakuliahMahasiswaMapper matakuliahMahasiswaMapper;

  @GET
  @Path("/")
  public List<MatakuliahDTO> getAllMatakuliah() {
    return matakuliahRepository.listAll().stream().map(t -> matakuliahMapper.toMatakuliahDTO(t))
        .collect(Collectors.toList());
  }

  @GET
  @Path("{id}")
  public MatakuliahDTO getById(@PathParam("id") String id) {
    return matakuliahMapper.toMatakuliahDTO(matakuliahRepository.findById(UUID.fromString(id)));
  }

  @GET
  @Path("{id}/mahasiswa")
  @Transactional
  public MatakuliahMahasiswaDTO getMatakuliahMahasiswaDTO(@PathParam("id") String id) {
    return matakuliahMahasiswaMapper
        .toMatakuliahMahasiswaDTO(matakuliahRepository.findById(UUID.fromString(id)));
  }
}
