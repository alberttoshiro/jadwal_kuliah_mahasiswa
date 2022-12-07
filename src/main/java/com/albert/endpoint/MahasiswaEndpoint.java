package com.albert.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.albert.dao.JadwalKuliahDAO;
import com.albert.dao.MahasiswaDAO;
import com.albert.dto.JadwalKuliahDTO;
import com.albert.dto.MahasiswaDTO;
import com.albert.dto.MahasiswaMatakuliahDTO;
import com.albert.mapper.JadwalKuliahMapper;
import com.albert.mapper.MahasiswaMapper;
import com.albert.mapper.MahasiswaMatakuliahMapper;
import com.albert.model.Mahasiswa;
import org.jboss.logging.Logger;

@Path("mahasiswa")
@Produces(MediaType.APPLICATION_JSON)
public class MahasiswaEndpoint {

  @Inject
  Logger log;

  @Inject
  MahasiswaDAO mahasiswaDAO;

  @Inject
  JadwalKuliahDAO jadwalKuliahDAO;

  @Inject
  MahasiswaMapper mahasiswaMapper;

  @Inject
  MahasiswaMatakuliahMapper mahasiswaMatakuliahMapper;

  @Inject
  JadwalKuliahMapper jadwalKuliahMapper;

  @GET
  @Path("/")
  public List<MahasiswaDTO> getAllMahasiswa() {
    return getMahasiswaDTO(mahasiswaDAO.getAll());
  }

  @GET
  @Path("nama/{nama}")
  public List<MahasiswaDTO> getByNama(@PathParam("nama") String nama) {
    return getMahasiswaDTO(mahasiswaDAO.findByNama(nama));
  }

  @GET
  @Path("nim/{nim}")
  public List<MahasiswaDTO> getByNim(@PathParam("nim") String nim) {
    return getMahasiswaDTO(mahasiswaDAO.findByNim(nim));
  }

  @GET
  @Path("nama/{nama}/jadwal-kuliah")
  public List<JadwalKuliahDTO> getJadwalKuliahByNama(@PathParam("nama") String nama) {
    return getJadwalKuliahDTO(mahasiswaDAO.findByNama(nama));
  }

  @GET
  @Path("nim/{nim}/jadwal-kuliah")
  public List<JadwalKuliahDTO> getJadwalKuliahByNim(@PathParam("nim") String nim) {
    return getJadwalKuliahDTO(mahasiswaDAO.findByNim(nim));
  }

  public List<JadwalKuliahDTO> getJadwalKuliahDTO(List<Mahasiswa> listMahasiswa) {
    List<JadwalKuliahDTO> listJadwalKuliahDTO = new ArrayList<>();
    for (Mahasiswa mahasiswa : listMahasiswa) {
      listJadwalKuliahDTO.add(jadwalKuliahMapper.toJadwalKuliahDTO(mahasiswa));
    }
    return listJadwalKuliahDTO;
  }

  public List<MahasiswaDTO> getMahasiswaDTO(List<Mahasiswa> listMahasiswa) {
    return listMahasiswa.stream().map(t -> mahasiswaMapper.toMahasiswaDTO(t))
        .collect(Collectors.toList());
  }

  @GET
  @Path("nama/{nama}/matakuliah")
  @Transactional
  public List<MahasiswaMatakuliahDTO> getMahasiswaMatakuliahByNama(@PathParam("nama") String nama) {
    return getMahasiswaMatakuliahDTO(mahasiswaDAO.findByNama(nama));
  }

  @GET
  @Path("nim/{nim}/matakuliah")
  @Transactional
  public List<MahasiswaMatakuliahDTO> getMahasiswaMatakuliahByNim(@PathParam("nim") String nim) {
    return getMahasiswaMatakuliahDTO(mahasiswaDAO.findByNim(nim));
  }

  public List<MahasiswaMatakuliahDTO> getMahasiswaMatakuliahDTO(List<Mahasiswa> listMahasiswa) {
    return listMahasiswa.stream().map(t -> mahasiswaMatakuliahMapper.toMahasiswaMatakuliahDTO(t))
        .collect(Collectors.toList());
  }

}
