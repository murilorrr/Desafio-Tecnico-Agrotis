package com.agrotis.agrotis.controllers;

import com.agrotis.agrotis.entities.Propriedade;
import com.agrotis.agrotis.exceptions.ErroChavePropriedade;
import com.agrotis.agrotis.exceptions.ErroDeChave;
import com.agrotis.agrotis.exceptions.ErroPropriedadeNaoEncontrada;
import com.agrotis.agrotis.repositories.PropriedadeRepository;

import java.util.List;
import java.util.Optional;

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
public class PropriedadeController {

  PropriedadeRepository propriedadeRepository;

  @PostMapping("/ownerships")
  public ResponseEntity<Propriedade> create(@RequestBody Propriedade propriedade) throws
      ErroDeChave {
    if ((propriedade.getCnpj() == null || propriedade.getName() == null)) {
      throw new ErroChavePropriedade(null);
    }

    List<Propriedade> props = propriedadeRepository.findByName(propriedade.getName());
    if (props.size() == 0) {
      return new ResponseEntity<>(propriedadeRepository.save(
              propriedade), HttpStatus.OK
      );
    }
    return new ResponseEntity<>(HttpStatus.CONFLICT);
  }

  @GetMapping("/ownerships")
  public ResponseEntity<List<Propriedade>> getAll() {
    return ResponseEntity.status(HttpStatus.OK).body(propriedadeRepository.findAll());
  }

  @GetMapping("/ownerships/{name}")
  public Propriedade getOneByName(@PathVariable String name) throws ErroPropriedadeNaoEncontrada {
    Optional<Propriedade> propriedade = propriedadeRepository.findOneByName(name);
    if (propriedade.isPresent()) {
      return propriedade.get();
    }
    throw new ErroPropriedadeNaoEncontrada();
  }

}

