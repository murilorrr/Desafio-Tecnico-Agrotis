package com.agrotis.agrotis.services;

import java.util.List;
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

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {

  UserRepository userRepository;
  LaboratorioRepository laboratorioRepository;
  PropriedadeRepository propriedadeRepository;

  @Override
  public List<User> findAll() {
    List<User> users = userRepository.findAll();
    return users;
  }
  
  @Override
  public User findById(Long id) throws ErroUsuarioNaoEncontrado {
    return userRepository.findById(id)
      .orElseThrow(() -> new ErroUsuarioNaoEncontrado());
  }

  @Override
  public User create(UserRequest userRequest) throws ErroDeChave {

    Laboratorio lab;
    Propriedade prop;

    if (userRequest.getName() == null) {
      throw new ErroChaveName();
    }

    if ((userRequest.getInitialDate() == null) || (userRequest.getEndDate() == null)) {
      throw new ErroChaveDate();
    }

    try {
      lab = laboratorioRepository.findOneByName(userRequest.getLaboratorio()).get();
    } catch (NoSuchElementException e) {
      throw new ErroChaveLaboratorio();
    }

    try {
      prop = propriedadeRepository.findOneByName(userRequest.getPropriedade()).get();
    } catch (NoSuchElementException e) {
      throw new ErroChavePropriedade();
    }
    
    User user = new User();
    user.setName(userRequest.getName());
    user.setInitialDate(userRequest.getInitialDate());
    user.setEndDate(userRequest.getEndDate());
    user.setLaboratorio(lab);
    user.setPropriedade(prop);
    user.setComments(userRequest.getComments());

    return userRepository.save(user);
  }

  @Override
  public void delete(Long id) throws ErroUsuarioNaoEncontrado {
    try{
      userRepository.deleteById(id);
    }
    catch (IllegalArgumentException e) {
      throw new ErroUsuarioNaoEncontrado();
    }
  }

  @Override
  public User update(UserRequest userRequest, Long id) throws ErroDeChave, ErroUsuarioNaoEncontrado {
    User user = findById(id);

    Laboratorio lab;
    Propriedade prop;

    try {
      lab = laboratorioRepository.findOneByName(userRequest.getLaboratorio()).get();
    } catch (NoSuchElementException e) {
      throw new ErroChaveLaboratorio();
    }

    try {
      prop = propriedadeRepository.findOneByName(userRequest.getPropriedade()).get();
    } catch (NoSuchElementException e) {
      throw new ErroChavePropriedade();
    }

    user.setLaboratorio(lab);
    user.setPropriedade(prop);

    user.setName(userRequest.getName());
    user.setInitialDate(userRequest.getInitialDate());
    user.setEndDate(userRequest.getEndDate());
    user.setComments(userRequest.getComments());
    return userRepository.save(user);
  }
}
