package com.agrotis.agrotis.repositories;

import com.agrotis.agrotis.entities.Property;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
  Optional<Property> findOneByName(String name);
  
  List<Property> findByName(String name);
}