package com.agrotis.agrotis.controllers;

import com.agrotis.agrotis.entities.Laboratory;
import com.agrotis.agrotis.exceptions.KeyErrorLaboratory;
import com.agrotis.agrotis.exceptions.ErrorLaboratoryNotFound;
import com.agrotis.agrotis.repositories.LaboratoryRepository;

import java.util.List;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class LaboratoryController {

  LaboratoryRepository laboratoryRepository;

  @PostMapping("/laboratories")
  public ResponseEntity<Laboratory> create(@RequestBody Laboratory laboratory)
      throws KeyErrorLaboratory {
    if (laboratory.getName() == null) {
      throw new KeyErrorLaboratory(laboratory.getName());
    }

    List<Laboratory> labs = laboratoryRepository.findByName(laboratory.getName());
    if (labs.size() == 0) {
      return new ResponseEntity<>(laboratoryRepository.save(
        laboratory), HttpStatus.OK
      );
    }
    return new ResponseEntity<>(HttpStatus.CONFLICT);
  }

  @GetMapping("/laboratories")
  public ResponseEntity<List<Laboratory>> getAll() {
    return ResponseEntity.status(HttpStatus.OK).body(laboratoryRepository.findAll());
  }

  @GetMapping("/laboratories/{name}")
  public Laboratory getOneByName(@PathVariable String name) throws ErrorLaboratoryNotFound {
    return laboratoryRepository.findOneByName(name)
      .orElseThrow(ErrorLaboratoryNotFound::new);
  }

}
