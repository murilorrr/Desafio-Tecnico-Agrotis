package com.agrotis.agrotis.controllers;

import java.util.List;

import com.agrotis.agrotis.Entities.Laboratorio;
import com.agrotis.agrotis.Exceptions.ErroChaveLaboratorio;
import com.agrotis.agrotis.Exceptions.ErroLaboratorioNaoEncontrado;
import com.agrotis.agrotis.repositories.LaboratorioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LaboratorioController {

  LaboratorioRepository laboratorioRepository;

  @PostMapping("/laboratories")
  public ResponseEntity<Laboratorio> create(@RequestBody Laboratorio laboratorio) throws Exception {
    try {
      List<Laboratorio> labs = laboratorioRepository.findByName(laboratorio.getName());
      if (labs.size() == 0) {
        return new ResponseEntity<Laboratorio>(laboratorioRepository.save(laboratorio), HttpStatus.OK);
      }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    } catch (Exception e) {
      throw new ErroChaveLaboratorio();
    }
  }

  @GetMapping("/laboratories")
  public List<Laboratorio> getAll() {
    return laboratorioRepository.findAll();
  }

  @GetMapping("/laboratories/{name}")
  public Laboratorio getOneByName(@PathVariable String name) throws ErroLaboratorioNaoEncontrado {
    return laboratorioRepository.findOneByName(name)
      .orElseThrow(() -> new ErroLaboratorioNaoEncontrado());
  }

}
