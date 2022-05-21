package com.agrotis.agrotis.controllers;

import java.util.List;

import com.agrotis.agrotis.Entities.Laboratorio;
import com.agrotis.agrotis.Entities.Propriedade;
import com.agrotis.agrotis.Entities.User;
import com.agrotis.agrotis.Entities.UserRequest;
import com.agrotis.agrotis.Exceptions.ErroChaveDate;
import com.agrotis.agrotis.Exceptions.ErroChaveLaboratorio;
import com.agrotis.agrotis.Exceptions.ErroChaveName;
import com.agrotis.agrotis.Exceptions.ErroChavePropriedade;
import com.agrotis.agrotis.Exceptions.ErroDeChave;
import com.agrotis.agrotis.repositories.LaboratorioRepository;
import com.agrotis.agrotis.repositories.PropriedadeRepository;
import com.agrotis.agrotis.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @GetMapping("/users/{id}")
  public User getById(@PathVariable Long id) {
    return userRepository.findById(id).get();
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

    lab = laboratorioRepository.findOneByName(u.getLaboratorio());
    if(lab == null) {
      throw new ErroChaveLaboratorio();
    }

    prop = propriedadeRepository.findOneByName(u.getPropriedade());
    if(prop == null) {
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

  @DeleteMapping("/users/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    userRepository.deleteById(id);
    return new ResponseEntity<String>("User has deleted", HttpStatus.OK);
  }

}
