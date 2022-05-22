package com.agrotis.agrotis.controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.agrotis.agrotis.Entities.Laboratorio;
import com.agrotis.agrotis.Entities.Propriedade;
import com.agrotis.agrotis.Entities.User;
import com.agrotis.agrotis.Entities.UserRequest;
import com.agrotis.agrotis.Exceptions.ErroChaveDate;
import com.agrotis.agrotis.Exceptions.ErroChaveLaboratorio;
import com.agrotis.agrotis.Exceptions.ErroChaveName;
import com.agrotis.agrotis.Exceptions.ErroChavePropriedade;
import com.agrotis.agrotis.Exceptions.ErroDeChave;
import com.agrotis.agrotis.Exceptions.ErroUsuarioNaoEncontrado;
import com.agrotis.agrotis.repositories.LaboratorioRepository;
import com.agrotis.agrotis.repositories.PropriedadeRepository;
import com.agrotis.agrotis.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

  UserRepository userRepository;
  LaboratorioRepository laboratorioRepository;
  PropriedadeRepository propriedadeRepository;
  
  @GetMapping("/users")
  public Map<String,List<User>> getAll() {
    List<User> users = userRepository.findAll();
    return Map.of("users", users);
  }

  @GetMapping("/users/{id}")
  public User getById(@PathVariable Long id) throws ErroUsuarioNaoEncontrado {
    return userRepository.findById(id)
      .orElseThrow(() -> new ErroUsuarioNaoEncontrado());
  }

  @PostMapping("/users")
  public User create(@RequestBody UserRequest u) throws ErroDeChave {
    Laboratorio lab;
    Propriedade prop;

    if (u.getName() == null) {
      throw new ErroChaveName();
    }

    if ((u.getInitialDate() == null) || (u.getEndDate() == null)) {
      throw new ErroChaveDate();
    }

    try {
      lab = laboratorioRepository.findOneByName(u.getLaboratorio()).get();
    } catch (NoSuchElementException e) {
      throw new ErroChaveLaboratorio();
    }

    try {
      prop = propriedadeRepository.findOneByName(u.getPropriedade()).get();
    } catch (NoSuchElementException e) {
      throw new ErroChavePropriedade();
    }
    
    User user = new User();
    user.setName(u.getName());
    user.setInitialDate(u.getInitialDate());
    user.setEndDate(u.getEndDate());
    user.setLaboratorio(lab);
    user.setPropriedade(prop);
    user.setComments(u.getComments());

    return userRepository.save(user);
  }

  @PutMapping("/users/{id}")
  public User updateUser(@PathVariable Long id,@RequestBody UserRequest u) throws ErroDeChave {
    User user = userRepository.findById(id).get();

    Laboratorio lab;
    Propriedade prop;

    try {
      lab = laboratorioRepository.findOneByName(u.getLaboratorio()).get();
    } catch (NoSuchElementException e) {
      throw new ErroChaveLaboratorio();
    }

    try {
      prop = propriedadeRepository.findOneByName(u.getPropriedade()).get();
    } catch (NoSuchElementException e) {
      throw new ErroChavePropriedade();
    }

    user.setLaboratorio(lab);
    user.setPropriedade(prop);

    user.setName(u.getName());
    user.setInitialDate(u.getInitialDate());
    user.setEndDate(u.getEndDate());
    user.setComments(u.getComments());
    return userRepository.save(user);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    userRepository.deleteById(id);
    return new ResponseEntity<String>("User has deleted", HttpStatus.OK);
  }

}
