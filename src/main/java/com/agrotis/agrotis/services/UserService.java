package com.agrotis.agrotis.services;

import com.agrotis.agrotis.entities.Laboratorio;
import com.agrotis.agrotis.entities.Propriedade;
import com.agrotis.agrotis.entities.User;
import com.agrotis.agrotis.entities.UserRequest;
import com.agrotis.agrotis.exceptions.ErroChaveDate;
import com.agrotis.agrotis.exceptions.ErroChaveLaboratorio;
import com.agrotis.agrotis.exceptions.ErroChaveName;
import com.agrotis.agrotis.exceptions.ErroChavePropriedade;
import com.agrotis.agrotis.exceptions.ErroDeChave;
import com.agrotis.agrotis.exceptions.ErroUsuarioNaoEncontrado;
import com.agrotis.agrotis.repositories.LaboratorioRepository;
import com.agrotis.agrotis.repositories.PropriedadeRepository;
import com.agrotis.agrotis.repositories.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


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
      throw new ErroChaveLaboratorio(userRequest.getLaboratorio());
    }

    try {
      prop = propriedadeRepository.findOneByName(userRequest.getPropriedade()).get();
    } catch (NoSuchElementException e) {
      throw new ErroChavePropriedade(userRequest.getPropriedade());
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
    try {
      userRepository.deleteById(id);
    } catch (IllegalArgumentException e) {
      throw new ErroUsuarioNaoEncontrado();
    }
  }

  @Override
  public User update(UserRequest userRequest, Long id) throws
      ErroDeChave, ErroUsuarioNaoEncontrado {
    User user = findById(id);

    Laboratorio lab;
    Propriedade prop;

    try {
      lab = laboratorioRepository.findOneByName(userRequest.getLaboratorio()).get();
    } catch (NoSuchElementException e) {
      throw new ErroChaveLaboratorio(userRequest.getLaboratorio());
    }

    try {
      prop = propriedadeRepository.findOneByName(userRequest.getPropriedade()).get();
    } catch (NoSuchElementException e) {
      throw new ErroChavePropriedade(userRequest.getPropriedade());
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
