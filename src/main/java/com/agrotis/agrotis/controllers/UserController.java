package com.agrotis.agrotis.controllers;

import com.agrotis.agrotis.entities.User;
import com.agrotis.agrotis.entities.UserRequest;
import com.agrotis.agrotis.exceptions.KeyError;
import com.agrotis.agrotis.exceptions.ErrorUserNotFound;
import com.agrotis.agrotis.services.UserService;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class UserController {

  UserService userService;
  
  @GetMapping("/users")
  public Map<String,List<User>> getAll() {
    List<User> users = userService.findAll();
    return Map.of("users", users);
  }

  @GetMapping("/users/{id}")
  public User getById(@PathVariable Long id) throws ErrorUserNotFound {
    return userService.findById(id);
  }

  @PostMapping("/users")
  public User create(@RequestBody UserRequest userRequest) throws KeyError {
    return userService.create(userRequest);
  }

  @PutMapping("/users/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody UserRequest u) throws ErrorUserNotFound {
    return userService.update(u, id);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) throws ErrorUserNotFound {
    userService.delete(id);
    return new ResponseEntity<>("User has deleted", HttpStatus.OK);
  }

}
