package com.agrotis.agrotis.repositories;

import com.agrotis.agrotis.entities.Propriedade;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {
  public Optional<Propriedade> findOneByName(String name);
  
  public List<Propriedade> findByName(String name);
}