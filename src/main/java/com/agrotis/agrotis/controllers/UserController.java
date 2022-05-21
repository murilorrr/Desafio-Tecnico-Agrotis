package com.agrotis.agrotis.controllers;

import java.util.List;

import com.agrotis.agrotis.Entities.User;
import com.agrotis.agrotis.repositories.UserRepository;

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
  
  @GetMapping("/users")
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @GetMapping("/users/{id}")
  public User getById(@PathVariable Long id) {
    return userRepository.findById(id).get();
  }

  @PostMapping("/users")
  public User create(@RequestBody User user) {
    return userRepository.save(user);
  }

  @DeleteMapping("/users/{id}")
  public void delete(@PathVariable Long id) {
    userRepository.deleteById(id);
    return;
  }

}
