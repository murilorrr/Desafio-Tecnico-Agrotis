package com.agrotis.agrotis.repositories;


import com.agrotis.agrotis.Entities.Propriedade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropriedadeRepository extends JpaRepository<Propriedade, Long>{
  
}