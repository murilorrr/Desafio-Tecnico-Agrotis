package com.agrotis.agrotis.controllers;

import java.util.List;

import com.agrotis.agrotis.Entities.User;
import com.agrotis.agrotis.repositories.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  UserRepository userRepository;
  
  @GetMapping("/users")
  public List<User> getAll() {
    return userRepository.findAll();
  }

}
