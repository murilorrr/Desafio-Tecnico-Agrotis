package com.agrotis.agrotis.repositories;

import com.agrotis.agrotis.entities.Laboratorio;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
  List<Laboratorio> findByName(String name);

  Optional<Laboratorio> findOneByName(String name);
}
