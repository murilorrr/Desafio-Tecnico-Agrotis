package com.agrotis.agrotis.services;

import java.util.List;

import com.agrotis.agrotis.Entities.User;
import com.agrotis.agrotis.Entities.UserRequest;
import com.agrotis.agrotis.Exceptions.ErroDeChave;
import com.agrotis.agrotis.Exceptions.ErroUsuarioNaoEncontrado;

public interface UserServiceInterface {
  public List<User> findAll();
  public User findById(Long id) throws ErroUsuarioNaoEncontrado;
  public User create(UserRequest userRequest) throws ErroDeChave;
  public void delete(Long id) throws ErroUsuarioNaoEncontrado;
  public User update(UserRequest userRequest, Long id) throws ErroDeChave, ErroUsuarioNaoEncontrado;
}
