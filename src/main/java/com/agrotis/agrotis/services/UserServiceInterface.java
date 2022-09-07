package com.agrotis.agrotis.services;

import com.agrotis.agrotis.entities.User;
import com.agrotis.agrotis.entities.UserRequest;
import com.agrotis.agrotis.exceptions.KeyError;
import com.agrotis.agrotis.exceptions.ErrorUserNotFound;

import java.util.List;

public interface UserServiceInterface {
  List<User> findAll();
  
  User findById(Long id) throws ErrorUserNotFound;

  User create(UserRequest userRequest) throws KeyError;

  void delete(Long id) throws ErrorUserNotFound;

  User update(UserRequest userRequest, Long id) throws KeyError, ErrorUserNotFound;
}
