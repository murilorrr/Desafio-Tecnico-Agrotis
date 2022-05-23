package com.agrotis.agrotis.services;

import com.agrotis.agrotis.entities.User;
import com.agrotis.agrotis.entities.UserRequest;
import com.agrotis.agrotis.exceptions.ErroDeChave;
import com.agrotis.agrotis.exceptions.ErroUsuarioNaoEncontrado;

import java.util.List;

public interface UserServiceInterface {
  public List<User> findAll();
  
  public User findById(Long id) throws ErroUsuarioNaoEncontrado;

  public User create(UserRequest userRequest) throws ErroDeChave;

  public void delete(Long id) throws ErroUsuarioNaoEncontrado;

  public User update(UserRequest userRequest, Long id) throws ErroDeChave, ErroUsuarioNaoEncontrado;
}
