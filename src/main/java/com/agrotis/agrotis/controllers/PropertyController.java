package com.agrotis.agrotis.controllers;

import com.agrotis.agrotis.entities.Property;
import com.agrotis.agrotis.exceptions.KeyErrorProperty;
import com.agrotis.agrotis.exceptions.KeyError;
import com.agrotis.agrotis.exceptions.ErrorPropertyNotFound;
import com.agrotis.agrotis.repositories.PropertyRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PropertyController {

  private final PropertyRepository propertyRepository;

  @Autowired
  public PropertyController(PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }

  @PostMapping("/ownerships")
  public ResponseEntity<Property> create(@RequestBody Property propriedade) throws
          KeyError {
    if ((propriedade.getCnpj() == null || propriedade.getName() == null)) {
      throw new KeyErrorProperty(null);
    }

    List<Property> props = propertyRepository.findByName(propriedade.getName());
    if (props.size() == 0) {
      return new ResponseEntity<>(propertyRepository.save(
              propriedade), HttpStatus.OK
      );
    }
    return new ResponseEntity<>(HttpStatus.CONFLICT);
  }

  @GetMapping("/ownerships")
  public ResponseEntity<List<Property>> getAll() {
    return ResponseEntity.status(HttpStatus.OK).body(propertyRepository.findAll());
  }

  @GetMapping("/ownerships/{name}")
  public Property getOneByName(@PathVariable String name) throws ErrorPropertyNotFound {
    Optional<Property> propriedade = propertyRepository.findOneByName(name);
    if (propriedade.isPresent()) {
      return propriedade.get();
    }
    throw new ErrorPropertyNotFound();
  }

}

