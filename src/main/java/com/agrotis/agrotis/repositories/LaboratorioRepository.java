package com.agrotis.agrotis.repositories;


import java.util.List;

import com.agrotis.agrotis.Entities.Laboratorio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long>{
  List<Laboratorio> findByName(String name);
  Laboratorio findOneByName(String name);
}
