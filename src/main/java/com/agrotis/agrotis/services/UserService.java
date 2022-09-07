package com.agrotis.agrotis.services;

import com.agrotis.agrotis.entities.Laboratory;
import com.agrotis.agrotis.entities.Propriedade;
import com.agrotis.agrotis.entities.User;
import com.agrotis.agrotis.entities.UserRequest;
import com.agrotis.agrotis.exceptions.ErroChaveDate;
import com.agrotis.agrotis.exceptions.KeyErrorLaboratory;
import com.agrotis.agrotis.exceptions.ErroChaveName;
import com.agrotis.agrotis.exceptions.ErroChavePropriedade;
import com.agrotis.agrotis.exceptions.ErroDeChave;
import com.agrotis.agrotis.exceptions.ErroUsuarioNaoEncontrado;
import com.agrotis.agrotis.repositories.LaboratoryRepository;
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
  LaboratoryRepository laboratoryRepository;
  PropriedadeRepository propriedadeRepository;

  @Override
  public List<User> findAll() {
    List<User> users = userRepository.findAll();
    return users;
  }
  
  @Override
  public User findById(Long id) throws ErroUsuarioNaoEncontrado {
    return userRepository.findById(id)
      .orElseThrow(ErroUsuarioNaoEncontrado::new);
  }

  @Override
  public User create(UserRequest userRequest) throws ErroDeChave {

    Laboratory lab;
    Propriedade prop;

    if (userRequest.getName() == null) {
      throw new ErroChaveName();
    }

    if ((userRequest.getInitialDate() == null) || (userRequest.getEndDate() == null)) {
      throw new ErroChaveDate();
    }

    try {
      lab = laboratoryRepository.findOneByName(userRequest.getLaboratory()).get();
    } catch (NoSuchElementException e) {
      throw new KeyErrorLaboratory(userRequest.getLaboratory());
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
    user.setLaboratory(lab);
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

    Laboratory lab;
    Propriedade prop;

    try {
      lab = laboratoryRepository.findOneByName(userRequest.getLaboratory()).get();
    } catch (NoSuchElementException e) {
      throw new KeyErrorLaboratory(userRequest.getLaboratory());
    }

    try {
      prop = propriedadeRepository.findOneByName(userRequest.getPropriedade()).get();
    } catch (NoSuchElementException e) {
      throw new ErroChavePropriedade(userRequest.getPropriedade());
    }

    user.setLaboratory(lab);
    user.setPropriedade(prop);

    user.setName(userRequest.getName());
    user.setInitialDate(userRequest.getInitialDate());
    user.setEndDate(userRequest.getEndDate());
    user.setComments(userRequest.getComments());
    return userRepository.save(user);
  }
}
