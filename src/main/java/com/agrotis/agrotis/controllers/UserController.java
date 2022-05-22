package com.agrotis.agrotis.controllers;

import java.util.List;
import java.util.Map;
import com.agrotis.agrotis.Entities.User;
import com.agrotis.agrotis.Entities.UserRequest;
import com.agrotis.agrotis.Exceptions.ErroDeChave;
import com.agrotis.agrotis.Exceptions.ErroUsuarioNaoEncontrado;
import com.agrotis.agrotis.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

  UserService userService;
  
  @GetMapping("/users")
  // @ResponseStatus(code = HttpStatus.OK, reason = "OK")
  public Map<String,List<User>> getAll() {
    List<User> users = userService.findAll();
    return Map.of("users", users);
  }

  @GetMapping("/users/{id}")
  // @ResponseStatus(code = HttpStatus.OK, reason = "OK")
  public User getById(@PathVariable Long id) throws ErroUsuarioNaoEncontrado {
    return userService.findById(id);
  }

  @PostMapping("/users")
  // @ResponseStatus(code = HttpStatus.CREATED, reason = "CREATED")
  public User create(@RequestBody UserRequest userRequest) throws ErroDeChave {
    return userService.create(userRequest);
  }

  @PutMapping("/users/{id}")
  // @ResponseStatus(code = HttpStatus.OK, reason = "Update")
  public User updateUser(@PathVariable Long id, @RequestBody UserRequest u) throws ErroDeChave, ErroUsuarioNaoEncontrado {
    return userService.update(u, id);
  }

  @DeleteMapping("/users/{id}")
  // @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Deleted")
  public ResponseEntity<String> delete(@PathVariable Long id) throws ErroUsuarioNaoEncontrado {
    userService.delete(id);
    return new ResponseEntity<String>("User has deleted", HttpStatus.OK);
  }

}
