package com.agrotis.agrotis.repositories;

import com.agrotis.agrotis.entities.Laboratory;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
  List<Laboratory> findByName(String name);

  Optional<Laboratory> findOneByName(String name);
}
