package com.agrotis.agrotis.controllers;

import java.util.List;

import com.agrotis.agrotis.Entities.Propriedade;
import com.agrotis.agrotis.Exceptions.ErroChavePropriedade;
import com.agrotis.agrotis.Exceptions.ErroDeChave;
import com.agrotis.agrotis.repositories.PropriedadeRepository;

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
public class PropriedadesController {

  PropriedadeRepository propriedadeRepository;

  @PostMapping("/ownership")
  public ResponseEntity<Propriedade> create(@RequestBody Propriedade propriedade) throws ErroDeChave {
    try {
      List<Propriedade> props = propriedadeRepository.findByName(propriedade.getName());
      if (props.size() == 0) {
        return new ResponseEntity<Propriedade>(propriedadeRepository.save(propriedade), HttpStatus.OK);
      }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    } catch (Exception e) {
      throw new ErroChavePropriedade();
    }
  }

  @GetMapping("/ownership")
  public List<Propriedade> getAll() {
    return propriedadeRepository.findAll();
  }

  @GetMapping("/ownership/{name}")
  public Propriedade getOneByName(@PathVariable String name) {
    return propriedadeRepository.findOneByName(name).get();
  }

}

